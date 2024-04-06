package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  public VenueHireSystem() {}

  private ArrayList<venueDetails> venues = new ArrayList<venueDetails>();
  private String[] IntToWord = {
    "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
  };
  private String SystemDate = "not set";
  private boolean Stop = true;

  public void printVenues() {
    if (venues.isEmpty() == true) { // Checks if the venue array list is empty
      MessageCli.NO_VENUES.printMessage(); // Currently no venues
      return;
    } else if (venues.size() == 1) { // Sentence format for one venue
      MessageCli.NUMBER_VENUES.printMessage("is", IntToWord[venues.size()], "");
    } else if (venues.size() < 10) { // Sentence format for more than one venue
      MessageCli.NUMBER_VENUES.printMessage("are", IntToWord[venues.size()], "s");
    } else {
      MessageCli.NUMBER_VENUES.printMessage("are", String.valueOf(venues.size()), "s");
    }
    for (venueDetails venue : venues) {
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
      for (venueDetails venue : venues) {
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

    venueDetails venue = new venueDetails(venueName, venueCode, capacityInput, hireFeeInput);
    venues.add(venue);
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  public void setSystemDate(String dateInput) {
    SystemDate = dateInput;
    MessageCli.DATE_SET.printMessage(SystemDate);
  }

  public void printSystemDate() {
    if (SystemDate == "not set") {
      MessageCli.CURRENT_DATE.printMessage(SystemDate);
    } else {
      MessageCli.CURRENT_DATE.printMessage(SystemDate);
    }
  }

  public void makeBooking(String[] options) {
    // Scenarios where a booking should not be made

    if (SystemDate == "not set") {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage(); // Date not set
      return;
    } else if (venues.isEmpty() == true) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage(); // No venues in the system
      return;
    } else {

      // Verifies the client code with codes in the system
      Stop = true;
      for (venueDetails venue : venues) {
        if (venue.getCode().equals(options[0])) {

          Stop = false;

          break;
        }
      }

      if (Stop == true) {

        Stop = false;

        MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
        return;
      }
    }

    // TODO

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
    // TODO implement this method
    return true;
  }
}
