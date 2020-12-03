package jdbc.models;

public class InPersonMeeting {
  Integer id;
  String location;

  public InPersonMeeting(Integer id, String location) {
    this.id = id;
    this.location = location;
  }

  public Integer getId() {
    return id;
  }

  public String getLocation() {
    return location;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public String toString() {
    return id + "," + location;
  }
}
