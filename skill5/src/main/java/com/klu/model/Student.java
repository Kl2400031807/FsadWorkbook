package com.klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student 
{
   private int StudentId;
   private String StudentName;
   private String gender;
   @Autowired
   private Certification certificate;
   
  public Student()
  {
    this.StudentId = 2;
    this.StudentName = "Anvitha";
    this.gender = "Female";
  }
   @Autowired
  public Student(@Value("3")int StudentId,@Value("Akshaya")String StudentName)
  {
    this.StudentId = StudentId;
    this.StudentName = StudentName;
  }
  @Autowired
  public void setGender(@Value("Female")String gender)
  {
    this.gender = gender;
  }
  public void display()
  {
    System.out.println("StudentId: "+StudentId);
    System.out.println("StudentName: "+StudentName);
    System.out.println("Gender: "+gender);
    System.out.println("CertificateId: "+certificate.getCertificateId());
    System.out.println("CertificateName: "+ certificate.getCertificateName());
    System.out.println("Date of Completion: "+certificate.getdateOfCompletion());
  }
   
}