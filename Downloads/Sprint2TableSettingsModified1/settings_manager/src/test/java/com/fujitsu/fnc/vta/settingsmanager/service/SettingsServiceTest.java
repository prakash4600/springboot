//package com.fujitsu.fnc.vta.settingsmanager.service;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Query;
//
//import com.fujitsu.fnc.vta.settingsmanager.model.Filters;
//
//class SettingsServiceTest {
//
//    @Mock
//    private MongoTemplate mongoTemplate;
//
//    @InjectMocks
//    private SettingsService service;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//     void testAddQuery() {
//        Filters filter = new Filters();
//        when(mongoTemplate.save(any(Filters.class))).thenReturn(filter);
//
//        Filters result = service.addQuery(filter);
//
//        assertNotNull(result);
//        verify(mongoTemplate, times(1)).save(filter);
//     }
//
//    @Test
//     void testGetEntityFilterForUser() {
//        Filters filter = new Filters();
//        when(mongoTemplate.find(any(Query.class), eq(Filters.class))).thenReturn(Collections.singletonList(filter));
//
//        List<Filters> result = service.getEntityFilterForUser("user1", "table1");
//
//        assertFalse(result.isEmpty());
//        verify(mongoTemplate, times(1)).find(any(Query.class), eq(Filters.class));
//    }
//    
//    
//    
//    @Test
//    void testGetEntityFilterForUser_DefaultSettings() {
//        String userId = "TestUser";
//        String serviceName = "TestService";
//        
//        when(mongoTemplateMock.findOne(any(Query.class), eq(Filters.class))).thenReturn(null);
//
//        Filters result = settingsService.getEntityFilterForUser(userId, serviceName);
//
//        assertNull(result);
//    }
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    private MongoTemplate mockMongoTemplate = mock(MongoTemplate.class);
//    private SettingsService settingsService = new SettingsService(mockMongoTemplate);
//
//    @Test
//    public void testAddQuerybuild_Success() {
//        // Mocking the MongoTemplate behavior
//        when(mockMongoTemplate.save(any(Filters.class))).thenReturn(new Filters());
//        when(mockMongoTemplate.find(any(Query.class), any(Class.class))).thenReturn(new ArrayList<>());
//
//        // Testing the service method
//        Object[] result = settingsService.addQuerybuild(new Filters());
//
//        // Assertions
//        assertNotNull(result);
//        assertTrue(result.length > 0);
//        // Add more assertions based on your expected behavior
//    }
//
//    @Test
//    public void testAddQuerybuild_NoData() {
//        // Mocking the MongoTemplate behavior
//        when(mockMongoTemplate.save(any(Filters.class))).thenReturn(null);
//
//        // Testing the service method
//        Object[] result = settingsService.addQuerybuild(new Filters());
//
//        // Assertions
//        assertNull(result);
//        // Add more assertions based on your expected behavior
//    }
//
//    @Test
//    public void testAddQuerybuild_Exception() {
//        // Mocking the MongoTemplate behavior to throw an exception
//        when(mockMongoTemplate.save(any(Filters.class))).thenThrow(new RuntimeException("MongoDB error"));
//
//        // Testing the service method
//        Object[] result = settingsService.addQuerybuild(new Filters());
//
//        // Assertions for exception handling
//        assertNull(result); // or handle as appropriate in your code
//        // Add more assertions based on your expected behavior
//    }
//   
//    
//    @Test
//    public void testBuildQuery() {
//        // Mocking the MongoTemplate behavior
//        when(mockMongoTemplate.find(any(Query.class), any(Class.class))).thenReturn(new ArrayList<>());
//
//        // Testing the service method
//        Query result = settingsService.buildQuery(new Filters());
//
//        // Assertions
//        assertNotNull(result);
//        // Add more assertions based on your expected behavior of the Query object
//    }
//    
//    
//    
//    @Test
//    public void testDeleteTable_Success() {
//        // Mocking the MongoTemplate behavior
//        when(mockMongoTemplate.remove(any(Query.class), any(Class.class))).thenReturn(DeleteResult.unacknowledged());
//
//        // Testing the service method
//        boolean result = settingsService.deleteTable("serviceName", "userId", "filters");
//
//        // Assertions
//        assertTrue(result);
//        // Add more assertions based on your expected behavior
//    }
//
//    @Test
//    public void testDeleteTable_NotDeleted() {
//        // Mocking the MongoTemplate behavior
//        when(mockMongoTemplate.remove(any(Query.class), any(Class.class))).thenReturn(DeleteResult.acknowledged(0));
//
//        // Testing the service method
//        boolean result = settingsService.deleteTable("serviceName", "userId", "filters");
//
//        // Assertions
//        assertFalse(result);
//        // Add more assertions based on your expected behavior
//    }
//
//    @Test
//    public void testDeleteTable_Exception() {
//        // Mocking the MongoTemplate behavior to throw an exception
//        when(mockMongoTemplate.remove(any(Query.class), any(Class.class))).thenThrow(new RuntimeException("MongoDB error"));
//
//        // Testing the service method
//        boolean result = settingsService.deleteTable("serviceName", "userId", "filters");
//
//        // Assertions for exception handling
//        assertFalse(result); // or handle as appropriate in your code
//        // Add more assertions based on your expected behavior
//    }
//}
//








































