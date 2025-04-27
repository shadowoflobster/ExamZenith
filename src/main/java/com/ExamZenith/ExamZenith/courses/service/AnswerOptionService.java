package com.ExamZenith.ExamZenith.courses.service;


import com.ExamZenith.ExamZenith.courses.model.AnswerOption.AnswerOptionRequest;
import com.ExamZenith.ExamZenith.courses.persistence.AnswerOption.AnswerOption;
import com.ExamZenith.ExamZenith.courses.persistence.AnswerOption.AnswerOptionRepository;
import com.ExamZenith.ExamZenith.courses.persistence.Question.Question;
import com.ExamZenith.ExamZenith.courses.persistence.Question.QuestionRepository;
import com.ExamZenith.ExamZenith.errors.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnswerOptionService {
    private final AnswerOptionRepository answerOptionRepository;
    private final QuestionRepository questionRepository;

    public void createAnswerOption(AnswerOptionRequest request){
        AnswerOption answerOption = new AnswerOption();
        Question question = questionRepository.findById(request.getQuestion_id())
                        .orElseThrow(() -> new NotFoundException("Not Question found with id:"+request.getQuestion_id()));

        answerOption.setOption_text(request.getOption_text());
        answerOption.setQuestion(question);

        answerOptionRepository.save(answerOption);
    }

}
