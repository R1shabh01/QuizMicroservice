package com.example.questionService.Repositories;

import com.example.questionService.Model.Ques;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuesRepo extends JpaRepository<Ques , Integer> {
    List<Ques> findAllByCategory(String category);

    @Query(value = "Select * From ques q WHERE q.category =:category ORDER BY RANDOM() LIMIT :num",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(@Param("category") String category, @Param("num") int num);
}