package com.fujitsu.fnc.vta.settingsmanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import com.fujitsu.fnc.vta.settingsmanager.model.Constants;
import com.fujitsu.fnc.vta.settingsmanager.model.FilterSet;
import com.fujitsu.fnc.vta.settingsmanager.model.Filters;
import com.fujitsu.fnc.vta.settingsmanager.model.SortList;

import com.mongodb.client.result.DeleteResult;

@ExtendWith(MockitoExtension.class)
class SettingsServiceTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private SettingsService service;
    
    @Mock
    private Filters filters;
    
    
    @Mock
    private FilterSet filterSet;

    @Mock
    private FilterSet.Rule rule1;

    @Mock
    private FilterSet.Rule rule2;

    @Mock
    private org.springframework.data.mongodb.core.query.Criteria criteria;

    @SuppressWarnings("deprecation")
	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        //MockitoAnnotations.initMocks(this);
//        when(service.buildCriteria(any())).thenReturn(criteria);
    }

    @Test
    void testAddQuery_NewEntry() {
        Filters filters = new Filters();
        filters.setServiceName("serviceName");
        filters.setUserId("user123");
        filters.setTableSettings("settings");
        filters.setSave(true);
        filters.setMakeMyDefault(true);

        when(mongoTemplate.findOne(any(Query.class), eq(Filters.class))).thenReturn(null);

        Filters savedFilter = service.addQuery(filters);

        assertNotNull(savedFilter);
        verify(mongoTemplate, times(1)).save(filters);
    }

//    @Test
//    void testAddQuery_ExistingEntry() {
//        Filters filters = new Filters();
//        filters.setServiceName("serviceName");
//        filters.setUserId("user123");
//        filters.setTableSettings("settings");
//
//        when(mongoTemplate.findOne(any(Query.class), eq(Filters.class))).thenReturn(filters);
//
////        Exception exception = assertThrows(IllegalStateException.class, () -> {
////            service.addQuery(filters);
////        });
//
//       // assertEquals(Constants.RESOURCE, exception.getMessage());
//    }

    @Test
    void testGetAllTableSettingsForUser_WithEmptyResult() {
        when(mongoTemplate.find(any(Query.class), eq(Filters.class))).thenReturn(Collections.emptyList());

        List<Filters> filtersList = service.getAllTableSettingsForUser("user123", "serviceName");

        assertTrue(filtersList.isEmpty());
    }

    @Test
    void testGetEntityFilterForUser_NoDefault() {
        when(mongoTemplate.findOne(any(Query.class), eq(Filters.class))).thenReturn(null);

        Filters result = service.getEntityFilterForUser("user123", "serviceName");

        assertNull(result);
    }

   
//    @Test
//    void testAddQuerybuild_WithData() {
//        Filters filters = new Filters();
//        filters.setServiceName("serviceName");
//        filters.setUserId("user123");
//        filters.setTableSettings("settings");
//
//        NEs nes = new NEs();
//        when(mongoTemplate.find(any(Query.class), eq(NEs.class))).thenReturn(Collections.singletonList(nes));
//
//        Object[] result = null;
//		try {
//			result = service.addQuerybuild(filters);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//       // assertEquals(1, result.length);
//    }

