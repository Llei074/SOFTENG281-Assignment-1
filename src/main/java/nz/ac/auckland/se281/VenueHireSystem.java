package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

import java.util.ArrayList;

public class VenueHireSystem {

  public VenueHireSystem() {
    
  }
  private ArrayList<venueDetails> venues = new ArrayList<venueDetails>();

  public void printVenues() {
    if (venues.isEmpty() == true) {
      System.out.println("There are no venues in the system. Please create a venue first.");
      
    } if(venues.size() == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is","one","",":");

      for (venueDetails venue : venues) {
        MessageCli.VENUE_ENTRY.printMessage(venue.getName(),venue.getCode(),venue.getCapacity(),venue.gethirefee(),"%s");
      }

    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
        
      //Checking Empty Venue Name
      if (venueName.trim().isEmpty()) {
        MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
        return;
      }

      //Rejecting Duplicate Venue Codes
      if (venues.isEmpty() != true) {
        for (venueDetails venue : venues) {
          if (venue.getCode().equals(venueCode)) {
            MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venue.getName());
            return;
          }
        }
      }


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
