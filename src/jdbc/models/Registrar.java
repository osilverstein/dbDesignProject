package jdbc.models;

public class Registrar {
  Integer id;
  Integer clubId;
  Boolean isPresident;
  Integer studentId;

  public Registrar(Integer id, Integer clubId, Boolean isPresident, Integer studentId) {
    this.id = id;
    this.clubId = clubId;
    this.isPresident = isPresident;
    this.studentId = studentId;
  }

  public Integer getId() {
    return id;
  }

  public Integer getClubId() {
    return clubId;
  }

  public Boolean getIsPresident() {
    return isPresident;
  }

  public Integer getStudentId() {
    return studentId;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setClubId(Integer clubId) {
    this.clubId = clubId;
  }

  public void setIsPresident(Boolean isPresident) {
    this.isPresident = isPresident;
  }

  public void setStudentId(Integer studentId) {
    this.studentId = studentId;
  }

  @Override
  public String toString() {
    return "Registrar{" +
        "id=" + id +
        ", clubId=" + clubId +
        ", isPresident=" + isPresident +
        ", studentId=" + studentId +
        '}';
  }
}
