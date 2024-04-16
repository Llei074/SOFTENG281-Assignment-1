package nz.ac.auckland.se281;

import java.util.ArrayList;

public class VenueDetails extends Venue {


  private ArrayList<BookingDetails> bookings = new ArrayList<BookingDetails>();

  public VenueDetails(String name, String code, String capacityInput, String hireFeeInput) {
    this.name = name;
    this.code = code;
    this.venueCapacity = capacityInput;
    this.hireFee = hireFeeInput;
  }

  @Override 
  public String getName() {
    return this.name;
  }

  @Override
  public String getHireFee() {
    return this.hireFee;
  }

  @Override
  public String getCapacity() {
    return this.venueCapacity;
  }

  public void addBooking(BookingDetails booking) {
    bookings.add(booking);
    booking.setName(this.name);
  }

  public boolean checkBookingDate(String bookingDate) {
    // Returns true if the user is booking an already booked venue date
    // Checks if there has been bookings made
    if (!bookings.isEmpty()) {
      for (BookingDetails booking : bookings) {
        if (booking.getPartyDate().equals(bookingDate)) {
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
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
            booking.getReference(), booking.getPartyDate());
      }
      return;
    }
    MessageCli.PRINT_BOOKINGS_NONE.printMessage(this.name);
  }

  public String nextAvalibleDate(SystemDate sysDate) {

    boolean stop = false;
    int counter = 0;

    // Allows code to run if the date has not been set by returning "%s"
    if (sysDate.getDate().equals("not set")) {
      return "%s";
    }

    // Stores the system day at the begining to get the latest day
    int dayValue = Integer.parseInt(sysDate.getSysDay());

    // Prevents code from running if there is no bookings made
    if (!bookings.isEmpty()) {

      // This code forces the array to continously repeat itself until it has reached the end of the
      // arraylist
      // By breaking out of the for loop when a day has already been booked prevents the checker
      // from skipping a booking
      // This allows the code to sort descending booking dates
      while (stop == false) {
        counter = 0;
        for (BookingDetails booking : bookings) {
          counter++;
          String[] bookingDate = booking.getPartyDate().split("/");
          if (dayValue == Integer.parseInt(bookingDate[0])) {
            dayValue++;
            break;
          } else if (counter == bookings.size()) {
            stop = true;
          }
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
