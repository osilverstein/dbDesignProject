package jdbc.models;

public class PostType {
  String postType;

  public PostType(String postType) {
    this.postType = postType;
  }

  public String getPostType() {
    return postType;
  }

  public void setPostType(String postType) {
    this.postType = postType;
  }

  @Override
  public String toString() {
    return "PostType{" +
        "postType='" + postType + '\'' +
        '}';
  }
}
