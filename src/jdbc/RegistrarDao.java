package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.models.Club;
import jdbc.models.Registrar;

public class RegistrarDao {

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

  List<Registrar> findAllRegistrars() {
    Connection connection = null;
    Statement statement = null;
    List<Registrar> output = new ArrayList<Registrar>();

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM registrars;");
      while(resultSet.next()) {
        Integer idCandidate = resultSet.getInt("id");
        Integer clubId = resultSet.getInt("clubId");
        Boolean isPresident = resultSet.getBoolean("isPresident");
        Integer studentId = resultSet.getInt("studentId");
        output.add(new Registrar(idCandidate, clubId, isPresident, studentId));
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  Registrar findfindAllRegistrarById(Integer id) {
    Connection connection = null;
    Statement statement = null;
    Registrar output = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM registrars;");
      while(resultSet.next()) {
        Integer currentId = resultSet.getInt("id");
        if (id.equals(currentId)) {
          Integer idCandidate = resultSet.getInt("id");
          Integer clubId = resultSet.getInt("clubId");
          Boolean isPresident = resultSet.getBoolean("isPresident");
          Integer studentId = resultSet.getInt("studentId");
          output = new Registrar(idCandidate, clubId, isPresident, studentId);
          return output;
        }
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  void addRegistrar(Registrar registrar) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `registrars` WRITE;");
      statement.executeQuery("INSERT INTO `registrars` VALUES (" + registrar.getId() + "," + registrar.getClubId() + "," + registrar.getIsPresident() + "," + registrar.getStudentId());
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void deleteRegistrar(Integer id) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `registrars` WRITE;");
      statement.executeQuery("DELETE FROM `registrars` WHERE id =" + id);
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void updateRegistrar(Integer id, Registrar registrar) {
    deleteRegistrar(id);
    addRegistrar(registrar);
  }
}
