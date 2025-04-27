package com.ExamZenith.ExamZenith.courses.persistence.AnswerOption;


import com.ExamZenith.ExamZenith.courses.persistence.Question.Question;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "answer_options", schema = "exam_zenith")
@SequenceGenerator(name = "answer_options_seq_gen", sequenceName = "answer_options_seq", allocationSize = 1)
@Getter
@Setter
public class AnswerOption {
    @Id
    @GeneratedValue(generator = "answer_options_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Size(max = 1000)
    private String option_text;
}
