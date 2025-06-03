package com.ExamZenith.ExamZenith.courses.persistence.UserQuestionGrade;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserQuestionGradeKey implements Serializable {
    @Column(name="student_id")
    private Long studentId;

    @Column(name="question_id")
    private Long questionId;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof UserQuestionGradeKey)) return false;

        UserQuestionGradeKey that = (UserQuestionGradeKey) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(questionId, that.questionId);

    }

    @Override
    public int hashCode(){return Objects.hash(studentId,questionId);}

}
