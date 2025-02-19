package com.question.quizService.controller;

import com.question.quizService.model.QuestionWrapper;
import com.question.quizService.model.QuizDtoCon;
import com.question.quizService.model.Response;
import com.question.quizService.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDtoCon quizDtoCon){
        return quizService.createQuiz(quizDtoCon.getCategory(),quizDtoCon.getNoOfQuestion(),quizDtoCon.getTitle());
    }

    @GetMapping("/getQuestion/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer>submitQuiz(@PathVariable Integer id, @RequestBody List<Response>responses){
        return quizService.calculateResult(id,responses);
    }
}
