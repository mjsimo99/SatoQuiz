package com.example.satoruquizzes.satoquiz.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "participates")
@Data
@ToString
public class Participate {

    @EmbeddedId
    private ParticipateId participateId;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne
    @MapsId("salonId")
    @JoinColumn(name = "salonId")
    private Salon salon;

    @OneToMany(mappedBy = "participate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> chatMessages;

}
