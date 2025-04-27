package com.ExamZenith.ExamZenith.courses.persistence.Course;


import com.ExamZenith.ExamZenith.courses.persistence.QuestionForm.QuestionForm;
import com.ExamZenith.ExamZenith.users.persistence.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses", schema = "exam_zenith")
@SequenceGenerator(name = "course_seq_gen", sequenceName = "course_seq", allocationSize = 1)
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(generator = "course_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            schema = "exam_zenith",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<User> students = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "teacher_course",
            schema = "exam_zenith",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<User> teachers = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<QuestionForm> questionForms = new HashSet<>();


}
