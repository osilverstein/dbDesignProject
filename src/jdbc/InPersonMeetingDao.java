package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.models.DiscussionCenter;
import jdbc.models.InPersonMeeting;

public class InPersonMeetingDao {

  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  static final String HOST = "localhost:3306";
  static final String SCHEMA = "database_design";
  static final String CONFIG = "serverTimezone=UTC";
  static final String DB_URL = "jdbc:mysql://"+HOST+"/"+SCHEMA+"?"+CONFIG;
  static final String USER = "dbDesign";
  static final String PASS = "dbDesign";

  public static void main(String[] args) {
    //execute stuff here
  }

  List<InPersonMeeting> findAllInPersonMeetings() {
    Connection connection = null;
    Statement statement = null;
    List<InPersonMeeting> output = new ArrayList<InPersonMeeting>();

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM in_person_meetings;");
      while(resultSet.next()) {
        Integer id = resultSet.getInt("id");
        String location = resultSet.getString("location");
        output.add(new InPersonMeeting(id, location));
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  InPersonMeeting findInPersonMeetingById(Integer id) {
    Connection connection = null;
    Statement statement = null;
    InPersonMeeting output = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM in_person_meetings;");
      while(resultSet.next()) {
        Integer currentId = resultSet.getInt("id");
        if (id.equals(currentId)) {
          String loc = resultSet.getString("location");
          output = new InPersonMeeting(id, loc);
          return output;
        }
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  void addInPersonMeeting(InPersonMeeting inPersonMeeting) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `in_person_meetings` WRITE;");
      statement.executeQuery("INSERT INTO `in_person_meetings` VALUES (" + inPersonMeeting.getId() + "," + inPersonMeeting.getLocation());
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void deleteInPersonMeeting(Integer id) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `in_person_meetings` WRITE;");
      statement.executeQuery("DELETE FROM `in_person_meetings` WHERE id =" + id);
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void updateInPersonMeeting(Integer id, InPersonMeeting meet) {
    deleteInPersonMeeting(id);
    addInPersonMeeting(meet);
  }
}
