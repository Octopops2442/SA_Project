package com.iiitb.addservice.repository;

import com.iiitb.addservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends  JpaRepository<Question, Integer> {
}
