package nz.ac.auckland.se281;

public abstract class Venue {

  protected String name;
  protected String code;
  protected int tempAnswer;

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }
}