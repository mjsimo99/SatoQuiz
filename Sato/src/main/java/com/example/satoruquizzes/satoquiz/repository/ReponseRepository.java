package com.example.satoruquizzes.satoquiz.repository;

import com.example.satoruquizzes.satoquiz.model.entity.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse, Long> {


    @Transactional
    @Modifying
    @Query("DELETE FROM Reponse r WHERE r.assignTest.assignTestId = :assignTestId")
    void deleteByAssignTestId(@Param("assignTestId") Long assignTestId);

}


