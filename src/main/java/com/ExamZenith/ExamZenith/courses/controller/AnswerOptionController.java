package com.ExamZenith.ExamZenith.courses.controller;


import com.ExamZenith.ExamZenith.courses.model.AnswerOption.AnswerOptionRequest;
import com.ExamZenith.ExamZenith.courses.service.AnswerOptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/answer-option")
public class AnswerOptionController {
    private final AnswerOptionService answerOptionService;

    @PostMapping
    public void createAnswerOption(@RequestBody AnswerOptionRequest request){
        answerOptionService.createAnswerOption(request);
    }

}
