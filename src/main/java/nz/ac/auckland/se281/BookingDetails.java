package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;

public class BookingDetails extends Venue {

  private String date;
  private String email;
  private String attendees;
  private String reference;

  ArrayList<CateringType> CateringTypes = new ArrayList<CateringType>();

  public BookingDetails(String[] options, String ref) {
    this.code = options[0];
    this.date = options[1];
    this.email = options[2];
    this.attendees = options[3];
    this.reference = ref;
  }

  public void setCateringType(CateringType cateringType) {
    CateringTypes.add(cateringType);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Catering (" + cateringType.getName() + ")", this.reference);
  }

  public void checkCapacity(String capcacity) {

    // Reduce repeated calculations by storing it in tempAnswer
    tempAnswer = Integer.parseInt(capcacity) / 4;

    if (tempAnswer > Integer.parseInt(this.attendees)) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          attendees, Integer.toString(tempAnswer), capcacity);
      this.attendees = Integer.toString(tempAnswer);
    } else if (Integer.parseInt(this.attendees) > Integer.parseInt(capcacity)) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(attendees, capcacity, capcacity);
      this.attendees = capcacity;
    }
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
