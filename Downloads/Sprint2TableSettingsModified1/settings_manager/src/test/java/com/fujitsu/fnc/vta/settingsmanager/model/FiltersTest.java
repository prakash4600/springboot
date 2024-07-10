package com.fujitsu.fnc.vta.settingsmanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FiltersTest {

    private Filters filters;
    private SortingSet sortingOrder;
    private FilterSet filter;

    @BeforeEach
    void setUp() {
        sortingOrder = new SortingSet();
        filter = new FilterSet();
        filters = new Filters("filter1", "user1", "service1", true, false, 
                Collections.singletonList("column1"), sortingOrder, filter, "tableSettings");
    }

    @Test
    void testDefaultConstructor() {
        Filters defaultFilters = new Filters();
        assertNull(defaultFilters.getFilterName());
        assertNull(defaultFilters.getUserId());
        assertNull(defaultFilters.getServiceName());
        assertNull(defaultFilters.getColumnNames());
        assertNull(defaultFilters.getSortingOrder());
        assertNull(defaultFilters.getFilter());
        assertNull(defaultFilters.getTableSettings());
        assertNull(defaultFilters.getSave());
        assertNull(defaultFilters.getMakeMyDefault());
        assertNull(defaultFilters.getTimeStamp());
        assertEquals(0, defaultFilters.getPageSize());
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals("filter1", filters.getFilterName());
        assertEquals("user1", filters.getUserId());
        assertEquals("service1", filters.getServiceName());
        assertEquals(Collections.singletonList("column1"), filters.getColumnNames());
        assertEquals(sortingOrder, filters.getSortingOrder());
        assertEquals(filter, filters.getFilter());
        assertEquals("tableSettings", filters.getTableSettings());
        assertEquals(true, filters.getSave());
        assertEquals(false, filters.getMakeMyDefault());
        assertNull(filters.getTimeStamp());
        assertEquals(0, filters.getPageSize());
    }

    @Test
    void testSetterAndGetters() {
        Filters newFilters = new Filters();
        newFilters.setFilterName("filter2");
        newFilters.setUserId("user2");
        newFilters.setServiceName("service2");
        List<String> columnNames = Collections.singletonList("column2");
        newFilters.setColumnNames(columnNames);
        SortingSet newSortingOrder = new SortingSet();
        newFilters.setSortingOrder(newSortingOrder);
        FilterSet newFilter = new FilterSet();
        newFilters.setFilter(newFilter);
        newFilters.setTableSettings("newTableSettings");
        newFilters.setSave(false);
        newFilters.setMakeMyDefault(true);
        newFilters.setTimeStamp("timestamp");
        newFilters.setPageSize(10);

        assertEquals("filter2", newFilters.getFilterName());
        assertEquals("user2", newFilters.getUserId());
        assertEquals("service2", newFilters.getServiceName());
        assertEquals(columnNames, newFilters.getColumnNames());
        assertEquals(newSortingOrder, newFilters.getSortingOrder());
        assertEquals(newFilter, newFilters.getFilter());
        assertEquals("newTableSettings", newFilters.getTableSettings());
        assertEquals(false, newFilters.getSave());
        assertEquals(true, newFilters.getMakeMyDefault());
        assertEquals("timestamp", newFilters.getTimeStamp());
        assertEquals(10, newFilters.getPageSize());
    }

    @Test
    void testToString() {
        String expectedToString = "Filters(id=null, userId=user1, serviceName=service1, tableSettings=tableSettings, save=true, makeMyDefault=false, filterName=filter1, columnNames=[column1], sortingOrder=SortingSet(sortName=null, sortList=null), filter=FilterSet(condition=null, rules=null), timeStamp=null, pageSize=0)";
        assertEquals(expectedToString, filters.toString());
    }

    @Test
    void testEquals() {
        Filters filters1 = new Filters("filter1", "user1", "service1", true, false, 
                Collections.singletonList("column1"), sortingOrder, filter, "tableSettings");
        Filters filters2 = new Filters("filter1", "user1", "service1", true, false, 
                Collections.singletonList("column1"), sortingOrder, filter, "tableSettings");
        Filters filters3 = new Filters("filter3", "user3", "service3", false, true, 
                Collections.singletonList("column3"), new SortingSet(), new FilterSet(), "tableSettings3");

        assertEquals(filters1, filters2);
        assertNotEquals(filters1, filters3);
    }

    @Test
    void testHashCode() {
        Filters filters1 = new Filters("filter1", "user1", "service1", true, false, 
                Collections.singletonList("column1"), sortingOrder, filter, "tableSettings");
        Filters filters2 = new Filters("filter1", "user1", "service1", true, false, 
                Collections.singletonList("column1"), sortingOrder, filter, "tableSettings");
        Filters filters3 = new Filters("filter3", "user3", "service3", false, true, 
                Collections.singletonList("column3"), new SortingSet(), new FilterSet(), "tableSettings3");

        assertEquals(filters1.hashCode(), filters2.hashCode());
        assertNotEquals(filters1.hashCode(), filters3.hashCode());
    }
}
