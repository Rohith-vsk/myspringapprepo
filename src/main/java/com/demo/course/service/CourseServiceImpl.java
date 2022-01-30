package com.demo.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.course.entity.Course;
import com.demo.course.repository.CourseRepository;


@Service
public class CourseServiceImpl implements ICourseService{

	@Autowired
	private CourseRepository courseRepo; 
	
	@Override
	public Course addCourse(Course course) {
		Course addedcourse  = courseRepo.save(course);
		return addedcourse;
	}

	@Override
	public List<Course> getAllCourses() {
		List<Course> courseList = courseRepo.findAll();
		return courseList;
	}

	@Override
	public Course getCourseById(int id) {
		Course foundcourse = courseRepo.findById(id).get();
		return foundcourse;
		
	}
	


}
