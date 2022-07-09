package com.jmy.app.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonInput{


  @JsonProperty
  private String lastName;

  @JsonProperty
  private String firstName;

  @JsonProperty
  private String address;

  @JsonProperty
  private String city;

  protected PersonInput(){
  }

  /*protected PersonInput(String lastName,String firstName, String address,String city){
    this.lastName = lastName;
    this.firstName = firstName;
    this.address = address;
    this.city = city;
  }*/

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

  public String getValues(){
    return "'"+lastName+"', '"+firstName+"', '"+address+"', '"+city+"'";
  }


//  public Student (String firstName, String lastName, int studentId, int DOB){
//    this.firstName = firstName;
//    this.lastName = lastName;
//    this.studentId = studentId;
//    this.DOB = DOB;
//
//  }


}
