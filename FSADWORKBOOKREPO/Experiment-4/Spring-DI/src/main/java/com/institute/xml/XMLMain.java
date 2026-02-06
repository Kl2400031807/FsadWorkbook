package com.institute.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.institute.model.Student;

public class XMLMain {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Student s = context.getBean("student", Student.class);
        s.display();
    }
}
