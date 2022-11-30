package com.iiitb.deleteservice.service;


import com.iiitb.deleteservice.model.Question;
import com.iiitb.deleteservice.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@RequiredArgsConstructor
public class DeleteService{


    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
    }
}
