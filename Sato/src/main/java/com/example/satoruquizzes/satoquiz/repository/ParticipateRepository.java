package com.example.satoruquizzes.satoquiz.repository;

import com.example.satoruquizzes.satoquiz.model.entity.Participate;
import com.example.satoruquizzes.satoquiz.model.entity.Salon;
import com.example.satoruquizzes.satoquiz.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipateRepository extends JpaRepository<Participate, Long> {


    @Query("SELECT p FROM Participate p WHERE p.participateId.student.studentId = :studentId AND p.participateId.salon.id = :salonId")
    Optional<Participate> findByStudentIdAndSalonId(Long studentId, Long salonId);

}