package com.example.studentmanagentsystem.entity.repository;

import com.example.studentmanagentsystem.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer>
{
}

