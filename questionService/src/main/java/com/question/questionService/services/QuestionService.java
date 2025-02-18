package com.question.questionService.services;

import com.question.questionService.dao.QuestionDao;
import com.question.questionService.model.Question;
import com.question.questionService.model.QuestionWrapper;
import com.question.questionService.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
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

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer noOfQuestion) {
        List<Integer>questionList=questionDao.findRandomQuestionByCategory(category,noOfQuestion);
        return new ResponseEntity<>(questionList,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionId) {
        List<QuestionWrapper>wrapperList=new ArrayList<>();
        List<Question>questions=new ArrayList<>();

        for(Integer id:questionId){
            questions.add(questionDao.getById(id));;
        }
        for(Question question:questions){
            QuestionWrapper wrapper=new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrapperList.add(wrapper);
        }

        return new ResponseEntity<>(wrapperList,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right=0;
        for(Response response:responses){
            Question question=questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getCorrectAns())){
                right++;
            }
        }
        return new ResponseEntity(right,HttpStatus.OK);
    }
}
