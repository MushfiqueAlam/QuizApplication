package com.question.questionService.dao;

import com.question.questionService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question,Integer> {

   List<Question> findByCategory(String category);

   @Query(value = "SELECT q.id FROM Question q where q.category=:category ORDER BY RAND() LIMIT :noOfQuestion", nativeQuery = true)
  List<Integer> findRandomQuestionByCategory(String category,int noOfQuestion);
}
