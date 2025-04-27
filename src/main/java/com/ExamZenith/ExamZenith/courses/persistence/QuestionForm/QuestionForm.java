package com.ExamZenith.ExamZenith.courses.persistence.QuestionForm;


import com.ExamZenith.ExamZenith.courses.persistence.Course.Course;
import com.ExamZenith.ExamZenith.courses.persistence.Question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "question_forms", schema = "exam_zenith")
@SequenceGenerator(name = "question_form_seq_gen", sequenceName = "question_form_seq", allocationSize = 1)
@Getter
@Setter
public class QuestionForm {
    @Id
    @GeneratedValue(generator = "question_form_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;


    private String title;

    @OneToMany(mappedBy = "questionForm", fetch = FetchType.LAZY)
    private Set<Question> questions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
