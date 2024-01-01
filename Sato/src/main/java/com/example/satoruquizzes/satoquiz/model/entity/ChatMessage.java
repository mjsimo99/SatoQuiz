package com.example.satoruquizzes.satoquiz.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @NotBlank(message = "Content cannot be blank")
    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "student_id", referencedColumnName = "studentId"),
            @JoinColumn(name = "salon_id", referencedColumnName = "salonId")
    })
    private Participate participate;

    @CreationTimestamp
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
