package com.ExamZenith.ExamZenith.courses.persistence.UserQuestionAnswers;

import com.ExamZenith.ExamZenith.courses.persistence.AnswerOption.AnswerOption;
import com.ExamZenith.ExamZenith.courses.persistence.Question.Question;
import com.ExamZenith.ExamZenith.users.persistence.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_question_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestionAnswers {
    @EmbeddedId
    private UserQuestionAnswersKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_options_id")
    private AnswerOption answerOption;

    @Column(name = "answer_text")
    private String answerText;

    @Column(name = "answered_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime answeredAt = LocalDateTime.now();

}
