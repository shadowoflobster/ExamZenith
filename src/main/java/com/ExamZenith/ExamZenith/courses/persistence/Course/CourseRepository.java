package com.ExamZenith.ExamZenith.courses.persistence.Course;

import com.ExamZenith.ExamZenith.courses.model.Course.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("SELECT NEW com.ExamZenith.ExamZenith.courses.model.Course.CourseDTO(" +
            "c.id, c.name) " +
            "FROM Course c")
    Page<CourseDTO> findCourses(Pageable pageable);
}
