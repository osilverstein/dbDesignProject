package jdbc.models;

public class DiscussionBoard {
  private Integer id;
  private String title;
  private String description;
  private Integer discussionCenterId;

  public DiscussionBoard(Integer id, String title, String description,
      Integer discussionCenterId) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.discussionCenterId = discussionCenterId;
  }

  public Integer getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public Integer getDiscussionCenterId() {
    return discussionCenterId;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDiscussionCenterId(Integer discussionCenterId) {
    this.discussionCenterId = discussionCenterId;
  }

  @Override
  public String toString() {
    return id + "," + title;
  }
}
