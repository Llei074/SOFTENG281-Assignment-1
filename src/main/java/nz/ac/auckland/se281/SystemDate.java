package nz.ac.auckland.se281;

public class SystemDate {

  private String sysDate;
  private String[] stringDate;
  private int[] intDate = {0, 0, 0};

  public SystemDate(String date) {
    this.sysDate = date;

    if (!sysDate.equals("not set")) {
      stringDate = date.split("/");
      for (int i = 0; i < 3; i++) {
        intDate[i] = Integer.parseInt(stringDate[i]);
      }
    }
  }

  public void setDate(String date) {
    this.sysDate = date;
    stringDate = date.split("/");
    for (int i = 0; i < 3; i++) {
      intDate[i] = Integer.parseInt(stringDate[i]);
    }
  }

  public String getDate() {
    return sysDate;
  }

  public String getSysDay() {
    return stringDate[0];
  }

  public String getSysMonth() {
    return stringDate[1];
  }

  public String getSysYear() {
    return stringDate[2];
  }

  public boolean checkDate(String bookingDate) {
    // Returns true if the system date IS greater than the booking date

    String[] bookingDateParts = bookingDate.split("/");

    // Checks year, month, day
    if (Integer.parseInt(bookingDateParts[2]) < intDate[2]) {
      return true;

    } else if (Integer.parseInt(bookingDateParts[2]) > intDate[2]) {
      return false;

    } else if (Integer.parseInt(bookingDateParts[1]) < intDate[1]) {
      return true;

    } else if (Integer.parseInt(bookingDateParts[1]) > intDate[1]) {
      return false;

    } else if (Integer.parseInt(bookingDateParts[0]) < intDate[0]) {
      return true;
    }

    return false;
  }
}
