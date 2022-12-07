package support;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class apiHelper {
    public Response get(String url){
        return given().get(url);
    }

    public Response getFilteredByName(String url, String firstName, String lastName){
        return given()
                .queryParam("firstname", firstName)
                .queryParam("lastname", lastName)
                .get(url);
    }

    public Response getFilteredByCheckinCheckout(String url, String checkin, String checkout){
        return given()
                .queryParam("checkin", checkin)
                .queryParam("checkout", checkout)
                .get(url);
    }
    public Response post(String url, Object payload){
        return given()
                .body(payload)
                .contentType("application/json")
                .post(url);
    }

    public Response put(String url, Object payload, String token){
        return given()
                .header("Cookie", "token=" + token)
                .body(payload)
                .contentType("application/json")
                .put(url);
    }

    public Response patch(String url, Object payload, String token){
        return given()
                .header("Cookie", "token=" + token)
                .body(payload)
                .contentType("application/json")
                .patch(url);
    }

    public Response delete(String url, String token){
        return given()
                .header("Cookie", "token=" + token)
                .contentType("application/json")
                .delete(url);
    }
}
