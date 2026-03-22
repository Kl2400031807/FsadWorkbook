package com.klu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.klu.model.Course;
import com.klu.exception.CourseNotFoundException;
import com.klu.repo.CourseRepo;
@Service
public class  CourseService {
	@Autowired
    private CourseRepo repository;

    // CREATE
    public Course createCourse(Course course) {
        return repository.save(course);
    }

    // READ ALL (Pagination)
    public Page<Course> getAllCourses(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    // READ BY ID
    public Course getCourseById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new CourseNotFoundException("Course not found with id: " + id));
    }

    // READ BY Title (JPQL + Pagination)
    public Page<Course> getCoursesByTitle(String title, int page, int size) {
        Page<Course> courses =
                repository.findByTitle(title, PageRequest.of(page, size));

        if (courses.isEmpty()) {
            throw new CourseNotFoundException(
                    "No course found for title: " + title);
            }
        return courses;
    }

    // UPDATE
    public Course updateCourse(Long id, Course updatedCourse) {

        Course existing = getCourseById(id);

        existing.setTitle(updatedCourse.getTitle());
        existing.setDuration(updatedCourse.getDuration());
        existing.setFee(updatedCourse.getFee());

        return repository.save(existing);
    }

    // DELETE
    public void deleteCourse(Long id) {

        Course course = getCourseById(id);
        repository.delete(course);
    }
}