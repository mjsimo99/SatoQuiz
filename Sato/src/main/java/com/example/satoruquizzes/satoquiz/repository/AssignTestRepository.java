package com.example.satoruquizzes.satoquiz.repository;

import com.example.satoruquizzes.satoquiz.model.entity.AssignTest;
import com.example.satoruquizzes.satoquiz.model.entity.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignTestRepository extends JpaRepository<AssignTest, Long> {

}