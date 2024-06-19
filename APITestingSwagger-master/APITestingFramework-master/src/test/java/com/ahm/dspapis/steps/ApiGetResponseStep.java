package com.ahm.dspapis.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.sql.*;
import java.util.List;

public class ApiGetResponseStep {
    public final String dbUrl = "jdbc:mysql://localhost:3306/apitestingformat";
    public final String dbUser = "root";
    public final String dbPassword = "Root123";
    public String expectedResult;
    public String actualResult;
    public String firstname = "Jim";

    @When("I clean the db")
    public void iCleanTheDb() {
        String deleteSql = "DELETE FROM BOOKDETAILS";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {

            pstmt.executeUpdate();
//            System.out.println("All rows deleted from BOOKDETAILS table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @When("I set expectedres")
    public void iSetExpectedres() {

        Response idsResponse = RestAssured.get("https://restful-booker.herokuapp.com/booking?firstname=" + firstname);
        List<Integer> bookingIds = idsResponse.jsonPath().getList("bookingid", Integer.class);

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode expectedBookingsArray = mapper.createArrayNode();

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {

            for (Integer bookingId : bookingIds) {
                Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/" + bookingId);
                String jsonString = response.getBody().asString();
                JsonPath jsonPath = new JsonPath(jsonString);

                ObjectNode bookingNode = mapper.createObjectNode();
                bookingNode.put("firstname", jsonPath.getString("firstname"));
                bookingNode.put("lastname", jsonPath.getString("lastname"));
                bookingNode.put("totalprice", jsonPath.getInt("totalprice"));
                bookingNode.put("depositpaid", jsonPath.getBoolean("depositpaid"));

                ObjectNode bookingDatesNode = mapper.createObjectNode();
                bookingDatesNode.put("checkin", jsonPath.getString("bookingdates.checkin"));
                bookingDatesNode.put("checkout", jsonPath.getString("bookingdates.checkout"));
                bookingNode.set("bookingdates", bookingDatesNode);

                String additionalneeds = jsonPath.getString("additionalneeds");
                if (additionalneeds != null) {
                    bookingNode.put("additionalneeds", additionalneeds);
                }

                expectedBookingsArray.add(bookingNode);

                String insertSql = "INSERT INTO BOOKDETAILS (firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement insertPstmt = conn.prepareStatement(insertSql)) {
                    insertPstmt.setString(1, jsonPath.getString("firstname"));
                    insertPstmt.setString(2, jsonPath.getString("lastname"));
                    insertPstmt.setInt(3, jsonPath.getInt("totalprice"));
                    insertPstmt.setBoolean(4, jsonPath.getBoolean("depositpaid"));
                    insertPstmt.setString(5, jsonPath.getString("bookingdates.checkin"));
                    insertPstmt.setString(6, jsonPath.getString("bookingdates.checkout"));
                    insertPstmt.setString(7, additionalneeds);
                    insertPstmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            expectedResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(expectedBookingsArray);
//            System.out.println("Expected booking details for " + firstname + " in JSON:");
//            System.out.println(expectedResult);

        } catch (SQLException | com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Then("I get actualresu")
    public void iGetActualresu() {

        String sql = "SELECT firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds " +
                "FROM BOOKDETAILS " +
                "WHERE firstname = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, firstname);
            ResultSet rs = pstmt.executeQuery();
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode actualBookingsArray = mapper.createArrayNode();

            while (rs.next()) {
                String lastname = rs.getString("lastname");
                int totalprice = rs.getInt("totalprice");
                boolean depositpaid = rs.getBoolean("depositpaid");
                String checkin = rs.getString("checkin");
                String checkout = rs.getString("checkout");
                String additionalneeds = rs.getString("additionalneeds");

                ObjectNode bookingNode = mapper.createObjectNode();
                bookingNode.put("firstname", firstname);
                bookingNode.put("lastname", lastname);
                bookingNode.put("totalprice", totalprice);
                bookingNode.put("depositpaid", depositpaid);

                ObjectNode bookingDatesNode = mapper.createObjectNode();
                bookingDatesNode.put("checkin", checkin);
                bookingDatesNode.put("checkout", checkout);
                bookingNode.set("bookingdates", bookingDatesNode);

                if (additionalneeds != null) {
                    bookingNode.put("additionalneeds", additionalneeds);
                }

                actualBookingsArray.add(bookingNode);
            }

            actualResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(actualBookingsArray);
//            System.out.println("Actual booking details for " + firstname + " in JSON:");
//            System.out.println(actualResult);

        } catch (SQLException | com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Then("I compare expected and actual results")
    public void iCompareExpectedAndActualResults() {
        Assert.assertEquals("Expected and actual results should match", expectedResult, actualResult);
    }
}
