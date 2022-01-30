package com.demo.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.course.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer>{

}
