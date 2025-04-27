package com.ExamZenith.ExamZenith.courses.persistence.QuestionForm;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface QuestionFormRepository extends JpaRepository<QuestionForm, Long> {
}
