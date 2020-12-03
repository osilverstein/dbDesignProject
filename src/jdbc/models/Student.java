package jdbc.models;

public class Student {
  Integer id;
  String name;
  String username;
  String password;
  Integer gradYear;

  public Student(Integer id, String name, String username, String password,
      Integer gradYear) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.password = password;
    this.gradYear = gradYear;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public Integer getGradYear() {
    return gradYear;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setGradYear(Integer gradYear) {
    this.gradYear = gradYear;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", gradYear=" + gradYear +
        '}';
  }
}
