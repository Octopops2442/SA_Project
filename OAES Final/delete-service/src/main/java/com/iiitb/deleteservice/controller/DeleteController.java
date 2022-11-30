package com.iiitb.deleteservice.controller;

import com.iiitb.deleteservice.model.Question;
import com.iiitb.deleteservice.service.DeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DeleteController{

    private final DeleteService deleteService;

    @PostMapping(path="/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteQuestion(@RequestBody Question question){
        deleteService.deleteQuestion(question);
        return "Deleted Question successfully\n";
    }
}
