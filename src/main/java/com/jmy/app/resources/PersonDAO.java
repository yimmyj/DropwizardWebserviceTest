package com.jmy.app.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.sql.*;
import java.util.ArrayList;
import java.lang.*;
import io.dropwizard.configuration.*;
import io.dropwizard.db.DataSourceFactory;
import org.postgresql.jdbc.*;


public class PersonDAO {

  private Connection con;

  protected PersonDAO(Connection con) throws SQLException {
    this.con = con;
  }


  public ArrayList<Person> getAll() throws SQLException {
    ArrayList<Person> People = new ArrayList<>();
    try {
      Statement st = con.createStatement();
      try {
        ResultSet rs = st.executeQuery("SELECT * FROM chat.persons");
        try {

          while (rs.next()) {
            People.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
          }

        } finally {
          rs.close();
        }
      } finally {
        st.close();
      }
    } finally {
      return People;
    }
  }



  public Person getByID(int id){
    Person output = null;
    try {
      Statement st = con.createStatement();
      try {
        String sql = "Select * from chat.persons where personid = " + id;
        ResultSet rs = st.executeQuery(sql);
        try {
          if (!rs.next() ) {
            return output;
          }
           output = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        } finally {
          rs.close();
        }
      } finally {
        st.close();
      }
    } finally {
      return output;
    }
  }

  public Person delete(int id){
    Person output = null;
    try {
      Statement st = con.createStatement();
      try {
        String sql = "Select * from chat.persons where personid = " + id;
        ResultSet rs = st.executeQuery(sql);
        try {
          if (!rs.next() ) {
            return output; //return null
          }
          output = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

          String deletesql = "DELETE FROM chat.persons WHERE personid = " + id;
          st.executeQuery(deletesql);
        } finally {
          rs.close();
        }
      } finally {
        st.close();
      }
    } finally {
      return output;
    }
  }

  public int insert(PersonInput PI){

    int id = 0;
    try {
      Statement st = con.createStatement();

      String query =
          "INSERT INTO chat.persons (lastname, firstname, address, city)" +
              "VALUES (?,?,?,?)";

      PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, PI.getLastName());
      ps.setString(2, PI.getFirstName());
      ps.setString(3, PI.getAddress());
      ps.setString(4, PI.getCity());

      ps.executeUpdate();

      try{

        ResultSet rs = ps.getGeneratedKeys();

        while (rs.next())
        {
          id = rs.getInt(1);
        }


      }



      /*try {
        String sql = "INSERT INTO chat.persons (lastname, firstname, address, city)" + '\n' +
        "VALUES ("+PI.getValues()+")";
        int lines = st.executeUpdate(sql);
        ResultSet rs = st.getGeneratedKeys();


        //ResultSet rs = st.executeQuery("SELECT * FROM chat.persons ORDER BY personid DESC LIMIT 1");
        rs.next();
        id = rs.getInt(0);


        rs.close();


      } */finally {
        st.close();
      }
    } finally {
      return id;
    }
  }

  public int update(Person P) throws SQLException{

    try {

      String query =
          "UPDATE chat.persons SET firstname=?, lastname=?, address=?, city=? where personid=?;";

      PreparedStatement ps = con.prepareStatement(query);
      ps.setString(1, P.getLastName());
      ps.setString(2, P.getFirstName());
      ps.setString(3, P.getAddress());
      ps.setString(4, P.getCity());
      ps.setInt(5,P.getID());

      ps.executeUpdate();


    } finally {
      System.out.print(P.getInfo());
    }
    return 0;
  }




}
