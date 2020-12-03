package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.models.Member;

public class MemberDao {

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

  List<Member> findAllMembers() {
    Connection connection = null;
    Statement statement = null;
    List<Member> output = new ArrayList<Member>();

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM members;");
      while(resultSet.next()) {
        Integer id = resultSet.getInt("id");
        Integer yearsInClub = resultSet.getInt("yearsInClub");
        String memberRole = resultSet.getString("memberRole");
        output.add(new Member(id, yearsInClub, memberRole));
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  Member findMemberById(Integer id) {
    Connection connection = null;
    Statement statement = null;
    Member output = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM members;");
      while(resultSet.next()) {
        Integer currentId = resultSet.getInt("id");
        if (id.equals(currentId)) {
          Integer yearsInClub = resultSet.getInt("yearsInClub");
          String memberRole = resultSet.getString("memberRole");
          output = new Member(id, yearsInClub, memberRole);
          return output;
        }
      }

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
    return output;
  }

  void addMember(Member member) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `members` WRITE;");
      statement.executeQuery("INSERT INTO `members` VALUES ("+ member.getId() + "," + member.getYearsInClub()+ "," + member.getMemberRole());
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void deleteMember(Integer id) {
    Connection connection = null;
    Statement statement = null;

    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
      statement = connection.createStatement();
      statement.executeQuery("LOCK TABLES `members` WRITE;");
      statement.executeQuery("DELETE FROM `members` WHERE id =" + id);
      statement.executeQuery("UNLOCK TABLES;");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();;
    }
  }

  void updateMember(Integer id, Member member) {
    deleteMember(id);
    addMember(member);
  }
}
