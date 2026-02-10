package com.klu.main;

import com.klu.model.Student;
import com.klu.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
public class MainApp {

  public static void main(String[] args) 
  {
    ApplicationContext c1 = new AnnotationConfigApplicationContext(AppConfig.class);
    Student s1 = c1.getBean(Student.class);
    System.out.println("Details of Student using Annotation");
    s1.display();
    System.out.println("-----------------------------------------");
    ApplicationContext c2 = new ClassPathXmlApplicationContext("applicationContext.xml");
    Student s2 = (Student)c2.getBean("student");
    System.out.println("Details of Student using Xml");
    s2.display();
  }

}