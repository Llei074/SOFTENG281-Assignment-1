package nz.ac.auckland.se281;

import java.util.ArrayList;

public class VenueDetails extends Venue {

  private String venueCapacity;
  private String hireFee;
  private ArrayList<BookingDetails> bookings = new ArrayList<BookingDetails>();

  public VenueDetails(String name, String code, String capacityInput, String hireFeeInput) {
    this.name = name;
    this.code = code;
    this.venueCapacity = capacityInput;
    this.hireFee = hireFeeInput;
  }

  public String getCapacity() {
    return venueCapacity;
  }

  public void addBooking(BookingDetails booking) {
    bookings.add(booking);
    booking.setName(this.name);
  }

  public String gethirefee() {
    return hireFee;
  }

  public boolean checkBookingDate(String bookingDate) {
    // Returns true if the user is booking an already booked venue date
    // Checks if there has been bookings made
    if (!bookings.isEmpty()) {
      for (BookingDetails booking : bookings) {
        if (booking.getDate().equals(bookingDate)) {
          return true;
        }
      }
    }
    // Loop through bookings and check if the user input date (options[2]) is equal/already booked
    return false;
  }

  public void printVenueBookings() {

    // Skips the loop if no bookings have been made
    if (!bookings.isEmpty()) {
      for (BookingDetails booking : bookings) {
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(booking.getReference(), booking.getDate());
      }
      return;
    }
    MessageCli.PRINT_BOOKINGS_NONE.printMessage(this.name);
  }

  public String nextAvalibleDate(SystemDate sysDate) {

    if (sysDate.getDate() == "not set") {
      return "%s";
    }

    int dayValue = Integer.parseInt(sysDate.getSysDay());

    if (!bookings.isEmpty()) {
      for (BookingDetails booking : bookings) {
        String[] bookingDate = booking.getDate().split("/");
        if (dayValue == Integer.parseInt(bookingDate[0])) {
          dayValue++;
        }
      }
      if (dayValue < 10) {
        return "0"
            + Integer.toString(dayValue)
            + "/"
            + sysDate.getSysMonth()
            + "/"
            + sysDate.getSysYear();
      } else {
        return Integer.toString(dayValue)
            + "/"
            + sysDate.getSysMonth()
            + "/"
            + sysDate.getSysYear();
      }
    }
    return sysDate.getDate();
  }
}
