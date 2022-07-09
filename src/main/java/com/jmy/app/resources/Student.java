package com.jmy.app.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {

  @JsonProperty
  private int studentId;

  @JsonProperty
  private int DOB;

  @JsonProperty
  private String firstName;

  @JsonProperty
  private String lastName;

  @JsonProperty
  private Integer id;

  protected Student(){
  }

//  public Student (String firstName, String lastName, int studentId, int DOB){
//    this.firstName = firstName;
//    this.lastName = lastName;
//    this.studentId = studentId;
//    this.DOB = DOB;
//
//  }

  public void setID(Integer id){
    this.id = id;
  }

  public String name(){
    return firstName + " " + lastName;
  }

  public int getID(){
    return id;
  }
}
