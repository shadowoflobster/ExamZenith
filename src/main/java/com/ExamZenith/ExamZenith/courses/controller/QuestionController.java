package com.ExamZenith.ExamZenith.courses.controller;


import com.ExamZenith.ExamZenith.courses.model.Question.QuestionRequest;
import com.ExamZenith.ExamZenith.courses.persistence.Question.QuestionRepository;
import com.ExamZenith.ExamZenith.courses.persistence.QuestionForm.QuestionForm;
import com.ExamZenith.ExamZenith.courses.service.QuestionFormService;
import com.ExamZenith.ExamZenith.courses.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("question")
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public void createQuestion(@RequestBody QuestionRequest request){
        questionService.createQuestion(request);
    }

    @DeleteMapping("/{question_id}")
    public void deleteQuestion(@PathVariable Long question_id){
        questionService.deleteQuestion(question_id);
    }
}