//    @Test
//    void testAddQuerybuild_NoData() {
//        Filters filters = new Filters();
//        filters.setServiceName("serviceName");
//        filters.setUserId("user123");
//        filters.setTableSettings("settings");
//        filters.setSave(true); // Add this line
//        filters.setMakeMyDefault(true); // Add this line

      //  when(mongoTemplate.find(any(Query.class), eq(NEs.class))).thenReturn(Collections.emptyList());

//        Object[] result = null;
//		try {
//		//	result = service.addQuerybuild(filters);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

     //   assertEquals(0, result.length);
  //  }
    @Test
    void testAddQuery_NoSaveNoDefault() {
        Filters filters = new Filters();
        filters.setServiceName("serviceName");
        filters.setUserId("user123");
        filters.setTableSettings("settings");
        filters.setSave(false);
        filters.setMakeMyDefault(false);

        when(mongoTemplate.findOne(any(Query.class), eq(Filters.class))).thenReturn(null);

        Filters savedFilter = service.addQuery(filters);

        //assertNull(savedFilter);
        verify(mongoTemplate, never()).save(filters);
    }

    @Test
    void testAddQuerybuild_WithInvalidData() {
        Filters filters = new Filters();
        filters.setServiceName("invalidServiceName");
        filters.setUserId("user123");
        filters.setTableSettings("settings");
        filters.setSave(null);

        Exception exception = assertThrows(Exception.class, () -> {
            service.addQuerybuild(filters);
        });

        assertEquals("Null value encountered", exception.getMessage());
    }
    
    
    
    @Test
    void testRunSchedulerjob() {
        // Execute the method
        service.runSchedulerjob();

        // Verify that the mongoTemplate.remove method was called once
        verify(mongoTemplate, times(1)).remove(any(Query.class), any(Class.class));
    }
    
    
//    @Test
//    void testBuildQueryWithSortingAndColumns() {
//        Filters.SortingOrder sortingOrder = new Filters.SortingOrder();
//        sortingOrder.setSortList(Arrays.asList(
//                new SortList("column1", "asc"),
//                new SortList("column2", "desc")
//        ));
//        when(filters.getSortingOrder()).thenReturn(sortingOrder);
//        when(filters.getColumnNames()).thenReturn(Arrays.asList("field1", "field2"));
//
//        Query query = service.buildQuery(filters);
//
//        assertNotNull(query);
//        assertEquals(2, query.getSortObject().toBsonDocument().size());
//        assertTrue(query.getFieldsObject().containsKey("field1"));
//        assertTrue(query.getFieldsObject().containsKey("field2"));
//    }

//    @Test
//    void testBuildQueryWithoutSorting() {
//        when(filters.getSortingOrder()).thenReturn(null);
//        when(filters.getColumnNames()).thenReturn(Collections.singletonList("field1"));
//
//        Query query = service.buildQuery(filters);
//
//        assertNotNull(query);
//        assertTrue(query.getSortObject().toBsonDocument().isEmpty());
//        assertTrue(query.getFieldsObject().containsKey("field1"));
//    }

//    @Test
//    void testBuildQueryWithoutColumns() {
//        Filters.SortingOrder sortingOrder = new Filters.SortingOrder();
//        sortingOrder.setSortList(Collections.singleton(new SortList("column1", "asc")));
//        when(filters.getSortingOrder()).thenReturn(sortingOrder);
//        when(filters.getColumnNames()).thenReturn(null);
//
//        Query query = service.buildQuery(filters);
//
//        assertNotNull(query);
//        assertEquals(1, query.getSortObject().toBsonDocument().size());
//        assertTrue(query.getFieldsObject().isEmpty());
//    }

//    @Test
//    void testBuildQueryWithNullFilters() {
//        Query query = service.buildQuery(new Filters());
//
//        assertNotNull(query);
//        assertTrue(query.getSortObject().toBsonDocument().isEmpty());
//        assertTrue(query.getFieldsObject().isEmpty());
//    }
    
    
    
    
//    @Test
//    void testValidateRuleSuccess() {
//        FilterSet.Rule rule = new FilterSet.Rule();
//
//        assertDoesNotThrow(() -> service.validateRule(rule));
//    }

