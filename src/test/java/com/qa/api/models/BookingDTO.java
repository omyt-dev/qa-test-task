package com.qa.api.models;

public record BookingDTO(
        String firstname,
        String lastname,
        int totalprice,
        boolean depositpaid,
        BookingDates bookingdates,
        String additionalneeds) 
{
}
