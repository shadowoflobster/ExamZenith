package com.ExamZenith.ExamZenith.courses.service;

import com.ExamZenith.ExamZenith.courses.model.Course.CourseDTO;
import com.ExamZenith.ExamZenith.courses.model.Course.CourseRequest;
import com.ExamZenith.ExamZenith.courses.model.QuestionForm.QuestionFormDTO;
import com.ExamZenith.ExamZenith.courses.persistence.Course.Course;
import com.ExamZenith.ExamZenith.courses.persistence.Course.CourseRepository;
import com.ExamZenith.ExamZenith.errors.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final QuestionFormService questionFormService;



    public Page<CourseDTO> getCourses(int page, int pageSize){
            return courseRepository.findCourses(PageRequest.of(page,pageSize));
    }

    public CourseDTO mapCourse(Course course){
        Set<QuestionFormDTO> questionFormDTOSet = course.getQuestionForms()
                .stream()
                .map(questionFormService::mapQuestionForm)
                .collect(Collectors.toSet());
    return new CourseDTO(
        course.getId(),
        course.getName(),
        questionFormDTOSet
);
    }

    public CourseDTO getCourse(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException("Course not found with id: "+ id));
        if (course != null)
            return mapCourse(course);
        return null;
    }

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
