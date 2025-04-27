package com.ExamZenith.ExamZenith.courses.service;

import com.ExamZenith.ExamZenith.courses.model.Course.CourseRequest;
import com.ExamZenith.ExamZenith.courses.persistence.Course.Course;
import com.ExamZenith.ExamZenith.courses.persistence.Course.CourseRepository;
import com.ExamZenith.ExamZenith.errors.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public void createCourse(CourseRequest request){
        Course course = new Course();
        course.setName(request.getName());
        courseRepository.save(course);
    }

    public void deleteCourse(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No course found with id: "+id));
        courseRepository.deleteById(id);
    }

}
