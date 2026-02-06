package com.klu.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.klu.config.AppConfig;
import com.klu.model.Student;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context1 = new AnnotationConfigApplicationContext(AppConfig.class);
        Student s1 = context1.getBean(Student.class);
        s1.display();
        ApplicationContext context2 = new ClassPathXmlApplicationContext("bean.xml");
        Student s2 = (Student) context2.getBean("student");
        s2.display();
    }
}
