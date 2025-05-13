package com.ExamZenith.ExamZenith;

import com.ExamZenith.ExamZenith.courses.model.AnswerOption.AnswerOptionDTO;
import com.ExamZenith.ExamZenith.courses.persistence.AnswerOption.AnswerOption;
import com.ExamZenith.ExamZenith.courses.persistence.AnswerOption.AnswerOptionRepository;
import com.ExamZenith.ExamZenith.courses.persistence.Question.Question;
import com.ExamZenith.ExamZenith.courses.service.AnswerOptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnswerOptionTest {
    @Mock
    AnswerOptionRepository answerOptionRepository;

    @InjectMocks
    AnswerOptionService answerOptionService;

    @Test
    void mapAnswerOption_shouldMapEntityAndReturnDTO(){
        //Given
        AnswerOption answerOption = new AnswerOption();
        Long optionId = 1L;
        String optionText = "Polymorphism";

        answerOption.setId(optionId);
        answerOption.setOption_text(optionText);

        Question question = new Question();
        question.setId(101L);

        answerOption.setQuestion(question);

        //When
        AnswerOptionDTO answerOptionDTO = answerOptionService.mapAnswerOption(answerOption);

        //Then
        Assertions.assertEquals(optionId, answerOptionDTO.getId());
        Assertions.assertEquals(optionText, answerOptionDTO.getOption_text());
        Assertions.assertEquals(question.getId(), answerOptionDTO.getQuestion_id());
    }

    @Test
    void getAnswerOptionById_shouldReturnAnswerOptionDTO(){
        //Given
        AnswerOption answerOption = new AnswerOption();
        Long optionId = 1L;
        String optionText = "Polymorphism";

        answerOption.setId(optionId);
        answerOption.setOption_text(optionText);

        Question question = new Question();
        question.setId(101L);

        answerOption.setQuestion(question);

        when(answerOptionRepository.findById(optionId)).thenReturn(Optional.of(answerOption));
        //When
        AnswerOptionDTO answerOptionDTO = answerOptionService.getAnswerOptionById(optionId);

        //Then
        Assertions.assertEquals(optionId, answerOptionDTO.getId());
        Assertions.assertEquals(optionText, answerOptionDTO.getOption_text());
        Assertions.assertEquals(question.getId(), answerOptionDTO.getQuestion_id());
    }
}
