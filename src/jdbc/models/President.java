package jdbc.models;

public class President {
  Integer id;
  Integer yearElected;
  Integer termEnd;

  public President(Integer id, Integer yearElected, Integer termEnd) {
    this.id = id;
    this.yearElected = yearElected;
    this.termEnd = termEnd;
  }

  public Integer getId() {
    return id;
  }

  public Integer getYearElected() {
    return yearElected;
  }

  public Integer getTermEnd() {
    return termEnd;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setYearElected(Integer yearElected) {
    this.yearElected = yearElected;
  }

  public void setTermEnd(Integer termEnd) {
    this.termEnd = termEnd;
  }

  @Override
  public String toString() {
    return "President{" +
        "id=" + id +
        ", yearElected=" + yearElected +
        ", termEnd=" + termEnd +
        '}';
  }
}
