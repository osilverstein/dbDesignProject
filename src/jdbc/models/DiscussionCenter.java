package jdbc.models;

public class DiscussionCenter {
  private Integer id;
  private Integer clubId;

  public DiscussionCenter(Integer id, Integer clubId) {
    this.id = id;
    this.clubId = clubId;
  }

  public Integer getId() {
    return id;
  }

  public Integer getClubId() {
    return clubId;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setClubId(Integer clubId) {
    this.clubId = clubId;
  }

  @Override
  public String toString() {
    return id + "," + clubId;
  }
}