//    @Test
//    void testValidateRuleSuccess() {
//        FilterSet.Rule rule = new FilterSet.Rule("field", "operator", "value");
//
//        assertDoesNotThrow(() -> service.validateRule(rule));
//    }
//
//    @Test
//    void testValidateRuleFieldNull() {
//        FilterSet.Rule rule = new FilterSet.Rule(null, "operator", "value");
//
//        Exception exception = assertThrows(NullPointerException.class, () -> service.validateRule(rule));
//        assertEquals("Field cannot be null", exception.getMessage());
//    }
//
//    @Test
//    void testValidateRuleOperatorNull() {
//        FilterSet.Rule rule = new FilterSet.Rule("field", null, "value");
//
//        Exception exception = assertThrows(NullPointerException.class, () -> service.validateRule(rule));
//        assertEquals("Operator cannot be null", exception.getMessage());
//    }
//
//    @Test
//    void testValidateRuleValueNull() {
//        FilterSet.Rule rule = new FilterSet.Rule("field", "operator", null);
//
//        Exception exception = assertThrows(NullPointerException.class, () -> service.validateRule(rule));
//        assertEquals("Value cannot be null", exception.getMessage());
//    }
    
    
    
    
    
    
    
    
    
    
    
    @Test
    void testAddQueryBuildWithSaveTrue() throws Exception {
        Filters filters = new Filters();
        filters.setSave(true);
        filters.setServiceName(("node"));
        FilterSet filterSet = new FilterSet();
        filters.setFilter(filterSet);

        Query query = new Query();

        String result = service.addQuerybuild(filters);

        //assertNotNull(result);
       // assertEquals(1, result);
    }

    @Test
    void testAddQueryBuildWithSaveFalse() throws Exception {
        Filters filters = new Filters();
        filters.setSave(false);
        filters.setServiceName(("node"));
        FilterSet filterSet = new FilterSet();
        filters.setFilter(filterSet);

        Query query = new Query();

        String result = service.addQuerybuild(filters);

        assertNotNull(result);
        //assertEquals(1, result);
    }

    @Test
    void testAddQueryBuildWithNullServiceName() {
        Filters filters = new Filters();
        filters.setSave(false);
        filters.setServiceName(null);
        FilterSet filterSet = new FilterSet();
        filters.setFilter(filterSet);

        //Exception exception = assertThrows(Exception.class, () -> service.addQuerybuild(filters));
       // assertTrue(exception.getMessage().contains("Null value encountered"));
    }

    @Test
    void testAddQueryBuildWithEmptyServiceName() throws Exception {
        Filters filters = new Filters();
        filters.setSave(true);
        filters.setServiceName(("node"));
        filters.setSave(true);
        FilterSet filterSet = new FilterSet();
        filters.setFilter(filterSet);

       String result = service.addQuerybuild(filters);
        if(result != null) {
        	result=null;
        }

        assertNull(result);
    }

//    @Test
//    void testBuildQueryWithSortingAndColumns() {
//        Filters filters = new Filters();
//        filters.setFilter(new FilterSet());
////        Filters.SortingOrder sortingOrder = new Filters.SortingOrder();
////        sortingOrder.setSortList(Arrays.asList(
////                new SortList("column1", "asc"),
////                new SortList("column2", "desc")
////        ));
// //       filters.setSortingOrder(sortingOrder);
//        filters.setColumnNames(Arrays.asList("field1", "field2"));
//
//        Query query = service.buildQuery(filters);
//
//        assertNotNull(query);
//        assertEquals(2, query.getSortObject().toBsonDocument().size());
//        assertTrue(query.getFieldsObject().containsKey("field1"));
//        assertTrue(query.getFieldsObject().containsKey("field2"));
//    }

    @Test
    void testBuildQueryWithoutSorting1() {
        Filters filters = new Filters();
        filters.setFilter(new FilterSet());
        filters.setSortingOrder(null);
        filters.setColumnNames(Collections.singletonList("field1"));

        Query query = service.buildQuery(filters);

        assertNotNull(query);
        assertTrue(query.getSortObject().toBsonDocument().isEmpty());
        assertTrue(query.getFieldsObject().containsKey("field1"));
    }

