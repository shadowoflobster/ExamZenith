package com.ExamZenith.ExamZenith.courses.persistence.UserQuestionGrade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuestionGradeRepository extends JpaRepository<UserQuestionGrade,UserQuestionGradeKey> {
}
