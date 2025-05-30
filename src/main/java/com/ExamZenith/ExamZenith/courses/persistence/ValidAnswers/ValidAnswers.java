package com.ExamZenith.ExamZenith.courses.persistence.ValidAnswers;


import com.ExamZenith.ExamZenith.courses.persistence.AnswerOption.AnswerOption;
import com.ExamZenith.ExamZenith.courses.persistence.Question.Question;
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
    @MapsId("questionId")
    @JoinColumn(name="question_id", nullable = false)
    private Question question;

    @ManyToOne
    @MapsId("answerOptionsId")
    @JoinColumn(name = "answer_options_id", nullable = false)
    private AnswerOption answerOption;


}