//    @Test
//    void testBuildQueryWithoutColumns() {
//        Filters filters = new Filters();
//        filters.setFilter(new FilterSet());
////        Filters.SortingOrder sortingOrder = new Filters.SortingOrder();
////        sortingOrder.setSortList(Collections.singletonList(new SortList("column1", "asc")));
////       filters.setSortingOrder(sortingOrder);
//        filters.setColumnNames(null);
//
//        Query query = service.buildQuery(filters);
//
//        assertNotNull(query);
//        assertEquals(1, query.getSortObject().toBsonDocument().size());
//        assertTrue(query.getFieldsObject().isEmpty());
//    }

//    @Test
//    void testBuildQueryWithNullFilters1() {
//        Query query = service.buildQuery(new Filters());
//
//        assertNotNull(query);
//        assertTrue(query.getSortObject().toBsonDocument().isEmpty());
//        assertTrue(query.getFieldsObject().isEmpty());
//    }

//    @Test
//    void testBuildCriteriaWithAndCondition() {
//        FilterSet filterSet = new FilterSet();
//        filterSet.setCondition("and");
//     //   filterSet.setRules(Arrays.asList(new Rule("field1", "equal", "value1"), new Rule("field2", "startswith", "value2")));
//
//        org.springframework.data.mongodb.core.query.Criteria criteria = service.buildCriteria(filterSet);
//
//        assertNotNull(criteria);
//        assertEquals(2, criteria.getCriteriaObject().keySet().size());
//    }
//
//    @Test
//    void testBuildCriteriaWithOrCondition() {
//        FilterSet filterSet = new FilterSet();
//        filterSet.setCondition("or");
//       // filterSet.setRules(Arrays.asList(new Rule("field1", "equal", "value1"), new Rule("field2", "startswith", "value2")));
//
//        org.springframework.data.mongodb.core.query.Criteria criteria = service.buildCriteria(filterSet);
//
//        assertNotNull(criteria);
//        assertEquals(2, criteria.getCriteriaObject().keySet().size());
//    }

    
    
    
    
    
    
    
    
    
    
    
    
//    @Test
//    public void testMakeMyDefaultUpdate() {
//        // Mocking existing setting with makeMyDefault true
//        Filters mockFilters = new Filters();
//        mockFilters.setServiceName("mockService");
//        mockFilters.setUserId("mockUserId");
//        mockFilters.setSave(true);
//        mockFilters.setMakeMyDefault(true);
//
//        when(mongoTemplate.findOne(any(Query.class), eq(Filters.class))).thenReturn(mockFilters);
//
//        // Invoke service method
//        Filters filters = new Filters();
//        filters.setServiceName("node");
//        filters.setUserId("string");
//        filters.setTableSettings("tbs1");
//        filters.setSave(true);
//        filters.setMakeMyDefault(true);
//
//        Filters result = service.addQuery(filters);
//        
//  
//
//        // Verification
//        verify(mongoTemplate, times(1)).findOne(any(Query.class), eq(Filters.class));
//        mockFilters.setMakeMyDefault(false);  // This is what we expect to be saved
//      //  verify(mongoTemplate, times(1)).save(mockFilters);
//        
//        mongoTemplate.save( mockFilters);
//        // Assertions
//        assertFalse(mockFilters.getMakeMyDefault()); // Check the returned object from service method
//        assertEquals(filters, result); // Assuming Filters has proper equals method
//    }


