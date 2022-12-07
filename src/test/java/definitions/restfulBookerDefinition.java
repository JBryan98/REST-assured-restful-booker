package definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import support.restfulBookerRequests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class restfulBookerDefinition {
    restfulBookerRequests restfulBooker;

    private static String token;
    private static String bookingid;

    public restfulBookerDefinition(){
        restfulBooker = new restfulBookerRequests();
    }

    @Given("User registered on the api")
    public void userRegisteredOnTheApi() {
    }

    @When("I access api request endpoint to create token")
    public void iAccessApiRequestEndpointToCreateToken(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for(Map<String, String> dt:data){
            restfulBooker.authCreateToken(dt.get("username"), dt.get("password"));
        }
    }

    @Then("Verify the response status code {string}")
    public void verifyTheResponseStatusCode(String statusCode) {
        System.out.println("Código de respuesta: " + restfulBookerRequests.restfulBookerResponse.getStatusCode());
        Assert.assertEquals(Integer.parseInt(statusCode), restfulBookerRequests.restfulBookerResponse.getStatusCode());
    }

    @And("Verify the response body contains the token")
    public void verifyTheTokenExists() {
        ResponseBody<Response> body = restfulBookerRequests.restfulBookerResponse;
        System.out.println(body.asString());
        //Assert.assertNotEquals("{}", body.asString());
        Assert.assertTrue(body.asString().contains("\"token\":"));
    }

    @And("Verify the token contains {string} characters")
    public void verifyTheTokenContainsCharacters(String charactersLength) {
        ResponseBody<Response> body = restfulBookerRequests.restfulBookerResponse;
        JsonPath json = new JsonPath(body.asString());
        token = json.getString("token");
        Assert.assertEquals(Integer.parseInt(charactersLength), token.length());
    }

    @And("Verify the token contains only alphanumeric characters")
    public void verifyTheTokenContainsOnlyAlphanumericCharacters() {
        Assert.assertFalse(token.contains("^[a-zA-Z0-9]*$"));
    }


    @Given("The access api request endpoint")
    public void theAccessApiRequestEndpoint() {
        restfulBooker.getBookingIds();
    }

    @When("I access the api request endpoint to get all booking ids")
    public void iAccesTheApiRequestEndpointToGetAllBookingIds() {
        ResponseBody<Response> body = restfulBookerRequests.restfulBookerResponse;
        System.out.println(body.asString());
    }

    @And("Show the response body")
    public void showTheResponseBody() {
        ResponseBody<Response> body = restfulBookerRequests.restfulBookerResponse;
        System.out.println(body.asString());
    }

    @When("I access the api request endpoint with booking name query params")
    public void iAccesTheApiRequestEndpointWithBookingNameQueryParams(DataTable dataTable){
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for(int i = 0; i < data.size(); i++){
            restfulBooker.getBookingIdsByName(data.get(i).get("firstname"), data.get(i).get("lastname"));
        }
    }

    @When("I access the api request endpoint with booking checkin and checkout query params")
    public void iAccessTheApiRequestEndpointWithBookingCheckingAndCheckoutQueryParams(DataTable dataTable){
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for(int i = 0; i < data.size(); i++){
            restfulBooker.getBookingIdsByCheckinCheckout(data.get(i).get("checkin"), data.get(i).get("checkout"));
        }
    }


    @Given("User not registered on the api")
    public void userNotRegisteredOnTheApi() {
    }

    @When("I access the api request endpoint to create a new booking")
    public void iAccesTheApiRequestEndpointToCreateANewBooking(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < data.size(); i++){
            restfulBooker.createBooking(data.get(i).get("firstname"),
                                        data.get(i).get("lastname"),
                                        data.get(i).get("totalprice"),
                                        data.get(i).get("depositpaid"),
                                        data.get(i).get("checkin"),
                                        data.get(i).get("checkout"),
                                        data.get(i).get("additionalneeds"));
        }
    }

    @And("Verify the response body contains the bookingid")
    public void verifyTheResponseBodyContainsTheBookingid() {
        ResponseBody<Response> body = restfulBookerRequests.restfulBookerResponse;
        System.out.println(body.asString());
        JsonPath json = new JsonPath(body.asString());
        bookingid = json.getString("bookingid");
        Assert.assertTrue(body.asString().contains("bookingid"));
    }

    @Given("Booking created on the api")
    public void bookingCreatedOnTheApi() {
        System.out.println("El id del booking es: " + bookingid);
    }

    @When("I access the api request endpoint to update a booking")
    public void iAccesTheApiRequestEndpointToUpdateABooking(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < data.size(); i++){
            restfulBooker.updateBooking(bookingid,
                                        data.get(i).get("firstname"),
                                        data.get(i).get("lastname"),
                                        data.get(i).get("totalprice"),
                                        data.get(i).get("depositpaid"),
                                        data.get(i).get("checkin"),
                                        data.get(i).get("checkout"),
                                        data.get(i).get("additionalneeds"),
                                        token);
        }
    }


    @When("I access the api request endpoint to partial update a booking")
    public void iAccesTheApiRequestEndpointToPartialUpdateABooking(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for(int i = 0; i < data.size(); i++){
            restfulBooker.partialUpdateBooking(bookingid,
                                               data.get(i).get("firstname"),
                                               data.get(i).get("lastname"),
                                               token);
        }
    }

    @When("I access the api request endpoint to delete a booking")
    public void iAccesTheApiRequestEndpointToDeleteABooking() {
        System.out.println("El id del booking borrado es : " + bookingid);
        restfulBooker.deleteBooking(bookingid, token);
    }

    @Given("An endpoint to confirm whether the API is up and running")
    public void anEndpointToConfirmWhetherTheAPIIsUpAndRunning() {
    }

    @When("I access the api request endpoint to confirm the API is running")
    public void iAccesTheApiRequestEndpointToConfirmTheAPIIsRunning() {
        restfulBooker.healthCheck();
    }
}
