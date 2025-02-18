package com.question.questionService.controller;

import com.question.questionService.model.Question;
import com.question.questionService.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllQuestion(){
        return questionService.getAllQuestion();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?>getQuestionByCategory(@PathVariable String category){
        return questionService.getAllQuestionByCategory(category);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        return questionService.getQuestionById(id);
    }
}
