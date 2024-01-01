package com.example.satoruquizzes.satoquiz.model.entity;

import com.example.satoruquizzes.satoquiz.model.enums.MediaType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaId;

    @NotBlank(message = "Link cannot be blank")
    private String link;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MediaType type;

    @NotNull(message = "Question cannot be null")
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
