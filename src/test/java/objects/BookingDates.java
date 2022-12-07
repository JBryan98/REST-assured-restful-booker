package objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDates {
    @JsonProperty(value = "checkin") String checkin;
    @JsonProperty(value = "checkout") String checkout;

    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }
}
