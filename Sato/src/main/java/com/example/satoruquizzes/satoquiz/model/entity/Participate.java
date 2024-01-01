package com.example.satoruquizzes.satoquiz.model.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "participates")
@Data
@ToString
public class Participate {

    @EmbeddedId
    @Valid
    private ParticipateId participateId;

    @NotNull(message = "Date cannot be null")
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "studentId")
    @NotNull(message = "Student cannot be null")
    private Student student;

    @ManyToOne
    @MapsId("salonId")
    @JoinColumn(name = "salonId")
    @NotNull(message = "Salon cannot be null")
    private Salon salon;

    @OneToMany(mappedBy = "participate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> chatMessages;
}
