package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.models.Meeting;

public class MeetingDao {

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

  List<Meeting> findAllMeetings() {
    Connection connection = null;
    Statement statement = null;
    List<Meeting> output = new ArrayList<Meeting>();

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM meetings;");
      while(resultSet.next()) {
        Integer id = resultSet.getInt("id");
        Date date = (Date) resultSet.getObject("date");
        Integer clubId = resultSet.getInt("club_id");
        output.add(new Meeting(id, date, clubId));
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  Meeting findMeetingById(Integer id) {
    Connection connection = null;
    Statement statement = null;
    Meeting output = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM meetings;");
      while(resultSet.next()) {
        Integer currentId = resultSet.getInt("id");
        if (id.equals(currentId)) {
          Date date = (Date) resultSet.getObject("date");
          Integer clubId = resultSet.getInt("club_id");
          output = new Meeting(id, date, clubId);
          return output;
        }
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  void addMeeting(Meeting meeting) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `meetings` WRITE;");
      statement.executeQuery("INSERT INTO `meetings` VALUES ("+ meeting.getId() + "," + meeting.getDate()+ "," + meeting.getClubId());
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void deleteMeeting(Integer id) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `meetings` WRITE;");
      statement.executeQuery("DELETE FROM `meetings` WHERE id =" + id);
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void updateMeeting(Integer id, Meeting meet) {
    deleteMeeting(id);
    addMeeting(meet);
  }
}