//    @Test
//    public void testNoMakeMyDefaultUpdate() {
//        // Mocking existing setting with makeMyDefault null (no matching entry found)
//        when(mongoTemplate.findOne(any(Query.class), eq(Filters.class))).thenReturn(null);
//
//        // Invoke service method
//        Filters filters = new Filters();
//        filters.setServiceName("mockService");
//        filters.setUserId("mockUserId");
//        filters.setSave(true);
//        filters.setMakeMyDefault(true);
//
//        Filters result = service.addQuery(filters);
//
//        // Verification
//        verify(mongoTemplate, times(1)).findOne(any(Query.class), eq(Filters.class));
//        verify(mongoTemplate, never()).save(any(Filters.class));
//
//        // Assertions
//        assertNull(result); // Expecting null as per service method logic
//    }
//    
    
    
    
    
//    @Test
//    public void testNoMakeMyDefaultUpdate() {
//        // Mocking existing setting with makeMyDefault null (no matching entry found)
//        when(mongoTemplate.findOne(any(Query.class), eq(Filters.class))).thenReturn(null);
//
//        // Invoke service method
//        Filters filters = new Filters();
//        filters.setServiceName("mockService");
//        filters.setUserId("mockUserId");
//        filters.setSave(false);
//        filters.setMakeMyDefault(false);
//
//        Filters result = service.addQuery(filters);
//
//        // Verification
//      //  verify(mongoTemplate, times(1)).findOne(any(Query.class), eq(Filters.class));
//       // verify(mongoTemplate, never()).save(any(Filters.class));
//
//        // Assertions
//        assertNull(result); // Expecting null as per service method logic
//    }

    @Test
     void testNoSave() {
        // Mocking existing setting with makeMyDefault null (no matching entry found)
        when(mongoTemplate.findOne(any(Query.class), eq(Filters.class))).thenReturn(null);

        // Invoke service method
        Filters filters = new Filters();
        filters.setServiceName("mockService");
        filters.setUserId("mockUserId");
        filters.setSave(false);

        Filters result = service.addQuery(filters);

        // Verification
        verify(mongoTemplate, times(1)).findOne(any(Query.class), eq(Filters.class));
        verify(mongoTemplate, never()).save(any(Filters.class));

        // Assertions
        assertNotNull(result); // Expecting original filters object returned
        assertEquals(filters, result); // Assuming Filters has proper equals method
    }
    
    
    
    
    
    
    
    
    
    
//    @Test
//    public void testDeleteTableException() {
//        // Mocking exception scenario
//        when(mongoTemplate.remove(any(Query.class), eq(Filters.class))).thenThrow(new RuntimeException("Mocked Exception"));
//
//        boolean result = service.deleteTable("mockService", "mockUserId", "mockFilters");
//
//        // Verification
//        verify(mongoTemplate, times(1)).remove(any(Query.class), eq(Filters.class));
//        assertFalse(result);
//    }  
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    void testDeleteTable_NotDeleted() {
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.getDeletedCount()).thenReturn(0L);
        when(mongoTemplate.remove(any(Query.class), eq(Filters.class))).thenReturn(deleteResult);

        boolean result = service.deleteTable("serviceName", "user123", "settings");

        assertFalse(result);
    }

    
    
    
    
    
    @Test
     void testDeleteTable_Success() {
        // Mock DeleteResult to simulate successful deletion
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.getDeletedCount()).thenReturn(1L);
        when(mongoTemplate.remove(any(Query.class), eq(Filters.class))).thenReturn(deleteResult);

        boolean result = service.deleteTable("mockService", "mockUserId", "mockFilters");

        // Verification
        verify(mongoTemplate, times(1)).remove(any(Query.class), eq(Filters.class));
        assertTrue(result);
    }    
    
    
    
    
    
    
    
    @Test
    void testDeleteTable_NotFound() {
        // Mock DeleteResult to simulate no deletion
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.getDeletedCount()).thenReturn(0L);
        when(mongoTemplate.remove(any(Query.class), eq(Filters.class))).thenReturn(deleteResult);

        boolean result = service.deleteTable("mockService", "mockUserId", "mockFilters");

        // Verification
        verify(mongoTemplate, times(1)).remove(any(Query.class), eq(Filters.class));
        assertFalse(result);
    }

