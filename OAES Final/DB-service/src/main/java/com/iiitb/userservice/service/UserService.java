package com.iiitb.userservice.service;

import com.iiitb.userservice.model.Question;
import com.iiitb.userservice.model.User;
import com.iiitb.userservice.repository.QuestionRepository;
import com.iiitb.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class UserService{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final QuestionRepository questionRepository;
    @Autowired
    public void addUser(User user){
        userRepository.save(user);
    }

    @Autowired
    public List<Question> getQuestions(User user){
//        return userRepository.findAllQuestions(user.getUid());
        List<Question> qList = questionRepository.findAll();
        List<Question> result = new ArrayList<>();

        for (Question q: qList) {
            if (q.getUser_id() == user.getUser_id()) {
                result.add(q);
            }
        }
        return result;
    }

}
