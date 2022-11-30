package com.iiitb.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.iiitb.userservice.model.Question;
import com.iiitb.userservice.model.User;
import com.iiitb.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();
    private final UserService userService;

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody User user){
//        User user = new User();
//
//        user.setUsername(username);
        userService.addUser(user);

        return "Added user successfully\n";
    }

    @PostMapping(path = "/getQuestions")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Question> getQuestions(@RequestBody User user){
        List<Question> questions = userService.getQuestions(user);
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(Question q:questions){
            sb.append(" Question: ");
            sb.append(q.getQuestion());
            sb.append("\n Question id: ");
            sb.append(q.getQID());
            sb.append("\n User id: ");
            sb.append(q.getUser_id());
            sb.append("\n Answer: ");
            sb.append(q.getAnswer());
            sb.append("\n Options:  ");
            sb.append(q.getOption1());
            sb.append(" ");
            sb.append(q.getOption2());
            sb.append(" ");
            sb.append(q.getOption3());
            sb.append(" ");
            sb.append(q.getOption4());
            sb.append("\n Version:");
            sb.append(q.getVersion());
            sb.append("\n Correct Option: ");
            sb.append(q.getCorrectOption());
            sb.append("\n");
        }
        sb.append("}");
        System.out.println(sb.toString());

        return questions;
//        return sb.toString();
    }

    @GetMapping(path = "/listenToQuestions")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String getQuestions(){
        return "";
    }


    @PostMapping(path = "/firebaseAddU")
    @ResponseStatus(HttpStatus.CREATED)
    public String firebaseAddU(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.firebaseAddU(user);
    }

    @PostMapping(path = "/firebaseGetQ")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Question> firebaseGetQ(@RequestBody User user) throws ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> qds = userService.firebaseGetQ(user);

        System.out.println(qds);

        List<Question> questions = new ArrayList<Question>();

        for(DocumentSnapshot doc: qds){
            questions.add(doc.toObject(Question.class));
        }

        System.out.print("Questions: ");
        System.out.println(questions);

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(Question q: questions){
            sb.append(" Question: ");
            sb.append(q.getQuestion());
            sb.append("\n Question id: ");
            sb.append(q.getQID());
            sb.append("\n User id: ");
            sb.append(q.getUser_id());
            sb.append("\n Answer: ");
            sb.append(q.getAnswer());
            sb.append("\n Options:  ");
            sb.append(q.getOption1());
            sb.append(" ");
            sb.append(q.getOption2());
            sb.append(" ");
            sb.append(q.getOption3());
            sb.append(" ");
            sb.append(q.getOption4());
            sb.append("\n Version:");
            sb.append(q.getVersion());
            sb.append("\n Correct Option: ");
            sb.append(q.getCorrectOption());
            sb.append("\n");
        }
        sb.append("}");
        System.out.println(sb.toString());

        return questions;
    }
}


