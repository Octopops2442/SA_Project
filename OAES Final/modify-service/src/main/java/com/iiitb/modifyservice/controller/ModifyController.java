package com.iiitb.modifyservice.controller;

import com.iiitb.modifyservice.model.Question;
import com.iiitb.modifyservice.service.ModifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ModifyController{

    private final ModifyService modifyService;

    @PostMapping(path="/modify")
    @ResponseStatus(HttpStatus.CREATED)
    public String modifyQuestion(@RequestBody Question question){
        modifyService.placeOrder(question);
        return "Modified Question successfully\n";
    }
}
