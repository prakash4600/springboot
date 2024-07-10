package com.fujitsu.fnc.vta.settingsmanager.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SortListTest {

    @Test
     void testGettersAndSetters() {
        // Create an instance of SortList
        SortList sortList = new SortList();

        // Set values
        String columnName = "testColumn";
        String order = "asc";
        sortList.setColumnName(columnName);
        sortList.setOrder(order);

        // Assert values
        assertEquals(columnName, sortList.getColumnName());
        assertEquals(order, sortList.getOrder());
    }

    @Test
    void testToString() {
        // Create an instance of SortList and set values
        SortList sortList = new SortList();
        sortList.setColumnName("testColumn");
        sortList.setOrder("asc");

        // Assert toString contains the field values
        String toString = sortList.toString();
        assertTrue(toString.contains("testColumn"));
        assertTrue(toString.contains("asc"));
    }

    @Test
    void testEqualsAndHashCode() {
        // Create two instances of SortList with the same values
        SortList sortList1 = new SortList();
        sortList1.setColumnName("testColumn");
        sortList1.setOrder("asc");

        SortList sortList2 = new SortList();
        sortList2.setColumnName("testColumn");
        sortList2.setOrder("asc");

        // Assert equals and hashCode methods
        assertEquals(sortList1, sortList2);
        assertEquals(sortList1.hashCode(), sortList2.hashCode());
    }
}
