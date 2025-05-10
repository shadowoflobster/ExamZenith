package com.ExamZenith.ExamZenith.courses.controller;

import com.ExamZenith.ExamZenith.courses.model.QuestionForm.QuestionFormDTO;
import com.ExamZenith.ExamZenith.courses.service.QuestionFormService;
import com.ExamZenith.ExamZenith.courses.model.QuestionForm.QuestionFormRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question-form")
@AllArgsConstructor
public class QuestionFormController {
    private final QuestionFormService service;

    @GetMapping("/{questionForm_id}")
    public ResponseEntity<QuestionFormDTO> getQuestionFormById(@PathVariable Long questionForm_id){
        QuestionFormDTO questionFormDTO= service.getQuestionFormById(questionForm_id);
        return ResponseEntity.ok(questionFormDTO);
    }


    @PostMapping
    public void createQuestionForm(@RequestBody QuestionFormRequest request){
        service.createQuestionForm(request);
    }

    @DeleteMapping("/{questionForm_id}")
    public void deleteQuestionForm(@PathVariable Long questionForm_id){
        service.deleteQuestionForm(questionForm_id);
    }
}
