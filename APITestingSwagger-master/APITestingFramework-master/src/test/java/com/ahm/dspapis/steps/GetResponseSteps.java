package com.ahm.dspapis.steps;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetResponseSteps {

    public Response response;

      public  String url = "jdbc:mysql://localhost:3306/apitestingformat";
      public  String user = "root";
      public  String password = "Root123";


        @Given("clean the database")
    public void cleanTheDatabase() {


    }



    @When("I retrieve booking details for firstname Mark")
    public void iRetrieveBookingDetailsForFirstnameMark() {
        Response idsResponse = RestAssured.get("https://restful-booker.herokuapp.com/booking?firstname=Jim");
        List<Integer> bookingIds = idsResponse.jsonPath().getList("bookingid", Integer.class);

        // Iterate over each bookingId and fetch booking details
        for (Integer bookingId : bookingIds) {
            response = RestAssured.get("https://restful-booker.herokuapp.com/booking/" + bookingId);
            String jsonString = response.getBody().asPrettyString();
            JsonPath jsonPath = new JsonPath(jsonString);

            // Extract data from JSON object
            String firstname = jsonPath.getString("firstname");
            String lastname = jsonPath.getString("lastname");
            int totalprice = jsonPath.getInt("totalprice");
            boolean depositpaid = jsonPath.getBoolean("depositpaid");
            String checkin = jsonPath.getString("bookingdates.checkin");
            String checkout = jsonPath.getString("bookingdates.checkout");
            String additionalneeds = jsonPath.getString("additionalneeds");

            // Insert the data into MySQL database
            insertIntoDatabase(bookingId, firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
        }
    }

    @Then("I print the booking details as inside the mysql database")
    public void iPrintTheBookingDetailsAsInsideTheMysqlDatabase() {
        // This step can be used to verify the data in the database if needed

    }


    public void insertIntoDatabase(int bookid, String firstname, String lastname, int totalprice, boolean depositpaid,
                                   String checkin, String checkout, String additionalneeds) {


        String sql = "INSERT INTO BOOKDETAILS (bookid, firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookid);
            pstmt.setString(2, firstname);
            pstmt.setString(3, lastname);
            pstmt.setInt(4, totalprice);
            pstmt.setBoolean(5, depositpaid);
            pstmt.setString(6, checkin);
            pstmt.setString(7, checkout);
            pstmt.setString(8, additionalneeds);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new row was inserted successfully for booking ID: " + bookid);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
