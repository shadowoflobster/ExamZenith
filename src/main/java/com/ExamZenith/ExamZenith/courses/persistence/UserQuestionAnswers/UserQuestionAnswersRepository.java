package com.ExamZenith.ExamZenith.courses.persistence.UserQuestionAnswers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuestionAnswersRepository extends JpaRepository<UserQuestionAnswers, UserQuestionAnswersKey> {
}
