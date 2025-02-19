package com.question.quizService.services;

import com.question.quizService.dao.QuizDao;
import com.question.quizService.feign.QuizInterface;
import com.question.quizService.model.QuestionWrapper;
import com.question.quizService.model.Quiz;
import com.question.quizService.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category,Integer noOfQuestion,String title){
        List<Integer>questions=quizInterface.getQuestionForQuiz(category,noOfQuestion).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id){
        Quiz quiz=quizDao.findById(id).get();

        List<Integer>questionIds=quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestionFromId(questionIds);
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer>result=quizInterface.getScore(responses);

        return result;
    }

}
