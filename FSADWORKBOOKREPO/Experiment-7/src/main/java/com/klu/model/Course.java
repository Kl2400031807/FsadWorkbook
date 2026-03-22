package com.klu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="course")
public class Course {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;
	    private String duration;
	    private int fee;
	    public Course() {}
	    
	    public Course(Long id,String title,String duration,int fee) {
	    	this.id=id;
	    	this.title=title;
	    	this.duration=duration;
	    	this.fee=fee;
	    }
	    public void setId(Long id) {
	    	this.id=id;
	    }
	    public Long getId() {
	    	return id;
	    }
	    public void setTitle(String title) {
	    	this.title=title;
	    }
	    public String getTitle() {
	    	return title;
	    }
	    public void setDuration(String duration) {
	    	this.duration = duration;
	    }
	    public String getDuration() {
	    	return duration;
	    }
	    public void setFee(int fee) {
	    	this.fee=fee;
	    }
	    public int getFee() {
	    	return fee;
	    }
}