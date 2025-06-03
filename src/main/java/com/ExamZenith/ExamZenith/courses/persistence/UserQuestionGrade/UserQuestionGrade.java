package com.ExamZenith.ExamZenith.courses.persistence.UserQuestionGrade;


import com.ExamZenith.ExamZenith.courses.persistence.Question.Question;
import com.ExamZenith.ExamZenith.users.persistence.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserQuestionGrade {
    @EmbeddedId
    private UserQuestionGradeKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name="student_id", nullable = false)
    private User student;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name="question_id", nullable = false)
    private Question question;

    @Column(name = "is_true")
    private Boolean isTrue;


}
