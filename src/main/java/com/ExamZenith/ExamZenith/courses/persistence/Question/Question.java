package com.ExamZenith.ExamZenith.courses.persistence.Question;


import com.ExamZenith.ExamZenith.courses.model.QuestionType;
import com.ExamZenith.ExamZenith.courses.persistence.AnswerOption.AnswerOption;
import com.ExamZenith.ExamZenith.courses.persistence.QuestionForm.QuestionForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions", schema = "exam_zenith")
@SequenceGenerator(name = "question_seq_gen", sequenceName = "question_seq", allocationSize = 1)
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(generator = "question_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_form_id", nullable = false)
    private QuestionForm questionForm;

    private String question_text;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private QuestionType question_type;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AnswerOption> options = new HashSet<>();


}
