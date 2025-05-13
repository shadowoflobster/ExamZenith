package com.ExamZenith.ExamZenith.courses.service;


import com.ExamZenith.ExamZenith.courses.model.AnswerOption.AnswerOptionDTO;
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


    public AnswerOptionDTO mapAnswerOption(AnswerOption answerOption){
        return new AnswerOptionDTO(
                answerOption.getId(),
                answerOption.getQuestion().getId(),
                answerOption.getOption_text()
        );
    }

    public AnswerOptionDTO getAnswerOptionById(Long optionId) {
        AnswerOption answerOption = answerOptionRepository.findById(optionId)
                .orElseThrow(() -> buildNotFoundException(optionId));
        if (answerOption != null) {
            return mapAnswerOption(answerOption);
        }
        return null;
    }

    public void createAnswerOption(AnswerOptionRequest request){
        AnswerOption answerOption = new AnswerOption();
        Question question = questionRepository.findById(request.getQuestion_id())
                        .orElseThrow(() -> new NotFoundException("Not Question found with id:"+request.getQuestion_id()));

        answerOption.setOption_text(request.getOption_text());
        answerOption.setQuestion(question);

        answerOptionRepository.save(answerOption);
    }

    public void deleteAnswerOption(Long id){
        AnswerOption answerOption = answerOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Answer Option found with id:"+id));

        answerOptionRepository.deleteById(id);
    }

    private NotFoundException buildNotFoundException(Long id){
        return new NotFoundException("Answer Option with id "+id+" not found");
    }
}
