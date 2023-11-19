package com.example.satoruquizzes.satoquiz.repository;

import com.example.satoruquizzes.satoquiz.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}