package com.klu.model;

import org.springframework.stereotype.Component;

@Component
public class Certification 
{
  private int CertificateId;
  private String CertificateName;
  private String  dateOfCompletion;
  
  public Certification()
  {
    this.CertificateId= 1;
    this.CertificateName = "Github Copilot";
    this.dateOfCompletion = "16-10-2025";
  }
  public int getCertificateId()
  {
    return CertificateId;
  }
  public String getCertificateName()
  {
    return CertificateName;
  }
  public String getdateOfCompletion()
  {
    return dateOfCompletion;
  }
}