package com.ExamZenith.ExamZenith.courses.service;


import com.ExamZenith.ExamZenith.courses.model.Question.QuestionDTO;
import com.ExamZenith.ExamZenith.courses.model.QuestionForm.QuestionFormDTO;
import com.ExamZenith.ExamZenith.courses.model.QuestionForm.QuestionFormRequest;
import com.ExamZenith.ExamZenith.courses.model.QuestionType;
import com.ExamZenith.ExamZenith.courses.persistence.AnswerOption.AnswerOption;
import com.ExamZenith.ExamZenith.courses.persistence.AnswerOption.AnswerOptionRepository;
import com.ExamZenith.ExamZenith.courses.persistence.Course.CourseRepository;
import com.ExamZenith.ExamZenith.courses.persistence.Question.Question;
import com.ExamZenith.ExamZenith.courses.persistence.Question.QuestionRepository;
import com.ExamZenith.ExamZenith.courses.persistence.QuestionForm.QuestionForm;
import com.ExamZenith.ExamZenith.courses.persistence.QuestionForm.QuestionFormRepository;
import com.ExamZenith.ExamZenith.errors.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class QuestionFormService {
    private final QuestionFormRepository questionFormRepository;
    private final CourseRepository courseRepository;
    private final AnswerOptionRepository answerOptionRepository;
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;


    public QuestionFormDTO mapQuestionForm(QuestionForm questionForm){
        Set<QuestionDTO> questionDTOSet = questionForm.getQuestions()
                .stream()
                .map(questionService::mapQuestion)
                .collect(Collectors.toSet());

        return new QuestionFormDTO(
                questionForm.getId(),
                questionForm.getCourse().getId(),
                questionForm.getTitle(),
                questionDTOSet
        );
    }

    @Transactional
    public void createQuestionForm(QuestionFormRequest request){
        QuestionForm questionForm = new QuestionForm();
        questionForm.setCourse(courseRepository.findById(request.getCourse_id())
                .orElseThrow(() -> new NotFoundException("Course Not Found")));
        questionForm.setTitle(request.getTitle());

        questionFormRepository.save(questionForm);


        Set<Question> questions = request.getQuestions().stream()
                .map(q -> {
                    Question question = new Question();
                    question.setQuestion_text(q.getQuestion_text());
                    question.setQuestionForm(questionForm);
                    question.setQuestion_type(q.getQuestion_type());

                    questionRepository.save(question);


                    if (q.getQuestion_type() == QuestionType.RADIO || q.getQuestion_type() == QuestionType.CHECKBOX) {
                        Set<AnswerOption> answerOptions = q.getOptions().stream()
                                .map(optionRequest  -> {
                                    AnswerOption option = new AnswerOption();
                                    option.setOption_text(optionRequest.getOption_text());
                                    option.setQuestion(question);

                                    answerOptionRepository.save(option);
                                    return option;
                                })
                                .collect(Collectors.toSet());
                        question.setOptions(answerOptions);
                    }

                    return question;
                })
                .collect(Collectors.toSet());
        questionForm.setQuestions(questions);
        questionFormRepository.save(questionForm);
    }

    public void deleteQuestionForm(Long questionFormId){
        QuestionForm questionForm = questionFormRepository.findById(questionFormId)
                .orElseThrow(() -> new NotFoundException("Question Form Not Found With Id: "+questionFormId));
        questionRepository.deleteById(questionFormId);
    }
}
