package com.klu.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.klu.model.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {
	  // JPQL query
    @Query("SELECT c FROM Course c WHERE c.title = :title")
    Page<Course> findByTitle(@Param("title") String title, Pageable pageable);
}