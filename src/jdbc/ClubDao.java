package jdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jdbc.models.Club;

public class ClubDao {

  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  static final String HOST = "localhost:3306";
  static final String SCHEMA = "database_design";
  static final String CONFIG = "serverTimezone=UTC";
  static final String DB_URL = "jdbc:mysql://"+HOST+"/"+SCHEMA+"?"+CONFIG;
  static final String USER = "dbDesign";
  static final String PASS = "dbDesign";

  static final String FIND_ALL_COURSES =
      "SELECT * FROM courses";


  public static void main(String[] args) {
    //execute stuff here
  }

  List<Club> findAllClubs() {
    Connection connection = null;
    Statement statement = null;
    List<Club> output = new ArrayList<Club>();

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM clubs;");
      while(resultSet.next()) {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        Integer founded = resultSet.getInt("founded");
        output.add(new Club(id, name, description, founded));
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  Club findClubById(Integer id) {
    Connection connection = null;
    Statement statement = null;
    Club output = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM clubs;");
      while(resultSet.next()) {
        Integer currentId = resultSet.getInt("id");
        if (id.equals(currentId)) {
          Integer idCandidate = resultSet.getInt("id");
          String name = resultSet.getString("name");
          String description = resultSet.getString("description");
          Integer founded = resultSet.getInt("founded");
          output = new Club(idCandidate, name, description, founded);
          return output;
        }
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  void addClub(Club club) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `clubs` WRITE;");
      statement.executeQuery("INSERT INTO `clubs` VALUES (" + club.getId() + "," + club.getName() + "," + club.getDescription() + "," + club.getFounded());
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void deleteClub(Integer id) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `clubs` WRITE;");
      statement.executeQuery("DELETE FROM `clubs` WHERE id =" + id);
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void updateClub(Integer id, Club club) {
    deleteClub(id);
    addClub(club);
  }
}
