package nz.ac.auckland.se281;

public abstract class Venue {

  protected int tempAnswer;
  protected String name;
  protected String code;
  protected String venueCapacity;
  protected String hireFee;

  public abstract String getName();

  public abstract String getHireFee();

  public abstract String getCapacity();

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }
}
