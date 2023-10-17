package com.example.studentmanagentsystem.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.studentmanagentsystem.entity.Course;

@Component
public class CourseDAO {
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Course");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int courseID = resultSet.getInt("CourseID");
                int credits = resultSet.getInt("Credits");
                String courseTitle = resultSet.getString("CourseTitle");

                Course course = new Course(courseID, credits, courseTitle);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }
}

