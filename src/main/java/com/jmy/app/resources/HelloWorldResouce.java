package com.jmy.app.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.ws.rs.PathParam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.lang.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.configuration.*;
import io.dropwizard.db.DataSourceFactory;
import org.json.simple.parser.ParseException;
import org.postgresql.jdbc.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/helloWorld")
public class HelloWorldResouce {

  public final String url = "jdbc:postgresql://localhost:5432/test";
  public final String user = "jimmy";
  public final String password = "10018029";


  @Path("/all")
  @GET
  public String get(){
    ArrayList<Person> People = new ArrayList<>();
    String output = "";

    try {
      Connection con = DriverManager.getConnection(url, user, password);
      PersonDAO test = new PersonDAO(con);
      People = test.getAll();

      for (int i = 0; i < People.size(); i++) {
        output+=People.get(i).getInfo();
        output += '\n';
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return output;
  }

  @Path("/{id}")
  @GET
  public String getByID(@PathParam("id") Integer id){
    String output = "";
    try {
      Connection con = DriverManager.getConnection(url, user, password);

      PersonDAO test = new PersonDAO(con);
      Person p = test.getByID(id);
      if (p == null) return "No person found with ID " + id + "!";
      output = p.getInfo();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return output;
  }

  @Path("/{id}")
  @DELETE
  public String deleteByID(@PathParam("id") Integer id){
    String output = "Deleted Person's Details" + '\n' + "-------------------------------------------------------------------" + '\n';
    try {
      Connection con = DriverManager.getConnection(url, user, password);

      PersonDAO test = new PersonDAO(con);
      Person p = test.delete(id);
      if (p == null) return "No person found with ID " + id + "!";
      else {
        output += p.getInfo();

      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return output;
  }



  @POST
  public String post(PersonInput PI){
    String output = "Inserted Person's Details" + '\n' + "-------------------------------------------------------------------" + '\n';
    try {
      Connection con = DriverManager.getConnection(url, user, password);

      PersonDAO test = new PersonDAO(con);
      int id = test.insert(PI);

      Person p = test.getByID(id);
      output += p.getInfo();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return output;
  }



  @PUT
  public String update(String str) throws ParseException {

    String output1 = "Before Update" + '\n' + "-------------------------------------------------------------------" + '\n';
    String output2 = "After Update" + '\n' + "-------------------------------------------------------------------" + '\n';

    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(str);
    Long id = (Long) json.get("id");
    if (id.equals(null)) return "No id given!";

    try {
      Connection con = DriverManager.getConnection(url, user, password);

      PersonDAO test = new PersonDAO(con);

      Person p = test.getByID(id.intValue());
      output1 += p.getInfo();

      ArrayList<String> as = new ArrayList<>();
      as.add("lastName");
      as.add("firstName");
      as.add("address");
      as.add("city");

      for (int i = 0; i < as.size(); i++){
        String v = (String) json.get(as.get(i));
        if (v!= null && !v.isEmpty()) {

          if (as.get(i) == "lastName") p.setLastName(v);
          else if (as.get(i) == "firstName") p.setFirstName(v);
          else if (as.get(i) == "address") p.setAddress(v);
          else if (as.get(i) == "city") p.setCity(v);
        }
      }
      test.update(p);
      output2 += p.getInfo();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }


    return output1 + '\n' + '\n' + output2;

  }

  @Path("/{id}")
  @POST
  public String postID(Student student, @PathParam("id") Integer id){
    //student.setID(id);
    return "Student name is: " + student.name() + "\n"+"Student ID is: " + student.getID();
  }
}





  /*ArrayList<Person> People = new ArrayList<>();
    String output = "";
    try {
      Connection con = DriverManager.getConnection(url, user, password);
      try {
        Statement st = con.createStatement();
        try {
          ResultSet rs = st.executeQuery("SELECT * FROM chat.persons");
          try {

            while (rs.next()) {
              People.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            for (int i = 0; i < People.size(); i++) {
              output+=People.get(i).getInfo();
              output += '\n';
            }

          } finally {
            rs.close();
          }
        } finally {
          st.close();
        }
      } finally {
        con.close();
      }
    } catch (SQLException ex) {

//      Logger lgr = Logger.getLogger(JavaPostgreSqlVersion.class.getName());
//      lgr.log(Level.SEVERE, ex.getMessage(), ex);
      System.out.println(ex.getMessage());
    } catch (Exception e){
      System.out.println(e.getMessage());
    }*/