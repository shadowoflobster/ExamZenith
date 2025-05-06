package com.ExamZenith.ExamZenith;


import com.ExamZenith.ExamZenith.courses.model.Course.CourseDTO;
import com.ExamZenith.ExamZenith.courses.model.Course.CourseRequest;
import com.ExamZenith.ExamZenith.courses.model.QuestionForm.QuestionFormDTO;
import com.ExamZenith.ExamZenith.courses.persistence.Course.Course;
import com.ExamZenith.ExamZenith.courses.persistence.Course.CourseRepository;
import com.ExamZenith.ExamZenith.courses.persistence.QuestionForm.QuestionForm;
import com.ExamZenith.ExamZenith.courses.service.CourseService;
import com.ExamZenith.ExamZenith.courses.service.QuestionFormService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;

    @Mock
    private QuestionFormService questionFormService;

    @InjectMocks
    private CourseService courseService;


    @Test
    void getCourses_shouldReturnPageOfCourseDTOs(){
        //Given
        int page=0;
        int pageSize=2;

        CourseDTO course1=new CourseDTO();
        course1.setId(1L);
        course1.setName("Java Course 1");

        CourseDTO course2=new CourseDTO();
        course2.setId(2L);
        course2.setName("Java Course 2");

        List<CourseDTO> courses = List.of(course1,course2);
        Page<CourseDTO> coursePage = new PageImpl<>(courses);

        when(courseRepository.findCourses(PageRequest.of(page,pageSize)))
                .thenReturn(coursePage);

        //When
        Page<CourseDTO> result = courseService.getCourses(page,pageSize);

        //Then
        Assertions.assertEquals(2,result.getTotalElements());
        Assertions.assertEquals("Java Course 1",result.getContent().get(0).getName());
        Assertions.assertEquals("Java Course 2",result.getContent().get(1).getName());



    }

    @Test
    void mapCourse_shouldReturnCorrectCourseDTO(){
        //Given
        Course course = new Course();
        course.setId(1L);
        course.setName("Java Course");

        QuestionForm form1 = new QuestionForm();
        form1.setId(101L);
        QuestionForm form2 = new QuestionForm();
        form2.setId(102L);

        Set<QuestionForm> questionForms = Set.of(form1,form2);
        course.setQuestionForms(questionForms);

        QuestionFormDTO questionFormDTO1 = new QuestionFormDTO();
        questionFormDTO1.setId(101L);
        QuestionFormDTO questionFormDTO2 = new QuestionFormDTO();
        questionFormDTO2.setId(102L);

        when(questionFormService.mapQuestionForm(form1)).thenReturn(questionFormDTO1);
        when(questionFormService.mapQuestionForm(form2)).thenReturn(questionFormDTO2);

        //When
        CourseDTO result = courseService.mapCourse(course);

        //Then
        Assertions.assertEquals(1L,result.getId());
        Assertions.assertEquals("Java Course",result.getName());
        Assertions.assertEquals(2, result.getQuestionFormDTOset().size());
        Assertions.assertTrue(result.getQuestionFormDTOset().stream().anyMatch(dto -> dto.getId() == 101L));
        Assertions.assertTrue(result.getQuestionFormDTOset().stream().anyMatch(dto -> dto.getId() == 102L));
    }

    @Test
    void getCourse_shouldReturnCourseDTO(){

        //Given
        Long courseId = 1L;
        Course course = new Course();
        course.setId(courseId);
        course.setName("Java Course");

        QuestionForm form1 = new QuestionForm();
        form1.setId(101L);
        QuestionForm form2 = new QuestionForm();
        form2.setId(102L);

        QuestionFormDTO questionFormDTO1 = new QuestionFormDTO();
        questionFormDTO1.setId(101L);
        QuestionFormDTO questionFormDTO2 = new QuestionFormDTO();
        questionFormDTO2.setId(102L);

        when(questionFormService.mapQuestionForm(form1)).thenReturn(questionFormDTO1);
        when(questionFormService.mapQuestionForm(form2)).thenReturn(questionFormDTO2);

        Set<QuestionForm> questionForms = Set.of(form1,form2);
        course.setQuestionForms(questionForms);

        when(courseRepository.findById(courseId))
                .thenReturn(Optional.of(course));

        //When
        CourseDTO courseDTO = courseService.getCourse(courseId);

        //Then
        Assertions.assertEquals(1L,courseDTO.getId());
        Assertions.assertEquals("Java Course",courseDTO.getName());
        Assertions.assertEquals(2, courseDTO.getQuestionFormDTOset().size());
        Assertions.assertTrue(courseDTO.getQuestionFormDTOset().stream().anyMatch(dto -> dto.getId()==101L));
        Assertions.assertTrue(courseDTO.getQuestionFormDTOset().stream().anyMatch(dto -> dto.getId()==102L));



    }

    @Test
    void createCourse_shouldCreateCourse(){
        //Given
        CourseRequest request = new CourseRequest("Java Course");

        //When
        courseService.createCourse(request);

        //Then
        verify(courseRepository).save(any(Course.class));

    }

    @Test
    void deleteCourse_shouldDeleteCourse(){
        //Given
        Long courseId = 1L;
        Course course = new Course();
        course.setId(courseId);
        course.setName("Javva Course");


        when(courseRepository.existsById(courseId)).thenReturn(true);

        //When
        courseService.deleteCourse(courseId);

        //Then
        verify(courseRepository).deleteById(courseId);
    }

    @Test
    void updateCourse_shouldUpdateCourse(){
        //Given
        Long courseId = 1L;
        Course course = new Course();
        course.setId(courseId);
        course.setName("Java Course");
        CourseRequest request = new CourseRequest("C++ Course");

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        //When
        courseService.updateCourse(courseId,request);

        //Then
        Assertions.assertEquals("C++ Course",course.getName());

    }
}
