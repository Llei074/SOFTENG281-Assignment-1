package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  public VenueHireSystem() {}

  private ArrayList<VenueDetails> venues = new ArrayList<VenueDetails>();
  private ArrayList<BookingDetails> bookings = new ArrayList<BookingDetails>();
  private String[] intToWord = {
    "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
  };
  private String systemDate = "not set";
  private boolean stop;

  public void printVenues() {
    if (venues.isEmpty() == true) { // Checks if the venue array list is empty
      MessageCli.NO_VENUES.printMessage(); // Currently no venues
      return;
    } else if (venues.size() == 1) { // Sentence format for one venue
      MessageCli.NUMBER_VENUES.printMessage("is", intToWord[venues.size()], "");
    } else if (venues.size() < 10) { // Sentence format for more than one venue
      MessageCli.NUMBER_VENUES.printMessage("are", intToWord[venues.size()], "s");
    } else {
      MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(venues.size()), "s");
    }
    for (VenueDetails venue : venues) {
      MessageCli.VENUE_ENTRY.printMessage(
          venue.getName(), venue.getCode(), venue.getCapacity(), venue.gethirefee(), "%s");
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    // Checking Empty Venue Name
    if (venueName.trim().isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // Rejecting Duplicate Venue Codes
    if (venues.isEmpty() != true) { // This will skip if there are no venues in the venues array
      for (VenueDetails venue : venues) {
        if (venue.getCode().equals(venueCode)) {
          MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venue.getName());
          return;
        }
      }
    }

    // Invalid Capacity Input or Invalid Hire Fee Input
    try {
      if (Integer.parseInt(hireFeeInput) <= 0) { // Hire fee is not a positive integer or zero
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        return;
      }
    } catch (NumberFormatException nfe) { // Hire fee is not a number
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }
    try {
      if (Integer.parseInt(capacityInput) <= 0) { // Capacity is not a positive integer or zero
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        return;
      }
    } catch (NumberFormatException nfe) { // capacity is not a number
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }

    VenueDetails venue = new VenueDetails(venueName, venueCode, capacityInput, hireFeeInput);
    venues.add(venue);
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  public void setSystemDate(String dateInput) {
    systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(systemDate);
  }

  public void printSystemDate() {
    if (systemDate == "not set") {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    } else {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    }
  }

  public void makeBooking(String[] options) {
    // Scenarios where a booking should not be made

    if (systemDate == "not set") {

      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage(); // Date not set
      return;

    } else if (venues.isEmpty() == true) {

      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage(); // No venues in the system
      return;

    } else if (checkDate(systemDate, options[1])) {

      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], systemDate);
      return;

    } else {

      // Verifies the input code with codes in the system
      stop = true;
      for (VenueDetails venue : venues) {
        if (venue.getCode().equals(options[0])) {
          stop = false;
          break;
        }
      }

      if (stop == true) {

        stop = false;

        MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
        return;
      }

    }

    BookingDetails booking = new BookingDetails(options[0], options[1], options[2], options[3]);
    bookings.add(booking);
    booking.storeReference(BookingReferenceGenerator.generateBookingReference());
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        booking.getReference(), booking.getCode(), booking.getDate(), booking.getAttendees());
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }

  public boolean checkDate(String sysDate, String bookingDate) {
    // Returns true if the date IS NOT the same

    String[] sysDateParts = sysDate.split("/");
    String[] bookingDateParts = bookingDate.split("/");

    // Checks year, month, day
    if (Integer.parseInt(bookingDateParts[2]) < Integer.parseInt(sysDateParts[2])) {
      return true;

    } else if (Integer.parseInt(bookingDateParts[2]) > Integer.parseInt(sysDateParts[2])) {
      return false;

    } else if (Integer.parseInt(bookingDateParts[1]) < Integer.parseInt(sysDateParts[1])) {
      return true;

    } else if (Integer.parseInt(bookingDateParts[1]) > Integer.parseInt(sysDateParts[1])) {
      return false;
      
    } else if (Integer.parseInt(bookingDateParts[0]) < Integer.parseInt(sysDateParts[0])) {
      return true;
    }

    return false;
  }
}
