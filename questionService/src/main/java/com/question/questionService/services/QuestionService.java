package com.question.questionService.services;

import com.question.questionService.dao.QuestionDao;
import com.question.questionService.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<?> getAllQuestion(){
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No have any question",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?>getAllQuestionByCategory(String category){
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No any question with this category",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> addQuestion(Question question){
        questionDao.save(question);
        return new ResponseEntity<>("Question has been added successfully",HttpStatus.CREATED);
    }

    public ResponseEntity<?> getQuestionById(Integer id){
        try{
            return new ResponseEntity<>(questionDao.findById(id),HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity<>("Question is not found with this id"+id,HttpStatus.NOT_FOUND);
        }
    }
}
