package objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

public class createBookingPayload {
    @JsonProperty(value = "firstname") String firstName;
    @JsonProperty(value = "lastname") String lastName;
    @JsonProperty(value = "totalprice") Integer totalPrice;
    @JsonProperty(value = "depositpaid") Boolean depositPaid;
    @JsonProperty(value = "bookingdates") BookingDates bookingDates;
    @JsonProperty(value = "additionalneeds") String additionalNeds;

    public createBookingPayload(String firstName, String lastName, Integer totalPrice, Boolean depositPaid, BookingDates bookingDates, String additionalNeds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.bookingDates = bookingDates;
        this.additionalNeds = additionalNeds;
    }
}
