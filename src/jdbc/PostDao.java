package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.models.Post;
import jdbc.models.PostType;

public class PostDao {

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

  List<Post> findAllPosts() {
    Connection connection = null;
    Statement statement = null;
    List<Post> output = new ArrayList<Post>();

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM posts;");
      while(resultSet.next()) {
        Integer id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        Boolean anonymous = resultSet.getBoolean("anonymous");
        PostType type = (PostType) resultSet.getObject("type");
        Integer memberId = resultSet.getInt("memberId");
        Integer discussionBoardId = resultSet.getInt("discussionBoardId");
        output.add(new Post(id, title, anonymous, type, memberId, discussionBoardId));
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  Post findPostById(Integer id) {
    Connection connection = null;
    Statement statement = null;
    Post output = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM posts;");
      while(resultSet.next()) {
        Integer currentId = resultSet.getInt("id");
        if (id.equals(currentId)) {
          String title = resultSet.getString("title");
          Boolean anonymous = resultSet.getBoolean("anonymous");
          PostType type = (PostType) resultSet.getObject("type");
          Integer memberId = resultSet.getInt("memberId");
          Integer discussionBoardId = resultSet.getInt("discussionBoardId");
          output = new Post(id, title, anonymous, type, memberId, discussionBoardId);
          return output;
        }
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  void addPost(Post post) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `posts` WRITE;");
      statement.executeQuery("INSERT INTO `posts` VALUES ("+ post.getId() + "," + post.getTitle()+ "," + post.getAnonymous()+ "," + post.getType()+ "," + post.getMemberId()+ "," + post.getDiscussionBoardId());
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void deletePost(Integer id) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `posts` WRITE;");
      statement.executeQuery("DELETE FROM `posts` WHERE id =" + id);
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void updatePost(Integer id, Post post) {
    deletePost(id);
    addPost(post);
  }
}
