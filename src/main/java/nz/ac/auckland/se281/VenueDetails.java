package nz.ac.auckland.se281;

abstract class Venue {

  protected String name;
  protected String code;
  protected String email;

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
  private String bookingCapacity;

  public BookingDetails(String code, String date, String email, String bookingCapacity) {
    this.code = code;
    this.date = date;
    this.email = email;
    this.bookingCapacity = bookingCapacity;
  }

  public String getBookingCapacity() {
    return bookingCapacity;
  }

  public String getEmail() {
    return email;
  }

  public String getDate() {
    return date;
  }
}
