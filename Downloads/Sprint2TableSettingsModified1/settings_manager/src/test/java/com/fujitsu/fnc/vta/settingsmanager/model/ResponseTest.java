package com.fujitsu.fnc.vta.settingsmanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 class ResponseTest {

    private Response response;

    @BeforeEach
     void setUp() {
        response = new Response(false, null, response);
    }
    @Test
     void testBuilder() {
        Response response = Response.builder()
                .status(true)
                .message("Success")
                .data(new Object())
                .build();

        assertTrue(response.isStatus());
        assertEquals("Success", response.getMessage());
        assertNotNull(response.getData());
    }

    @Test
    void testGettersAndSetters() {
        response.setStatus(true);
        response.setMessage("Test Message");
        response.setData("Test Data");

        assertTrue(response.isStatus());
        assertEquals("Test Message", response.getMessage());
        assertEquals("Test Data", response.getData());
    }

    @Test
   void testToString() {
        response.setStatus(true);
        response.setMessage("Test Message");
        response.setData("Test Data");

        String expectedToString = "Response(status=true, message=Test Message, data=Test Data)";
        assertEquals(expectedToString, response.toString());
    }

    @Test
    void testEquals() {
        Response response1 = Response.builder()
                .status(true)
                .message("Message")
                .data("Data")
                .build();

        Response response2 = Response.builder()
                .status(true)
                .message("Message")
                .data("Data")
                .build();

        Response response3 = Response.builder()
                .status(false)
                .message("Other Message")
                .data("Other Data")
                .build();

        assertEquals(response1, response2);
        assertNotEquals(response1, response3);
    }

    @Test
    void testHashCode() {
        Response response1 = Response.builder()
                .status(true)
                .message("Message")
                .data("Data")
                .build();

        Response response2 = Response.builder()
                .status(true)
                .message("Message")
                .data("Data")
                .build();

        Response response3 = Response.builder()
                .status(false)
                .message("Other Message")
                .data("Other Data")
                .build();

        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }
}
