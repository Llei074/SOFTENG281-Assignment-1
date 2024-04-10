package nz.ac.auckland.se281;

abstract class Venue {

  protected String name;
  protected String code;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }
}

class VenueDetails extends Venue {

  private String venueCapacity;
  private String hireFee;

  public VenueDetails(String name, String code, String capacityInput, String hireFeeInput) {
    this.name = name;
    this.code = code;
    this.venueCapacity = capacityInput;
    this.hireFee = hireFeeInput;
  }

  public String getCapacity() {
    return venueCapacity;
  }

  public String gethirefee() {
    return hireFee;
  }
}

class BookingDetails extends Venue {

  private String date;
  private String email;
  private String attendees;
  private String reference;

  public BookingDetails(String options[], String name, String ref) {
    this.code = options[0];
    this.date = options[1];
    this.email = options[2];
    this.attendees = options[3];
    this.name = name;
    this.reference = ref;
  }

  public String getAttendees() {
    return attendees;
  }

  public String getEmail() {
    return email;
  }

  public String getDate() {
    return date;
  }

  public String getReference() {
    return reference;
  }
}
