package com.institute.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.institute.model.Student;

@ComponentScan("com.institute")
public class AnnotationMain {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationMain.class);

        Student s = context.getBean(Student.class);
        s.display();
    }
}
