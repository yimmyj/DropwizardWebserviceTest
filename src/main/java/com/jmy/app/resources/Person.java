package com.jmy.app.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person{

  @JsonProperty
  private int Id;

  @JsonProperty
  private String lastName;

  @JsonProperty
  private String firstName;

  @JsonProperty
  private String address;

  @JsonProperty
  private String city;


  protected Person(int Id,String lastName,String firstName, String address,String city){
    this.Id = Id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.address = address;
    this.city = city;
  }

  protected Person (PersonInput PI, int Id){
    this.Id = Id;
    this.lastName = PI.getLastName();
    this.firstName = PI.getFirstName();
    this.address = PI.getAddress();;
    this.city = PI.getCity();;

  }

  public void setLastName(String l){
    lastName = l;
  }

  public void setFirstName(String l){
    firstName = l;
  }

  public void setAddress(String l){
    address = l;
  }

  public void setCity(String l){
    city = l;
  }

  public String getLastName(){
    return lastName;
  }

  public String getFirstName(){
    return firstName;
  }

  public String getAddress(){
    return address;
  }

  public String getCity(){
    return city;
  }

  public Integer getID(){
    return Id;
  }

//  public Student (String firstName, String lastName, int studentId, int DOB){
//    this.firstName = firstName;
//    this.lastName = lastName;
//    this.studentId = studentId;
//    this.DOB = DOB;
//
//  }

  public String getInfo(){
    return "ID:   " + Id + "\n"
        + "Name is:    " + firstName + " " + lastName + "\n" +
        "Lives at:   " + address + ", " + city;
  }


}
