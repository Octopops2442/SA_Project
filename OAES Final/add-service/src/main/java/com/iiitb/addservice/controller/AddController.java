package com.iiitb.addservice.controller;

import com.iiitb.addservice.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.iiitb.addservice.service.AddService;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AddController{

    private final AddService addService;

    @PostMapping(path="/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String AddQuestion(@RequestBody Question question){
        addService.AddQuestion(question);
        return "Added Question successfully\n";
    }
}