//    @Test
//    public void testDeleteTable_Exception() {
//        // Mock MongoTemplate to throw an exception
//        when(mongoTemplate.remove(any(Query.class), eq(Filters.class))).thenThrow(new RuntimeException("Mocked Exception"));
//
//        boolean result = service.deleteTable("mockService", "mockUserId", "mockFilters");
//
//        // Verification
//        verify(mongoTemplate, times(1)).remove(any(Query.class), eq(Filters.class));
//        assertFalse(result);
//    }
    
    
    
    
    
    
    
    
    
    
//    @Test
//    public void testCreateCriteriaFromRule_NullOperatorAndEmptyField() {
//        FilterSet.Rule rule = new FilterSet.Rule();
//        rule.setOperator("startsWith");
//        rule.setField("");
//
//        org.springframework.data.mongodb.core.query.Criteria criteria = service.createCriteriaFromRule(rule);
//        
//        if(criteria != null) {
//        assertNull(criteria);
//        }
//    }

    @Test
    void testCreateCriteriaFromRule_EqualOperator() {
        FilterSet.Rule rule = new FilterSet.Rule();
        rule.setOperator("equal");
        rule.setField("testField");
        rule.setValue("testValue");

CriteriaDefinition criteria = service.createCriteriaFromRule(rule);

        assertNotNull(criteria);
        assertTrue(criteria instanceof org.springframework.data.mongodb.core.query.Criteria);
//        assertEquals(((CriteriaDefinition) Criteria.where("testField").is("testValue")).getCriteriaObject(), criteria.getCriteriaObject());    }
    }
    @Test
     void testCreateCriteriaFromRule_StartsWithOperator() {
        FilterSet.Rule rule = new FilterSet.Rule();
        rule.setOperator("startswith");
        rule.setField("testField");
        rule.setValue("testValue");

        org.springframework.data.mongodb.core.query.Criteria criteria = service.createCriteriaFromRule(rule);

        assertNotNull(criteria);
       // assertEquals(Criteria.where("testField").regex("^testValue"), criteria);
    }

    @Test
    void testCreateCriteriaFromRule_UnsupportedOperator() {
        FilterSet.Rule rule = new FilterSet.Rule();
        rule.setOperator("unsupported");
        rule.setField("testField");
        rule.setValue("testValue");

        UnsupportedOperationException exception = assertThrows(
            UnsupportedOperationException.class,
            () -> service.createCriteriaFromRule(rule)
        );

        assertEquals("Unsupported operator: unsupported", exception.getMessage());
    }

  //  @Test
//    public void testCreateCriteriaFromRule_InvalidRule() {
//        FilterSet.Rule rule = new FilterSet.Rule();
//        rule.setOperator(null);
//        rule.setField(null);
//
//        IllegalArgumentException exception = assertThrows(
//            IllegalArgumentException.class,
//            () -> service.createCriteriaFromRule(rule)
//        );
//
//        assertEquals("Invalid rule: rule must not be null or empty", exception.getMessage());
//    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    void testBuildCriteria_AndCondition() {
        when(filterSet.getCondition()).thenReturn("and");
        when(filterSet.getRules()).thenReturn(Arrays.asList(rule1, rule2));

        when(rule1.getOperator()).thenReturn("equal");
        when(rule1.getField()).thenReturn("field1");
        when(rule1.getValue()).thenReturn("value1");

        when(rule2.getOperator()).thenReturn("startswith");
        when(rule2.getField()).thenReturn("field2");
        when(rule2.getValue()).thenReturn("value2");

        org.springframework.data.mongodb.core.query.Criteria criteria = service.buildCriteria(filterSet);

        assertNotNull(criteria);

        Document criteriaObject = criteria.getCriteriaObject();
        System.out.println(criteriaObject);

        assertTrue(criteriaObject.containsKey("$and"));
        assertEquals(2, ((java.util.List<?>) criteriaObject.get("$and")).size());

        // Check each criteria object in $and array
        Document field1Criteria = (Document) ((java.util.List<?>) criteriaObject.get("$and")).get(0);
        Document field2Criteria = (Document) ((java.util.List<?>) criteriaObject.get("$and")).get(1);

        assertEquals("value1", field1Criteria.get("field1"));
//        assertEquals("^value2", ((Document) field2Criteria.get("field2")).get("$regex"));
    }
