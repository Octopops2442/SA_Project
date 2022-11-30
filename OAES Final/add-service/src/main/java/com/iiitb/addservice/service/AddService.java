package com.iiitb.addservice.service;

import com.iiitb.addservice.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.iiitb.addservice.repository.QuestionRepository;

@Service
@Component
@RequiredArgsConstructor
public class AddService{
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    public void AddQuestion(Question question) {
        questionRepository.save(question);
    }
}
