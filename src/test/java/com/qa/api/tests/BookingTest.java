package com.qa.api.tests;

import com.qa.api.models.BookingDTO;
import com.qa.api.models.BookingDates;
import com.qa.api.models.CreateBookingResponse;
import com.qa.api.controllers.BookingController;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingTest 
{
    BookingController controller = new BookingController();

    @Test
    public void ShouldReturn404ForNonExistingBooking() {
        Response response = controller.GetBooking(-1);

        assertEquals(404, response.statusCode());
    }

    @Test
    public void ShouldCreateBooking() 
    {
        BookingDTO request = new BookingDTO(
                "Jim",
                "Brown",
                111,
                true,
                new BookingDates("2027-11-23", "2027-11-30"),
                "Breakfast");

        // POST
        Response createResponse = controller.CreateBooking(request);
        assertEquals(200, createResponse.statusCode());

        CreateBookingResponse bookingCreate = createResponse.as(CreateBookingResponse.class);
        assertTrue(bookingCreate.bookingid() > 0);
        AssertBookingEquals(bookingCreate.booking(), request);

        // GET : I'm not sure what the best practice is for this kind of verification.
        // Is it enough to check the response from the POST request, or should we also
        // verify it with a GET request? In any case, I added a GET request as well,
        // just to be on the safe side.
        Response getResponse = controller.GetBooking(bookingCreate.bookingid());
        assertEquals(200, createResponse.statusCode());

        BookingDTO bookingGet = getResponse.as(BookingDTO.class);
        AssertBookingEquals(bookingGet, request);
    }
    
    private void AssertBookingEquals(BookingDTO actual, BookingDTO expected)
    {
        assertEquals(actual.firstname(), expected.firstname());
        assertEquals(actual.lastname(), expected.lastname());
        assertEquals(actual.totalprice(), expected.totalprice());
        assertEquals(actual.depositpaid(), expected.depositpaid());
        assertEquals(actual.bookingdates(), expected.bookingdates());
        assertEquals(actual.additionalneeds(), expected.additionalneeds());
    }
}
