package com.ExamZenith.ExamZenith.courses.controller;

import com.ExamZenith.ExamZenith.courses.model.UserQuestionAnswers.UserQuestionAnswersDTO;
import com.ExamZenith.ExamZenith.courses.model.UserQuestionAnswers.UserQuestionAnswersRequest;
import com.ExamZenith.ExamZenith.courses.service.UserQuestionAnswersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user-answer")
@AllArgsConstructor
public class UserQuestionAnswersController {
    private UserQuestionAnswersService service;

    @GetMapping("/{userId},{questionId}")
    public ResponseEntity<UserQuestionAnswersDTO> getUserAnswer(@PathVariable Long userId,@PathVariable Long questionId){
        UserQuestionAnswersDTO data =  service.findByKey(userId, questionId);
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<String> createUserAnswer(@RequestBody UserQuestionAnswersRequest request){
        service.createUserQuestionAnswersService(request);
        return  ResponseEntity.ok("Answer submitted successfully");
    }
}
