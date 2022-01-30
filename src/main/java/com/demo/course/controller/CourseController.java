package com.demo.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.course.entity.Course;
import com.demo.course.service.ICourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private ICourseService courseService;
	
	@PostMapping("/save")
	public ResponseEntity<Course> addCourse(@RequestBody Course course)
	{
		Course savedcourse = courseService.addCourse(course);
		return new ResponseEntity<Course>(savedcourse,HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Course>> getAllDepartments()
	{
		List<Course> courseList = courseService.getAllCourses();
		return new ResponseEntity<List<Course>>(courseList,HttpStatus.OK);
	}
	
	@GetMapping("/get/{courseid}")
	public ResponseEntity<Course> getDepartmentById(@PathVariable("courseid") int id)
	{
		Course course = courseService.getCourseById(id);
		return new ResponseEntity<Course>(course,HttpStatus.OK);
	}

}
