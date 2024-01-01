package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.CustomRuntimeException;
import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.MediaDTO;
import com.example.satoruquizzes.satoquiz.model.dto.QuestionDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.QuestionRespDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Level;
import com.example.satoruquizzes.satoquiz.model.entity.Media;
import com.example.satoruquizzes.satoquiz.model.entity.Question;
import com.example.satoruquizzes.satoquiz.model.entity.Subject;
import com.example.satoruquizzes.satoquiz.repository.MediaRepository;
import com.example.satoruquizzes.satoquiz.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final MediaRepository mediaRepository;

    private final ModelMapper modelMapper;

    public QuestionService(QuestionRepository questionRepository, MediaRepository mediaRepository, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.mediaRepository = mediaRepository;
        this.modelMapper = modelMapper;
    }

    public QuestionDTO save(QuestionDTO questionDTO) {
        try {
            Question question = modelMapper.map(questionDTO, Question.class);
            question = questionRepository.save(question);
            return modelMapper.map(question, QuestionDTO.class);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while saving question: " + e.getMessage());
        }
    }

    public QuestionDTO saveWithMedia(QuestionDTO questionDTO) {
        try {
            Question question = modelMapper.map(questionDTO, Question.class);
            question = questionRepository.save(question);

            List<MediaDTO> mediaDTOList = questionDTO.getMediaList();
            if (mediaDTOList != null) {
                question.getMediaList().clear();

                for (MediaDTO mediaDTO : mediaDTOList) {
                    Media media = modelMapper.map(mediaDTO, Media.class);
                    media.setQuestion(question);
                    question.getMediaList().add(media);
                }

                questionRepository.save(question);
            }

            return modelMapper.map(question, QuestionDTO.class);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while saving question with media: " + e.getMessage());
        }
    }

    public List<QuestionRespDTO> getAll() {
        try {
            List<Question> questions = questionRepository.findAll();
            return questions.stream()
                    .map(question -> modelMapper.map(question, QuestionRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while fetching all questions: " + e.getMessage());
        }
    }

    public QuestionRespDTO getById(Long id) {
        try {
            Question question = questionRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Question not found for id: " + id));
            return modelMapper.map(question, QuestionRespDTO.class);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while fetching question by id: " + id + ": " + e.getMessage());
        }
    }

    public QuestionDTO update(Long id, QuestionDTO newQuestionDTO) {
        try {
            Question existingQuestion = questionRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Question not found for id: " + id));

            // Update Question fields
            existingQuestion.setAnswersNumber(newQuestionDTO.getAnswersNumber());
            existingQuestion.setAnswersNumberCorrect(newQuestionDTO.getAnswersNumberCorrect());
            existingQuestion.setText(newQuestionDTO.getText());
            existingQuestion.setType(newQuestionDTO.getType());
            existingQuestion.setScorePoints(newQuestionDTO.getScorePoints());

            // Update associated Level and Subject
            if (newQuestionDTO.getLevelId() != null) {
                existingQuestion.setLevel(modelMapper.map(newQuestionDTO.getLevelId(), Level.class));
            }
            if (newQuestionDTO.getSubjectId() != null) {
                existingQuestion.setSubject(modelMapper.map(newQuestionDTO.getSubjectId(), Subject.class));
            }

            List<MediaDTO> mediaDTOList = newQuestionDTO.getMediaList();
            if (mediaDTOList != null) {
                existingQuestion.getMediaList().clear();

                for (MediaDTO mediaDTO : mediaDTOList) {
                    Media existingMedia = findExistingMedia(mediaDTO.getMediaId());

                    if (existingMedia != null) {
                        existingMedia.setType(mediaDTO.getType());
                        existingMedia.setLink(mediaDTO.getLink());

                        existingMedia.setQuestion(existingQuestion);

                        existingQuestion.getMediaList().add(existingMedia);
                    }
                }
            }

            existingQuestion = questionRepository.save(existingQuestion);

            return modelMapper.map(existingQuestion, QuestionDTO.class);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while updating question: " + e.getMessage());
        }
    }

    public Media findExistingMedia(Long mediaId) {
        Optional<Media> existingMedia = mediaRepository.findById(mediaId);
        return existingMedia.orElse(null);
    }

    public void delete(Long id) {
        try {
            questionRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while deleting question: " + e.getMessage());
        }
    }
}
