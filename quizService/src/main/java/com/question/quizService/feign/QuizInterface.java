package com.question.quizService.feign;

import com.question.quizService.model.QuestionWrapper;
import com.question.quizService.model.Response;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTIONSERVICE")
public interface QuizInterface {

    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category, @RequestParam Integer noOfQuestion);

    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>>getQuestionFromId(@RequestBody List<Integer>questionId);


    @PostMapping("/question/getScore")
    public ResponseEntity<Integer>getScore(@RequestBody List<Response> responses);
}
