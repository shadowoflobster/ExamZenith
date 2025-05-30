package com.ExamZenith.ExamZenith.courses.persistence.ValidAnswers;


import com.ExamZenith.ExamZenith.courses.persistence.UserQuestionAnswers.UserQuestionAnswersKey;
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
public class ValidAnswersKey implements Serializable {
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "answer_options_id")
    private Long answerOptionsId;

    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof ValidAnswersKey)) return false;

        ValidAnswersKey that = (ValidAnswersKey) o;
        return Objects.equals(questionId, that.questionId) &&
                Objects.equals(answerOptionsId, that.answerOptionsId);

    }

    public int hashCode() {return Objects.hash(questionId,answerOptionsId);}

}
