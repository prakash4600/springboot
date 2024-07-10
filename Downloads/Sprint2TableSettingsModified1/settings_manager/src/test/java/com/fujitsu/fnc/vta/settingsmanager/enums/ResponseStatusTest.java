package com.fujitsu.fnc.vta.settingsmanager.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

 class ResponseStatusTest {

    @Test
     void testResponseStatus() {
        ResponseStatus status = ResponseStatus.SUCCESS;

        assertEquals("Successfully Inserted into Database", status.getMessage());
        assertEquals(HttpStatus.CREATED, status.getHttpStatus());
    }
}

