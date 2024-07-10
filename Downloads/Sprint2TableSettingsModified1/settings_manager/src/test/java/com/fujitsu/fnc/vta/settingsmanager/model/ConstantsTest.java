package com.fujitsu.fnc.vta.settingsmanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

 class ConstantsTest {
    @Test
     void testConstantsValues() {
        assertEquals("Creating the filter :  ", Constants.FILTER);
        assertEquals("Get the filter for user : ", Constants.FILTER_GET_ID);
        assertEquals("Get the filter : ", Constants.FILTER_GET);
        assertEquals("{}: {}", Constants.BRACKETS);
        assertEquals("Successfully Inserted into Database", Constants.SUCCESS);
        assertEquals("Failed to Insert", Constants.FAILURE);
        assertEquals("Successfully Fetched", Constants.OK);
        assertEquals("No Data Found", Constants.NODATA);
        assertEquals("Resource already exists", Constants.RESOURCE_EXISTS);
        assertEquals("Invalid data", Constants.INVALID_DATA);
        assertEquals("Entity name should not be null", Constants.NOT_NULL);
        assertEquals("Entity name should not be empty", Constants.NOT_EMPTY);
        assertEquals("Entity profile name should not be null", Constants.ENOT_NULL);
        assertEquals("Entity profile name should not be empty", Constants.ENOT_EMPTY);
        assertEquals("This field must not be empty", Constants.NODE_NOT_EMPTY);
        assertEquals("Error message in Get filters :   ", Constants.ERROR);
        assertEquals("userId", Constants.USERID);
        assertEquals("serviceName", Constants.TABLENAME);
    }
}
