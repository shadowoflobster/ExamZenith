package com.ExamZenith.ExamZenith;


import com.ExamZenith.ExamZenith.courses.model.Question.QuestionDTO;
import com.ExamZenith.ExamZenith.courses.model.QuestionForm.QuestionFormDTO;
import com.ExamZenith.ExamZenith.courses.model.QuestionForm.QuestionFormRequest;
import com.ExamZenith.ExamZenith.courses.persistence.AnswerOption.AnswerOptionRepository;
import com.ExamZenith.ExamZenith.courses.persistence.Course.Course;
import com.ExamZenith.ExamZenith.courses.persistence.Course.CourseRepository;
import com.ExamZenith.ExamZenith.courses.persistence.Question.Question;
import com.ExamZenith.ExamZenith.courses.persistence.Question.QuestionRepository;
import com.ExamZenith.ExamZenith.courses.persistence.QuestionForm.QuestionForm;
import com.ExamZenith.ExamZenith.courses.persistence.QuestionForm.QuestionFormRepository;
import com.ExamZenith.ExamZenith.courses.service.QuestionFormService;
import com.ExamZenith.ExamZenith.courses.service.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionFormServiceTest {
    @Mock
    private QuestionFormRepository questionFormRepository;

    @Mock
    private QuestionService questionService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerOptionRepository answerOptionRepository;

    @InjectMocks
    private QuestionFormService questionFormService;

    @Test
    void getQuestionFormById(){
        //Given
        Long questionFormId = 101L;
        QuestionForm questionForm = new QuestionForm();
        questionForm.setId(101L);
        questionForm.setTitle("OOP principles");

        Course course = new Course();
        course.setId(1L);
        questionForm.setCourse(course);

        Question question1 = new Question();
        question1.setId(201L);

        Question question2 = new Question();
        question2.setId(202L);

        Set<Question> questions = Set.of(question1,question2);
        questionForm.setQuestions(questions);

        QuestionDTO question1DTO = new QuestionDTO();
        question1DTO.setId(201L);

        QuestionDTO question2DTO = new QuestionDTO();
        question2DTO.setId(202L);

        when(questionService.mapQuestion(question1)).thenReturn(question1DTO);
        when(questionService.mapQuestion(question2)).thenReturn(question2DTO);
        when(questionFormRepository.findById(questionFormId)).thenReturn(Optional.of(questionForm));

        //When
        QuestionFormDTO questionFormDTO = questionFormService.getQuestionFormById(questionFormId);

        //Then
        Assertions.assertEquals(101L,questionFormDTO.getId());
        Assertions.assertEquals("OOP principles",questionFormDTO.getTitle());
        Assertions.assertEquals(1L,questionFormDTO.getCourse_id());
        Assertions.assertTrue(questionFormDTO.getQuestionDTOSet().stream().anyMatch(dto -> dto.getId()==201L));
        Assertions.assertTrue(questionFormDTO.getQuestionDTOSet().stream().anyMatch(dto -> dto.getId()==202L));

    }


    @Test
    void mapQuestionForms(){
        //Given
        Long questionFormId = 101L;
        QuestionForm questionForm = new QuestionForm();
        questionForm.setId(101L);
        questionForm.setTitle("OOP principles");

        Course course = new Course();
        course.setId(1L);
        questionForm.setCourse(course);

        Question question1 = new Question();
        question1.setId(201L);

        Question question2 = new Question();
        question2.setId(202L);

        Set<Question> questions = Set.of(question1,question2);
        questionForm.setQuestions(questions);

        QuestionDTO question1DTO = new QuestionDTO();
        question1DTO.setId(201L);

        QuestionDTO question2DTO = new QuestionDTO();
        question2DTO.setId(202L);

        when(questionService.mapQuestion(question1)).thenReturn(question1DTO);
        when(questionService.mapQuestion(question2)).thenReturn(question2DTO);

        //When
        QuestionFormDTO questionFormDTO = questionFormService.mapQuestionForm(questionForm);

        //Then
        Assertions.assertEquals(101L,questionFormDTO.getId());
        Assertions.assertEquals("OOP principles",questionFormDTO.getTitle());
        Assertions.assertEquals(1L,questionFormDTO.getCourse_id());
        Assertions.assertTrue(questionFormDTO.getQuestionDTOSet().stream().anyMatch(dto -> dto.getId()==201L));
        Assertions.assertTrue(questionFormDTO.getQuestionDTOSet().stream().anyMatch(dto -> dto.getId()==202L));
    }

    @Test
    void createQuestionForm_ShouldCreateCourseFromRequest(){
        //Given
        QuestionFormRequest request = new QuestionFormRequest();
        Course course = new Course();

        course.setId(1L);
        course.setName("OOP");

        request.setCourse_id(course.getId());
        request.setTitle("OOP principles");

        when(courseRepository.findById(request.getCourse_id())).thenReturn(Optional.of(course));

        //When
        questionFormService.createQuestionForm(request);

        //Then
        verify(questionFormRepository).save(any(QuestionForm.class));
        }



}
