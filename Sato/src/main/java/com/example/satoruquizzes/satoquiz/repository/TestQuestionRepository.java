package com.example.satoruquizzes.satoquiz.repository;

import com.example.satoruquizzes.satoquiz.model.entity.TestQuestion;
import com.example.satoruquizzes.satoquiz.model.entity.TestQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestion, TestQuestionId> {
    List<TestQuestion> findByTestTestId(Long testId);

}