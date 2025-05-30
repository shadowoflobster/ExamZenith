package com.ExamZenith.ExamZenith.courses.persistence.ValidAnswers;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ValidAnswers {
    @EmbeddedId
    private ValidAnswersKey id;

    @ManyToOne
    @MapsId("question_id")
    @JoinColumn(name="question_id", nullable = false)
    private Long questionId;

    @ManyToOne
    @MapsId("answer_options_id")
    @JoinColumn(name = "answer_options_id", nullable = false)
    private Long answerOptionsId;


}
