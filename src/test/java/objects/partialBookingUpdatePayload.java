package objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class partialBookingUpdatePayload {
    @JsonProperty(value = "firstname") String firstname;
    @JsonProperty(value = "lastname") String lastname;

    public partialBookingUpdatePayload(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
