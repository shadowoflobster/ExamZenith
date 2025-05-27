package com.ExamZenith.ExamZenith.courses.service;

import com.ExamZenith.ExamZenith.courses.model.AnswerOption.AnswerOptionDTO;
import com.ExamZenith.ExamZenith.courses.model.UserQuestionAnswers.UserQuestionAnswersDTO;
import com.ExamZenith.ExamZenith.courses.model.UserQuestionAnswers.UserQuestionAnswersRequest;
import com.ExamZenith.ExamZenith.courses.persistence.AnswerOption.AnswerOption;
import com.ExamZenith.ExamZenith.courses.persistence.AnswerOption.AnswerOptionRepository;
import com.ExamZenith.ExamZenith.courses.persistence.Question.QuestionRepository;
import com.ExamZenith.ExamZenith.courses.persistence.UserQuestionAnswers.UserQuestionAnswers;
import com.ExamZenith.ExamZenith.courses.persistence.UserQuestionAnswers.UserQuestionAnswersKey;
import com.ExamZenith.ExamZenith.courses.persistence.UserQuestionAnswers.UserQuestionAnswersRepository;
import com.ExamZenith.ExamZenith.errors.NotFoundException;
import com.ExamZenith.ExamZenith.users.persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserQuestionAnswersService {
    private UserQuestionAnswersRepository repository;
    private UserRepository userRepository;
    private QuestionRepository questionRepository;
    private AnswerOptionRepository answerOptionRepository;

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

    @Transactional
    public void createUserQuestionAnswersService(UserQuestionAnswersRequest request){
        UserQuestionAnswersKey key = new UserQuestionAnswersKey(request.getStudentId(), request.getQuestionId());
        UserQuestionAnswers entity = new UserQuestionAnswers();
        entity.setStudent(userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new NotFoundException("User not found with id: " + request.getStudentId())));
        entity.setQuestion(questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new NotFoundException("Question not found with id: " + request.getQuestionId())));

        if(request.getAnswerOptionId() != null){
            AnswerOption answerOption = answerOptionRepository.findById(request.getAnswerOptionId())
                    .orElseThrow(() -> new NotFoundException("Answer Option not found with id: "+ request.getAnswerOptionId()));
            entity.setAnswerOption(answerOption);
        }

        if(request.getAnswerText() != null){
            entity.setAnswerText(request.getAnswerText());
        }

        repository.save(entity);

    }



}
