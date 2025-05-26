package com.ExamZenith.ExamZenith.courses.service;

import com.ExamZenith.ExamZenith.courses.model.AnswerOption.AnswerOptionDTO;
import com.ExamZenith.ExamZenith.courses.model.UserQuestionAnswers.UserQuestionAnswersDTO;
import com.ExamZenith.ExamZenith.courses.persistence.UserQuestionAnswers.UserQuestionAnswers;
import com.ExamZenith.ExamZenith.courses.persistence.UserQuestionAnswers.UserQuestionAnswersKey;
import com.ExamZenith.ExamZenith.courses.persistence.UserQuestionAnswers.UserQuestionAnswersRepository;
import com.ExamZenith.ExamZenith.errors.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserQuestionAnswersService {
    private UserQuestionAnswersRepository repository;

    public UserQuestionAnswersDTO mapUserQuestionAnswers(UserQuestionAnswers userQuestionAnswers){
        return new UserQuestionAnswersDTO(
                userQuestionAnswers.getStudent().getId(),
                userQuestionAnswers.getQuestion().getId(),
                userQuestionAnswers.getAnswerOption() != null
                        ? userQuestionAnswers.getAnswerOption().getId()
                        : null,
                userQuestionAnswers.getAnswerText(),
                userQuestionAnswers.getAnsweredAt()

        );
    }

    public UserQuestionAnswersDTO findByKey(Long studentId, Long questionId){
        UserQuestionAnswersKey key = new UserQuestionAnswersKey(studentId, questionId);
        UserQuestionAnswers entity = repository.findById(key)
                .orElseThrow(() -> new NotFoundException("Answer record not found for user: " + studentId + " with question with id: "+ questionId));
        if(entity != null){
            return mapUserQuestionAnswers(entity);
        }
        return null;
    }


}
