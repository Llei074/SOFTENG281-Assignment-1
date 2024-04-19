package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class BookingDetails extends Venue {

  private String bookingDate;
  private String partyDate;
  private String email;
  private String attendees;
  private String reference;
  private VenueDetails venue;
  private ArrayList<CateringType> cateringTypes = new ArrayList<CateringType>();
  private boolean musicService = false;
  private boolean floral = false;
  private FloralType floralType;

  public BookingDetails(String[] options, String ref, String bookingDate, VenueDetails venue) {
    this.code = options[0];
    this.partyDate = options[1];
    this.email = options[2];
    this.attendees = options[3];
    this.reference = ref;
    this.bookingDate = bookingDate;
    this.venue = venue;
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

  public void setCateringType(CateringType cateringType) {
    cateringTypes.add(cateringType);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Catering (" + cateringType.getName() + ")", this.reference);
  }

  public void musicServices() {
    musicService = true;
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", this.reference);
  }

  public void setFloralType(FloralType floralType) {
    this.floralType = floralType;
    this.floral = true;
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Floral (" + floralType.getName() + ")", this.reference);
  }

  public void printInvoiceContent() {
    // tempAnswer calculates the cost as it runs printInvoiceContent
    // Resetting tempAnswer to prevent unwanted errors
    String cateringNames = "";
    int tempAnswer2;
    tempAnswer = 0;

    tempAnswer = Integer.parseInt(getHireFee());
    MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(getHireFee());

    if (!cateringTypes.isEmpty()) {
      for (CateringType CateringType : cateringTypes) {

        // calculate tempAnswer2 for each catering type
        tempAnswer2 = CateringType.getCostPerPerson() * Integer.parseInt(attendees);

        // if statement stores the name of each catering type into the catering string
        if (cateringNames.isEmpty()) {
          cateringNames = "" + CateringType.getName();
        } else {
          cateringNames = cateringNames + "/" + CateringType.getName();
        }
        tempAnswer += tempAnswer2;
      }
      MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
          cateringNames, Integer.toString(tempAnswer - Integer.parseInt(getHireFee())));
    }

    if (this.musicService == true) {

      tempAnswer += 500;
      MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage("500");
    }

    if (this.floral == true) {

      tempAnswer += floralType.getCost();
      MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
          floralType.getName(), Integer.toString(floralType.getCost()));
    }
  }

  public String getTotalAmount() {
    return Integer.toString(tempAnswer);
  }

  @Override
  public String getName() {
    return venue.getName();
  }

  @Override
  public String getHireFee() {
    return venue.getHireFee();
  }

  @Override
  public String getCapacity() {
    return venue.getCapacity();
  }

  public String getAttendees() {
    return attendees;
  }

  public String getEmail() {
    return email;
  }

  public String getPartyDate() {
    return partyDate;
  }

  public String getBookingDate() {
    return bookingDate;
  }

  public String getReference() {
    return reference;
  }
}
