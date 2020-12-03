package jdbc.models;

public class Club {
  private Integer id;
  private String name;
  private String description;
  private Integer founded;

  public Club(Integer id, String name, String description, Integer founded) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.founded = founded;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Integer getFounded() {
    return founded;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setFounded(Integer founded) {
    this.founded = founded;
  }

  @Override
  public String toString() {
    return id + "," + name;
  }
}
