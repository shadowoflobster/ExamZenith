package com.ExamZenith.ExamZenith.courses.service;

import com.ExamZenith.ExamZenith.courses.model.AnswerOption.AnswerOptionDTO;
import com.ExamZenith.ExamZenith.courses.model.Question.QuestionDTO;
import com.ExamZenith.ExamZenith.courses.model.Question.QuestionRequest;
import com.ExamZenith.ExamZenith.courses.persistence.Question.Question;
import com.ExamZenith.ExamZenith.courses.persistence.QuestionForm.QuestionFormRepository;
import com.ExamZenith.ExamZenith.courses.persistence.Question.QuestionRepository;
import com.ExamZenith.ExamZenith.errors.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionFormRepository questionFormRepository;
    private final AnswerOptionService answerOptionService;

    public QuestionDTO mapQuestion(Question question) {
        Set<AnswerOptionDTO> answerOptionDTOS= question.getOptions()
                .stream()
                .map(answerOptionService::mapAnswerOption)
                .collect(Collectors.toSet());

        return new QuestionDTO(
                question.getId(),
                question.getQuestionForm().getId(),
                question.getQuestion_text(),
                question.getQuestion_type(),
                answerOptionDTOS
        );
    }

    public QuestionDTO getQuestion(Long id){
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question not found with id: " + id));
        if(question != null){
            return mapQuestion(question);
        }
        return null;
    }

    @Transactional
    public void createQuestion(QuestionRequest request){
        Question question = new Question();
        question.setQuestionForm(questionFormRepository.findById(request.getQuestion_form_id())
                .orElseThrow(() -> new NotFoundException("QuestionForm not found")));
        question.setQuestion_text(request.getQuestion_text());
        question.setQuestion_type(request.getQuestion_type());
        questionRepository.save(question);
    }

    @Transactional
    public void deleteQuestion(Long questionId){
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question Not Found"));
        questionRepository.deleteById(questionId);
    }

}
