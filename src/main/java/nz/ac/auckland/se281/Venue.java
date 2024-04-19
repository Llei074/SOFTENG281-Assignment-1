package nz.ac.auckland.se281;

public abstract class Venue {

  protected int tempAnswer;
  protected String name;
  protected String code;
  protected String venueCapacity;
  protected String hireFee;

  public static String sysDate;
  public static String[] stringDate;

  public String getDate() {
    return sysDate;
  }

  public String getSysDay() {
    return stringDate[0];
  }

  public String getSysMonth() {
    return stringDate[1];
  }

  public String getSysYear() {
    return stringDate[2];
  }

  public String getName() {
    return this.name;
  }

  public String getHireFee() {
    return this.hireFee;
  }

  public String getCapacity() {
    return this.venueCapacity;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }
}