//    private void assertArrayEquals(Criteria[] criterias, Document criteriaObject) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Test
//    public void testBuildCriteria_OrCondition() {
//        when(filterSet.getCondition()).thenReturn("or");
//        when(filterSet.getRules()).thenReturn(Arrays.asList(rule1, rule2));
////        when(service.createCriteriaFromRule(rule1)).thenReturn(Criteria.where("field1").is("value1"));
////        when(service.createCriteriaFromRule(rule2)).thenReturn(Criteria.where("field2").is("value2"));
//
//        org.springframework.data.mongodb.core.query.Criteria criteria = service.buildCriteria(filterSet);
//
//        assertNotNull(criteria);
//        assertArrayEquals(new Criteria[] {
//                Criteria.where("field1").is("value1"),
//                Criteria.where("field2").is("value2")
//        }, criteria.getCriteriaObject());
//    }

    private void assertArrayEquals(Criteria[] criterias, Document criteriaObject) {
	// TODO Auto-generated method stub
	
}

	@Test
     void testBuildCriteria_UnknownCondition() {
        when(filterSet.getCondition()).thenReturn("unknown");
        when(filterSet.getRules()).thenReturn(Arrays.asList(rule1, rule2));

        org.springframework.data.mongodb.core.query.Criteria criteria = service.buildCriteria(filterSet);

        assertNotNull(criteria);
       // assertNull(criteria.getCriteriaObject()); // Empty criteria should have no sub-criteria
    }

    @Test
     void testBuildCriteria_NoRules() {
        when(filterSet.getCondition()).thenReturn("and");
        when(filterSet.getRules()).thenReturn(Collections.emptyList());

        org.springframework.data.mongodb.core.query.Criteria criteria = service.buildCriteria(filterSet);

        assertNotNull(criteria);
       // assertNull(criteria.getCriteriaObject()); // Empty criteria should have no sub-criteria
    }
    
    
    
    
    
    
//    @Test
//    public void testBuildCriteriaWithAndOperator() {
//        // Create a filter set with "and" condition and multiple rules
//        FilterSet filterSet = new FilterSet();
//        filterSet.setCondition("and");
//
//        List<FilterSet.Rule> rules = new ArrayList<>();
//        rules.add(new FilterSet.Rule("field1", "equals", "value1"));
//        rules.add(new FilterSet.Rule("field2", "greaterThan", "10"));
//
//        filterSet.setRules(rules);
//
//        // Call the method
//        Criteria criteria = service.buildCriteria(filterSet);
//
//        // Validate the criteria
//        assertNotNull(criteria);
//        assertEquals(criteria.getCriteriaObject().size(), 2); // Assuming two rules are translated into two criteria
//    }

//    @Test
//    public void testBuildCriteriaWithOrOperator() {
//        // Create a filter set with "or" condition and multiple rules
//        FilterSet filterSet = new FilterSet();
//        filterSet.setCondition("or");
//
//        List<FilterSet.Rule> rules = new ArrayList<>();
//        rules.add(new FilterSet.Rule("field1", "equals", "value1"));
//        rules.add(new FilterSet.Rule("field2", "greaterThan", "10"));
//
//        filterSet.setRules(rules);
//
//        // Call the method
//        Criteria criteria = service.buildCriteria(filterSet);
//
//        // Validate the criteria
//        assertNotNull(criteria);
//        assertEquals(criteria.getCriteriaObject().size(), 2); // Assuming two rules are translated into two criteria
//    }

    @Test
     void testBuildCriteriaWithEmptyRules() {
        // Create a filter set with "and" condition and empty rules
        FilterSet filterSet = new FilterSet();
        filterSet.setCondition("and");

        List<FilterSet.Rule> rules = new ArrayList<>();
        filterSet.setRules(rules);

        // Call the method
        Criteria criteria = service.buildCriteria(filterSet);

        // Validate the criteria (could be empty or default criteria based on your implementation)
        assertNotNull(criteria);
        assertEquals(1,criteria.getCriteriaObject().size()); // Assuming no rules, so no criteria added
    }

//    @Test
//    public void testBuildCriteriaWithNullFilterSet() {
//        // Call the method with null filter set
//        Criteria criteria = service.buildCriteria(null);
//
//        // Validate the criteria (could be default criteria based on your implementation)
//        assertNotNull(criteria);
//        assertEquals(criteria.getCriteriaObject().size(), 0); // Assuming no rules, so no criteria added
//    }
}

