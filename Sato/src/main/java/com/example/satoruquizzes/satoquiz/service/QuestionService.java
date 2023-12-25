package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.MediaDTO;
import com.example.satoruquizzes.satoquiz.model.dto.QuestionDTO;
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

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private ModelMapper modelMapper;

     public QuestionDTO save(QuestionDTO questionDTO) {
        Question question = modelMapper.map(questionDTO, Question.class);
        question = questionRepository.save(question);
        return modelMapper.map(question, QuestionDTO.class);
    }


/*    public QuestionDTO saveWithMedia(QuestionDTO questionDTO) {
        // Map and save the question
        Question question = modelMapper.map(questionDTO, Question.class);
        question = questionRepository.save(question);

        List<MediaDTO> mediaDTOList = questionDTO.getMediaList();
        if (mediaDTOList != null) {
            for (MediaDTO mediaDTO : mediaDTOList) {
                Media media = modelMapper.map(mediaDTO, Media.class);
                media.setQuestion(question);
                mediaRepository.save(media);
            }
        }

        return modelMapper.map(question, QuestionDTO.class);
    }

 */


    public QuestionDTO saveWithMedia(QuestionDTO questionDTO) {
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
    }



    public List<QuestionDTO> getAll() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(question -> modelMapper.map(question, QuestionDTO.class))
                .collect(Collectors.toList());
    }

    public QuestionDTO getById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question not found for id: " + id));
        return modelMapper.map(question, QuestionDTO.class);
    }

    /*
    public QuestionDTO update(Long id, QuestionDTO newQuestionDTO) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question not found for id: " + id));

        existingQuestion.setAnswersNumber(newQuestionDTO.getAnswersNumber());
        existingQuestion.setAnswersNumberCorrect(newQuestionDTO.getAnswersNumberCorrect());
        existingQuestion.setText(newQuestionDTO.getText());
        existingQuestion.setType(newQuestionDTO.getType());
        existingQuestion.setScorePoints(newQuestionDTO.getScorePoints());

        if (newQuestionDTO.getLevel() != null) {
            existingQuestion.setLevel(modelMapper.map(newQuestionDTO.getLevel(), Level.class));
        }
        if (newQuestionDTO.getSubject() != null) {
            existingQuestion.setSubject(modelMapper.map(newQuestionDTO.getSubject(), Subject.class));
        }



        return modelMapper.map(questionRepository.save(existingQuestion), QuestionDTO.class);
    }

     */
    public QuestionDTO update(Long id, QuestionDTO newQuestionDTO) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question not found for id: " + id));

        // Update Question fields
        existingQuestion.setAnswersNumber(newQuestionDTO.getAnswersNumber());
        existingQuestion.setAnswersNumberCorrect(newQuestionDTO.getAnswersNumberCorrect());
        existingQuestion.setText(newQuestionDTO.getText());
        existingQuestion.setType(newQuestionDTO.getType());
        existingQuestion.setScorePoints(newQuestionDTO.getScorePoints());

        // Update associated Level and Subject
        if (newQuestionDTO.getLevel() != null) {
            existingQuestion.setLevel(modelMapper.map(newQuestionDTO.getLevel(), Level.class));
        }
        if (newQuestionDTO.getSubject() != null) {
            existingQuestion.setSubject(modelMapper.map(newQuestionDTO.getSubject(), Subject.class));
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
    }

    public Media findExistingMedia(Long mediaId) {
        Optional<Media> existingMedia = mediaRepository.findById(mediaId);
        return existingMedia.orElse(null);
    }


    public void delete(Long id) {
        questionRepository.deleteById(id);

    }
}