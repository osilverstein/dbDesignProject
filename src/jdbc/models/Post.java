package jdbc.models;

public class Post {
  private Integer id;
  private String title;
  private Boolean anonymous;
  private PostType type;
  private Integer memberId;
  private Integer discussionBoardId;

  public Post(Integer id, String title, Boolean anonymous, PostType type,
      Integer discussionBoardId, Integer memberId) {
    this.id = id;
    this.title = title;
    this.anonymous = anonymous;
    this.type = type;
    this.discussionBoardId = discussionBoardId;
    this.memberId = memberId;
  }

  public Integer getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Boolean getAnonymous() {
    return anonymous;
  }

  public PostType getType() {
    return type;
  }

  public Integer getDiscussionBoardId() {
    return discussionBoardId;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAnonymous(Boolean anonymous) {
    this.anonymous = anonymous;
  }

  public void setType(PostType type) {
    this.type = type;
  }

  public void setDiscussionBoardId(Integer discussionBoardId) {
    this.discussionBoardId = discussionBoardId;
  }

  @Override
  public String toString() {
    return "Post{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", anonymous=" + anonymous +
        ", type=" + type +
        ", discussionBoardId=" + discussionBoardId +
        '}';
  }

  public Integer getMemberId() {
    return memberId;
  }

  public void setMemberId(Integer memberId) {
    this.memberId = memberId;
  }
}
