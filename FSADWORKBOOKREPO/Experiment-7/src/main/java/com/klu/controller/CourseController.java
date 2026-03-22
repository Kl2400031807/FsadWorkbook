package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.klu.model.Course;
import com.klu.service.CourseService;

@RestController
@RequestMapping("/api")
public class CourseController {
	@Autowired
    private CourseService service;

    // CREATE
    @PostMapping("/course/add")
    public ResponseEntity<Course> createStudent(@RequestBody Course course) {
        return new ResponseEntity<>(
                service.createCourse(course),
                HttpStatus.CREATED);
    }

    // READ ALL (Pagination)
    @GetMapping("/course/getall")
    public ResponseEntity<Page<Course>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(
                service.getAllCourses(page, size));
    }

    // READ BY ID
    @GetMapping("/course/getbyid/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(
                service.getCourseById(id));
    }

    // UPDATE
    @PutMapping("/course/update/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long id,
            @RequestBody Course course) {

        return ResponseEntity.ok(
                service.updateCourse(id, course));
    }

    // DELETE
    @DeleteMapping("/course/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {

        service.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }
    
 // SEARCH BY TITLE
    @GetMapping("/course/search/{title}")
    public ResponseEntity<Page<Course>> searchCourseByTitle(
            @PathVariable String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return new ResponseEntity<>(
                service.getCoursesByTitle(title, page, size),
                HttpStatus.OK);
    }
}