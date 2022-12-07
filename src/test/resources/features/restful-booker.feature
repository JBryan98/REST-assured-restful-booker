Feature: RestFul Booker API

  @Authorization
  Scenario: Auth - Create Token Successfull
    Given User registered on the api
    When I access api request endpoint to create token
      | username | password    |
      | admin    | password123 |
    Then Verify the response status code "200"
    And Verify the response body contains the token

  @Authorization
  Scenario: The token contains 15 characters
    Given User registered on the api
    When I access api request endpoint to create token
      | username | password    |
      | admin    | password123 |
    Then Verify the response status code "200"
    And Verify the token contains "15" characters

  @Authorization
  Scenario: The token allows only alphanumeric characters
    Given User registered on the api
    When I access api request endpoint to create token
      | username | password    |
      | admin    | password123 |
    Then Verify the response status code "200"
    And Verify the token contains only alphanumeric characters

  @GetBooking
  Scenario: Get all booking ids that exists within the API
    Given The access api request endpoint
    When I access the api request endpoint to get all booking ids
    Then Verify the response status code "200"
    And Show the response body

  @GetBooking
  Scenario: Get all booking ids filtered by name
    Given The access api request endpoint
    When I access the api request endpoint with booking name query params
      | firstname | lastname |
      | Sally     | Brown    |
    Then Verify the response status code "200"
    And Show the response body

  @GetBooking
  Scenario: Get all booking ids filtered by checking/checkout date
    Given The access api request endpoint
    When I access the api request endpoint with booking checkin and checkout query params
      | checkin    | checkout   |
      | 2014-03-13 | 2014-05-21 |
    Then Verify the response status code "200"
    And Show the response body

  @CreateBooking
  Scenario: Create a new booking in the API
    Given User not registered on the api
    When I access the api request endpoint to create a new booking
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Johan     | Vicencio | 111        | true        | 2022-12-01 | 2022-12-04 | Breakfast       |
    Then Verify the response status code "200"
    And Verify the response body contains the bookingid

  @UpdateBooking
  Scenario: Update booking
    Given Booking created on the api
    When I access the api request endpoint to update a booking
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Johan     | Vicencio | 100        | true        | 2022-12-01 | 2022-12-03 | Breakfast       |
    Then Verify the response status code "200"
    And Show the response body

  @PatchBooking
  Scenario: Patch booking
    Given Booking created on the api
    When I access the api request endpoint to partial update a booking
      | firstname | lastname |
      | Bryan     | Corrales |
    Then Verify the response status code "200"
    And  Show the response body

  @DeleteBooking
  Scenario: Delete booking
    Given Booking created on the api
    When I access the api request endpoint to delete a booking
    Then Verify the response status code "201"
    And Show the response body

  @Ping
  Scenario: Ping - HealthCheck
    Given An endpoint to confirm whether the API is up and running
    When I access the api request endpoint to confirm the API is running
    Then Verify the response status code "201"
    And Show the response body
