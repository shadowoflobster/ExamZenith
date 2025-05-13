package com.ExamZenith.ExamZenith;


import com.ExamZenith.ExamZenith.courses.model.AnswerOption.AnswerOptionDTO;
import com.ExamZenith.ExamZenith.courses.model.Question.QuestionDTO;
import com.ExamZenith.ExamZenith.courses.model.Question.QuestionRequest;
import com.ExamZenith.ExamZenith.courses.model.QuestionType;
import com.ExamZenith.ExamZenith.courses.persistence.AnswerOption.AnswerOption;
import com.ExamZenith.ExamZenith.courses.persistence.Question.Question;
import com.ExamZenith.ExamZenith.courses.persistence.Question.QuestionRepository;
import com.ExamZenith.ExamZenith.courses.persistence.QuestionForm.QuestionForm;
import com.ExamZenith.ExamZenith.courses.persistence.QuestionForm.QuestionFormRepository;
import com.ExamZenith.ExamZenith.courses.service.AnswerOptionService;
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
public class QuestionServiceTest {
    @Mock
    QuestionRepository questionRepository;

    @Mock
    AnswerOptionService answerOptionService;

    @Mock
    QuestionFormRepository questionFormRepository;

    @InjectMocks
    QuestionService questionService;

    @Test
    void mapQuestion_shouldReturnCorrectQuestionDTO(){
        //Given
        Long questionId = 1L;
        String questionText = "What are four basic OOP principles?";
        QuestionType questionType = QuestionType.RADIO;

        Question question = new Question();

        question.setId(1L);
        question.setQuestion_text(questionText);
        question.setQuestion_type(questionType);

        QuestionForm questionForm = new QuestionForm();
        questionForm.setId(201L);

        question.setQuestionForm(questionForm);

        AnswerOption answerOption1 = new AnswerOption();
        answerOption1.setId(101L);

        AnswerOption answerOption2 = new AnswerOption();
        answerOption2.setId(102L);



        Set<AnswerOption> answerOptions = Set.of(answerOption1,answerOption2);
        question.setOptions(answerOptions);

        AnswerOptionDTO answerOption1DTO = new AnswerOptionDTO();
        answerOption1DTO.setId(101L);

        AnswerOptionDTO answerOption2DTO = new AnswerOptionDTO();
        answerOption2DTO.setId(102L);


        when(answerOptionService.mapAnswerOption(answerOption1)).thenReturn(answerOption1DTO);
        when(answerOptionService.mapAnswerOption(answerOption2)).thenReturn(answerOption2DTO);

        //When
        QuestionDTO questionDTO = questionService.mapQuestion(question);

        //Then
        Assertions.assertEquals(1L,questionDTO.getId());
        Assertions.assertEquals("What are four basic OOP principles?",questionDTO.getQuestion_text());
        Assertions.assertEquals(QuestionType.RADIO,questionDTO.getQuestion_type());
        Assertions.assertEquals(201L,questionDTO.getQuestion_form_id());
        Assertions.assertTrue(questionDTO.getAnswerOptionDTOSet().stream().anyMatch(dto -> dto.getId()==101L));
        Assertions.assertTrue(questionDTO.getAnswerOptionDTOSet().stream().anyMatch(dto -> dto.getId()==102L));

    }

    @Test
    void getQuestion_shouldReturnQuestionDTO(){
        //Given
        Long questionId = 1L;
        String questionText = "What are four basic OOP principles?";
        QuestionType questionType = QuestionType.RADIO;

        Question question = new Question();

        question.setId(1L);
        question.setQuestion_text(questionText);
        question.setQuestion_type(questionType);

        QuestionForm questionForm = new QuestionForm();
        questionForm.setId(201L);

        question.setQuestionForm(questionForm);

        AnswerOption answerOption1 = new AnswerOption();
        answerOption1.setId(101L);

        AnswerOption answerOption2 = new AnswerOption();
        answerOption2.setId(102L);



        Set<AnswerOption> answerOptions = Set.of(answerOption1,answerOption2);
        question.setOptions(answerOptions);

        AnswerOptionDTO answerOption1DTO = new AnswerOptionDTO();
        answerOption1DTO.setId(101L);

        AnswerOptionDTO answerOption2DTO = new AnswerOptionDTO();
        answerOption2DTO.setId(102L);


        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));
        when(answerOptionService.mapAnswerOption(answerOption1)).thenReturn(answerOption1DTO);
        when(answerOptionService.mapAnswerOption(answerOption2)).thenReturn(answerOption2DTO);

        //When
        QuestionDTO questionDTO = questionService.getQuestion(questionId);

        //Then
        Assertions.assertEquals(1L,questionDTO.getId());
        Assertions.assertEquals("What are four basic OOP principles?",questionDTO.getQuestion_text());
        Assertions.assertEquals(QuestionType.RADIO,questionDTO.getQuestion_type());
        Assertions.assertEquals(201L,questionDTO.getQuestion_form_id());
        Assertions.assertTrue(questionDTO.getAnswerOptionDTOSet().stream().anyMatch(dto -> dto.getId()==101L));
        Assertions.assertTrue(questionDTO.getAnswerOptionDTOSet().stream().anyMatch(dto -> dto.getId()==102L));

    }

    @Test
    void createQuestion_shouldCreateQuestion(){
        //Given
        QuestionRequest request = new QuestionRequest();
        String questionText = "What are four basic OOP principles?";
        QuestionType questionType = QuestionType.RADIO;
        Long formId = 101L;
        request.setQuestion_text(questionText);
        request.setQuestion_type(questionType);
        request.setQuestion_form_id(formId);

        QuestionForm form = new QuestionForm();
        form.setId(formId);

        when(questionFormRepository.findById(request.getQuestion_form_id())).thenReturn(Optional.of(form));
        //when
        questionService.createQuestion(request);

        //Then
        verify(questionRepository).save(any(Question.class));
    }

    @Test
    void deleteQuestion_shouldDeleteQuestion(){
        //Given
        Question question = new Question();
        Long id = 1L;
        question.setId(id);
        question.setQuestion_text("What are four basic OOP principles");

        when(questionRepository.findById(id)).thenReturn(Optional.of(question));

        //When
        questionService.deleteQuestion(id);

        //Then
        verify(questionRepository).deleteById(id);
    }



}
