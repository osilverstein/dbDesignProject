package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.models.Club;
import jdbc.models.DiscussionBoard;
import jdbc.models.DiscussionCenter;

public class DiscussionCenterDao {

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

  List<DiscussionCenter> findAllDiscussionCenters() {
    Connection connection = null;
    Statement statement = null;
    List<DiscussionCenter> output = new ArrayList<DiscussionCenter>();

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM discussion_centers;");
      while(resultSet.next()) {
        Integer id = resultSet.getInt("id");
        Integer clubId = resultSet.getInt("clubId");
        output.add(new DiscussionCenter(id, clubId));
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  DiscussionCenter findDiscussionCenterById(Integer id) {
    Connection connection = null;
    Statement statement = null;
    DiscussionCenter output = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM discussion_centers;");
      while(resultSet.next()) {
        Integer currentId = resultSet.getInt("id");
        if (id.equals(currentId)) {
          Integer clubId = resultSet.getInt("clubId");
          output = new DiscussionCenter(id, clubId);
          return output;
        }
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  void addDiscussionCenter(DiscussionCenter discussionCenter) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `discussion_centers` WRITE;");
      statement.executeQuery("INSERT INTO `discussion_centers` VALUES (" + discussionCenter.getId() + "," + discussionCenter.getClubId());
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void deleteDiscussionCenter(Integer id) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `discussion_centers` WRITE;");
      statement.executeQuery("DELETE FROM `discussion_centers` WHERE id =" + id);
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void updateDiscussionCenter(Integer id, DiscussionCenter center) {
    deleteDiscussionCenter(id);
    addDiscussionCenter(center);
  }
}
