package com.ExamZenith.ExamZenith.courses.service;


import com.ExamZenith.ExamZenith.courses.model.Question.QuestionDTO;
import com.ExamZenith.ExamZenith.courses.model.QuestionForm.QuestionFormDTO;
import com.ExamZenith.ExamZenith.courses.model.QuestionForm.QuestionFormRequest;
import com.ExamZenith.ExamZenith.courses.persistence.AnswerOption.AnswerOptionRepository;
import com.ExamZenith.ExamZenith.courses.persistence.Course.CourseRepository;
import com.ExamZenith.ExamZenith.courses.persistence.Question.QuestionRepository;
import com.ExamZenith.ExamZenith.courses.persistence.QuestionForm.QuestionForm;
import com.ExamZenith.ExamZenith.courses.persistence.QuestionForm.QuestionFormRepository;
import com.ExamZenith.ExamZenith.errors.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class QuestionFormService {
    private final QuestionFormRepository questionFormRepository;
    private final CourseRepository courseRepository;
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;


    public QuestionFormDTO mapQuestionForm(QuestionForm questionForm){
        Set<QuestionDTO> questionDTOSet = questionForm.getQuestions()
                .stream()
                .map(questionService::mapQuestion)
                .collect(Collectors.toSet());

        return new QuestionFormDTO(
                questionForm.getId(),
                questionForm.getCourse().getId(),
                questionForm.getTitle(),
                questionDTOSet
        );
    }

    public QuestionFormDTO getQuestionFormById(Long id){
        QuestionForm questionForm = questionFormRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No QuestionForm Found With Id: "+id));
        return mapQuestionForm(questionForm);
    }


    @Transactional
    public void createQuestionForm(QuestionFormRequest request){
        QuestionForm questionForm = new QuestionForm();
        questionForm.setCourse(courseRepository.findById(request.getCourse_id())
                .orElseThrow(() -> new NotFoundException("Course Not Found")));
        questionForm.setTitle(request.getTitle());

        questionFormRepository.save(questionForm);
    }

    public void deleteQuestionForm(Long questionFormId){
        QuestionForm questionForm = questionFormRepository.findById(questionFormId)
                .orElseThrow(() -> new NotFoundException("Question Form Not Found With Id: "+questionFormId));
        questionRepository.deleteById(questionFormId);
    }
}
