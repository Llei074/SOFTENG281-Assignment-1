package nz.ac.auckland.se281;

public class VenueDetails {

  private String name;
  private String code;
  private String capacity;
  private String hirefee;

  public VenueDetails(String name, String code, String capacityInput, String hireFeeInput) {
    this.name = name;
    this.code = code;
    this.capacity = capacityInput;
    this.hirefee = hireFeeInput;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public String getCapacity() {
    return capacity;
  }

  public String gethirefee() {
    return hirefee;
  }
}
