package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.models.InPersonMeeting;
import jdbc.models.ZoomMeeting;

public class ZoomMeetingDao {

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

  List<ZoomMeeting> findAllZoomMeetings() {
    Connection connection = null;
    Statement statement = null;
    List<ZoomMeeting> output = new ArrayList<ZoomMeeting>();

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM zoom-meetings;");
      while(resultSet.next()) {
        Integer id = resultSet.getInt("id");
        String location = resultSet.getString("location");
        output.add(new ZoomMeeting(id, location));
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  ZoomMeeting findInPersonMeetingById(Integer id) {
    Connection connection = null;
    Statement statement = null;
    ZoomMeeting output = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM zoom-meetings;");
      while(resultSet.next()) {
        Integer currentId = resultSet.getInt("id");
        if (id.equals(currentId)) {
          String link = resultSet.getString("zoomLink");
          output = new ZoomMeeting(id, link);
          return output;
        }
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  void addZoomMeeting(ZoomMeeting zoom) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `zoom-meetings` WRITE;");
      statement.executeQuery("INSERT INTO `zoom-meetings` VALUES (" + zoom.getId() + "," + zoom.getZoomLink());
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void deleteZoomMeeting(Integer id) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `zoom-meetings` WRITE;");
      statement.executeQuery("DELETE FROM `zoom-meetings` WHERE id =" + id);
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void updateZoomMeeting(Integer id, ZoomMeeting meet) {
    deleteZoomMeeting(id);
    addZoomMeeting(meet);
  }
}
