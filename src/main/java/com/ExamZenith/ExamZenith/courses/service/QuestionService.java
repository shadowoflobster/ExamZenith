package com.ExamZenith.ExamZenith.courses.service;

import com.ExamZenith.ExamZenith.courses.model.Question.QuestionRequest;
import com.ExamZenith.ExamZenith.courses.persistence.Question.Question;
import com.ExamZenith.ExamZenith.courses.persistence.QuestionForm.QuestionFormRepository;
import com.ExamZenith.ExamZenith.courses.persistence.Question.QuestionRepository;
import com.ExamZenith.ExamZenith.errors.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionFormRepository questionFormRepository;


    public void createQuestion(QuestionRequest request){
        Question question = new Question();
        question.setQuestionForm(questionFormRepository.findById(request.getQuestion_form_id())
                .orElseThrow(() -> new NotFoundException("QuestionForm not found")));
        question.setQuestion_text(request.getQuestion_text());
        question.setQuestion_type(request.getQuestion_type());
        questionRepository.save(question);
    }

    public void deleteQuestion(Long questionId){
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question Not Found"));
        questionRepository.deleteById(questionId);
    }

}
