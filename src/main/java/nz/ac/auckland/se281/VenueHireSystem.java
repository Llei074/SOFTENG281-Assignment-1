package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

import java.util.ArrayList;

public class VenueHireSystem {

  public VenueHireSystem() {
    
  }

  public void printVenues() {
    // TODO implement this method
    System.out.println("There are no venues in the system. Please create a venue first.");
  }

  private ArrayList<venueDetails> venues = new ArrayList<venueDetails>();

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
        
      //Checking Empty Venue Name
      if (venueName.trim().isEmpty()) {
        MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
        return;
      }

      //Rejecting Duplicate Venue Codes

      //Invalid Capacity Input or Invalid Hire Fee Input
      
        //Hire fee is not a number

        //Capacity is not a number

        //Hire fee is not a positive integer

       //Capacity is not a positive interger

      venueDetails venue = new venueDetails(venueName, venueCode, capacityInput, hireFeeInput);
      venues.add(venue);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName,venueCode);
  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
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
}
