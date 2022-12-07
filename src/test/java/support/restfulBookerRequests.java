package support;

import io.restassured.response.Response;
import objects.BookingDates;
import objects.authUserPayload;
import objects.createBookingPayload;
import objects.partialBookingUpdatePayload;

public class restfulBookerRequests {
    apiHelper api;
    public static Response restfulBookerResponse;

    authUserPayload authUser;
    createBookingPayload bookingPayload;

    BookingDates bookingDates;

    partialBookingUpdatePayload partialBookingPayload;

    public restfulBookerRequests(){
        api = new apiHelper();
    }

    public void getBookingIds(){
        String url = "https://restful-booker.herokuapp.com/booking";
        restfulBookerResponse = api.get(url);
    }

    public void getBookingIdsByName(String firstName, String lastName){
        String url = "https://restful-booker.herokuapp.com/booking";
        restfulBookerResponse = api.getFilteredByName(url, firstName, lastName);
    }

    public void getBookingIdsByCheckinCheckout(String checkin, String checkout){
        String url = "https://restful-booker.herokuapp.com/booking";
        restfulBookerResponse = api.getFilteredByCheckinCheckout(url, checkin, checkout);
    }

    public void authCreateToken(String username, String password){
        String url = "https://restful-booker.herokuapp.com/auth";
        authUser = new authUserPayload(username, password);
        restfulBookerResponse = api.post(url, authUser);
    }


    public void createBooking(String firstName, String lastName, String totalPrice, String depositPaid, String checkin, String checkout, String additionalNeeds){
        String url= "https://restful-booker.herokuapp.com/booking";
        bookingDates = new BookingDates(checkin, checkout);
        bookingPayload = new createBookingPayload(firstName,lastName, Integer.parseInt(totalPrice), Boolean.parseBoolean(depositPaid), bookingDates, additionalNeeds);
        restfulBookerResponse = api.post(url, bookingPayload);
    }

    public void updateBooking(String id, String firstName, String lastName, String totalPrice, String depositPaid, String checkin, String checkout, String additionalNeeds,String token){
        String url ="https://restful-booker.herokuapp.com/booking/" + id;
        bookingDates = new BookingDates(checkin, checkout);
        bookingPayload = new createBookingPayload(firstName,lastName, Integer.parseInt(totalPrice), Boolean.parseBoolean(depositPaid), bookingDates, additionalNeeds);
        restfulBookerResponse = api.put(url, bookingPayload, token);
    }

    public void partialUpdateBooking(String id, String firstName, String lastName, String token){
        String url = "https://restful-booker.herokuapp.com/booking/" + id;
        partialBookingPayload = new partialBookingUpdatePayload(firstName, lastName);
        restfulBookerResponse = api.patch(url, partialBookingPayload, token);
    }

    public void deleteBooking(String id, String token){
        String url = "https://restful-booker.herokuapp.com/booking/" + id;
        restfulBookerResponse = api.delete(url, token);
    }

    public void healthCheck(){
        String url = "https://restful-booker.herokuapp.com/ping";
        restfulBookerResponse = api.get(url);
    }
}
