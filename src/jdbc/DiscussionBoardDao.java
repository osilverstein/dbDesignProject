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

public class DiscussionBoardDao {

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

  List<DiscussionBoard> findAllDiscussionBoards() {
    Connection connection = null;
    Statement statement = null;
    List<DiscussionBoard> output = new ArrayList<DiscussionBoard>();

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM discussion_boards;");
      while(resultSet.next()) {
        Integer id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        Integer discussionCenterId = resultSet.getInt("discussionCenterId");
        output.add(new DiscussionBoard(id, title, description, discussionCenterId));
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  DiscussionBoard findDiscussionBoardById(Integer id) {
    Connection connection = null;
    Statement statement = null;
    DiscussionBoard output = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM discussion_boards;");
      while(resultSet.next()) {
        Integer currentId = resultSet.getInt("id");
        if (id.equals(currentId)) {
          String title = resultSet.getString("title");
          String description = resultSet.getString("description");
          Integer discussionCenterId = resultSet.getInt("discussionCenterId");
          output = new DiscussionBoard(id, title, description, discussionCenterId);
          return output;
        }
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  void addDiscussionBoard(DiscussionBoard discussionBoard) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `discussion_boards` WRITE;");
      statement.executeQuery("INSERT INTO `discussion_boards` VALUES (" + discussionBoard.getId() + "," + discussionBoard.getTitle() + "," + discussionBoard.getDescription() + "," + discussionBoard.getDiscussionCenterId());
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void deleteDiscussionBoard(Integer id) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `discussion_boards` WRITE;");
      statement.executeQuery("DELETE FROM `discussion_boards` WHERE id =" + id);
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void updateDiscussionBoard(Integer id, DiscussionBoard board) {
    deleteDiscussionBoard(id);
    addDiscussionBoard(board);
  }
}
