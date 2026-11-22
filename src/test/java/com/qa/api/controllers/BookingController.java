package com.qa.api.controllers;

import com.qa.config.BookingConfig;
import com.qa.api.models.BookingDTO;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class BookingController 
{
    public Response CreateBooking(BookingDTO request)
    {
        return given()
                    .baseUri(BookingConfig.BASE_URL)
                    .contentType(JSON)
                    .body(request)
                .when()
                    .post("/booking");
    }   
    
    public Response GetBooking(int id)
    {
        return given()
                    .baseUri(BookingConfig.BASE_URL)
                .when()
                    .get("/booking/{id}", id);
    }
}
