package com.example.studentmanagentsystem.entity.repository;

import com.example.studentmanagentsystem.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer>
{
    Instructor findByEmail(String email);
    
    @Query("SELECT i FROM Instructor i WHERE i.course = :courseId")
    List<Instructor> findInstructorsByCourse(@Param("courseId") int courseId);
}