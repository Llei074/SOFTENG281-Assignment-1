package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  public VenueHireSystem() {}

  private ArrayList<VenueDetails> venues = new ArrayList<VenueDetails>();
  private String[] intToWord = {
    "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
  };
  SystemDate sysDate = new SystemDate("not set");

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
          venue.getName(), venue.getCode(), venue.getCapacity(), venue.gethirefee(), venue.nextAvalibleDate(sysDate));
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
    if (!venues.isEmpty()) { // This will skip if there are no venues in the venues array
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
    sysDate.setDate(dateInput);
    MessageCli.DATE_SET.printMessage(sysDate.getDate());
  }

  public void printSystemDate() {
    MessageCli.CURRENT_DATE.printMessage(sysDate.getDate());
  }

  public void makeBooking(String[] options) {
    // Scenarios where a booking should not be made

    if (sysDate.getDate().equals("not set")) {

      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage(); // Date not set
      return;

    } else if (venues.isEmpty()) {

      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage(); // No venues in the system
      return;

    } else if (sysDate.checkDate(options[1])) {

      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], sysDate.getDate());
      return;
    }
    // Verifies the input code with codes in the system
    for (VenueDetails venue : venues) {
      if (venue.getCode().equals(options[0])) {
        // With the confirmed venue code we can check through the booked dates and check if the
        // venue is already booked
        if (venue.checkBookingDate(options[1])) {
          MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
              venue.getName(), options[1]);
          return;
        }

        BookingDetails booking =
            new BookingDetails(options, BookingReferenceGenerator.generateBookingReference());
        venue.addBooking(booking);
        booking.checkCapacity(venue.getCapacity());
        MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
            booking.getReference(), booking.getName(), booking.getDate(), booking.getAttendees());
        return;
      }
    }

    // Could not match venue code with the venue list
    MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
    return;
  }

  public void printBookings(String venueCode) {
    for (VenueDetails venue : venues) {
      if (venueCode.equals(venue.getCode())) {
        MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venue.getName());
        venue.printVenueBookings();
        return;
      }
    }
    MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
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

}
