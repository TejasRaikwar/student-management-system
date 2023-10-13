package com.example.studentmanagentsystem.entity.repository;

import com.example.studentmanagentsystem.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer>
{
}
