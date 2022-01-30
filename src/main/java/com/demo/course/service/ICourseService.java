package com.demo.course.service;

import java.util.List;

import com.demo.course.entity.Course;

public interface ICourseService {
	
	public Course addCourse(Course course);

	public List<Course> getAllCourses();

	public Course getCourseById(int id);

}
