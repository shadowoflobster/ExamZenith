package com.ExamZenith.ExamZenith.courses.persistence.ValidAnswers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidAnswersRepository extends JpaRepository<ValidAnswersKey,ValidAnswers> {
}
