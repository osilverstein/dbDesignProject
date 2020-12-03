package jdbc.models;

public class Member {
  Integer id;
  Integer yearsInClub;
  String memberRole;

  public Member(Integer id, Integer yearsInClub, String memberRole) {
    this.id = id;
    this.yearsInClub = yearsInClub;
    this.memberRole = memberRole;
  }

  public Integer getId() {
    return id;
  }

  public Integer getYearsInClub() {
    return yearsInClub;
  }

  public String getMemberRole() {
    return memberRole;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setYearsInClub(Integer yearsInClub) {
    this.yearsInClub = yearsInClub;
  }

  public void setMemberRole(String memberRole) {
    this.memberRole = memberRole;
  }

  @Override
  public String toString() {
    return "Member{" +
        "id=" + id +
        ", yearsInClub=" + yearsInClub +
        ", memberRole='" + memberRole + '\'' +
        '}';
  }
}
