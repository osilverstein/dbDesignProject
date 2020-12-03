package jdbc.models;

public class ZoomMeeting {
  Integer id;
  String zoomLink;

  public ZoomMeeting(Integer id, String zoomLink) {
    this.id = id;
    this.zoomLink = zoomLink;
  }

  public Integer getId() {
    return id;
  }

  public String getZoomLink() {
    return zoomLink;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setZoomLink(String zoomLink) {
    this.zoomLink = zoomLink;
  }

  @Override
  public String toString() {
    return "ZoomMeeting{" +
        "id=" + id +
        ", zoomLink='" + zoomLink + '\'' +
        '}';
  }
}
