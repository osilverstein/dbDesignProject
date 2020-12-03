package jdbc.models;
import java.sql.*;


public class Meeting {
  Integer id;
  Date date;
  Integer clubId;

  public Meeting(Integer id, Date date, Integer clubId) {
    this.id = id;
    this.date = date;
    this.clubId = clubId;
  }

  public Integer getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }

  public Integer getClubId() {
    return clubId;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setClubId(Integer clubId) {
    this.clubId = clubId;
  }

  @Override
  public String toString() {
    return "Meeting{" +
        "id=" + id +
        ", date=" + date +
        ", clubId=" + clubId +
        '}';
  }
}
