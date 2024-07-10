package com.fujitsu.fnc.vta.settingsmanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortingSetTest {

    private SortingSet sortingSet;
    private SortList sortList;

    @BeforeEach
    void setUp() {
        sortingSet = new SortingSet();
        sortList = new SortList();
    }

    @Test
    void testDefaultConstructor() {
        SortingSet defaultSortingSet = new SortingSet();
        assertNull(defaultSortingSet.getSortName());
        assertNull(defaultSortingSet.getSortList());
    }

    @Test
    void testGettersAndSetters() {
        sortingSet.setSortName("sortName");

        sortList.setColumnName("columnName");
        sortList.setOrder("asc");
        List<SortList> sortList = Collections.singletonList(this.sortList);
        sortingSet.setSortList(sortList);

        assertEquals("sortName", sortingSet.getSortName());
        assertEquals(1, sortingSet.getSortList().size());
        assertEquals("columnName", sortingSet.getSortList().get(0).getColumnName());
        assertEquals("asc", sortingSet.getSortList().get(0).getOrder());
    }

    @Test
    void testToString() {
        sortingSet.setSortName("sortName");

        sortList.setColumnName("columnName");
        sortList.setOrder("asc");
        sortingSet.setSortList(Collections.singletonList(sortList));

        String expectedToString = "SortingSet(sortName=sortName, sortList=[SortList(columnName=columnName, order=asc)])";
        assertEquals(expectedToString, sortingSet.toString());
    }

    @Test
    void testEquals() {
        SortingSet sortingSet1 = new SortingSet();
        sortingSet1.setSortName("sortName");

        SortList sortList1 = new SortList();
        sortList1.setColumnName("columnName");
        sortList1.setOrder("asc");
        sortingSet1.setSortList(Collections.singletonList(sortList1));

        SortingSet sortingSet2 = new SortingSet();
        sortingSet2.setSortName("sortName");

        SortList sortList2 = new SortList();
        sortList2.setColumnName("columnName");
        sortList2.setOrder("asc");
        sortingSet2.setSortList(Collections.singletonList(sortList2));

        SortingSet sortingSet3 = new SortingSet();
        sortingSet3.setSortName("differentSortName");

        SortList sortList3 = new SortList();
        sortList3.setColumnName("differentColumnName");
        sortList3.setOrder("desc");
        sortingSet3.setSortList(Collections.singletonList(sortList3));

        assertEquals(sortingSet1, sortingSet2);
        assertNotEquals(sortingSet1, sortingSet3);
    }

    @Test
    void testHashCode() {
        SortingSet sortingSet1 = new SortingSet();
        sortingSet1.setSortName("sortName");

        SortList sortList1 = new SortList();
        sortList1.setColumnName("columnName");
        sortList1.setOrder("asc");
        sortingSet1.setSortList(Collections.singletonList(sortList1));

        SortingSet sortingSet2 = new SortingSet();
        sortingSet2.setSortName("sortName");

        SortList sortList2 = new SortList();
        sortList2.setColumnName("columnName");
        sortList2.setOrder("asc");
        sortingSet2.setSortList(Collections.singletonList(sortList2));

        SortingSet sortingSet3 = new SortingSet();
        sortingSet3.setSortName("differentSortName");

        SortList sortList3 = new SortList();
        sortList3.setColumnName("differentColumnName");
        sortList3.setOrder("desc");
        sortingSet3.setSortList(Collections.singletonList(sortList3));

        assertEquals(sortingSet1.hashCode(), sortingSet2.hashCode());
        assertNotEquals(sortingSet1.hashCode(), sortingSet3.hashCode());
    }
}
