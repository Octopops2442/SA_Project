package com.iiitb.modifyservice.service;

import com.iiitb.modifyservice.model.Question;
import com.iiitb.modifyservice.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@RequiredArgsConstructor
public class ModifyService{


    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    public void placeOrder(Question question) {
        questionRepository.save(question);
    }
}
