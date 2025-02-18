package com.question.questionService.dao;

import com.question.questionService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question,Integer> {

   List<Question> findByCategory(String category);

//   List<Question> findRandomQuestionByCategory(String category,int numQ);
}
