//package com.fujitsu.fnc.vta.settingsmanager.controller;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fujitsu.fnc.vta.settingsmanager.enums.ResponseStatus;
//import com.fujitsu.fnc.vta.settingsmanager.model.Constants;
//import com.fujitsu.fnc.vta.settingsmanager.model.Filters;
//import com.fujitsu.fnc.vta.settingsmanager.service.SettingsService;
//
//@WebMvcTest(SettingController.class)
//@AutoConfigureMockMvc
//class SettingControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SettingsService service;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    void testAddNodeFilters_Success() throws Exception {
//        Filters filter = new Filters();
//        filter.setFilterName("testFilter");
//        filter.setUserId("user123");
//        filter.setServiceName("node");
//
//        Filters mockResponse = new Filters();
//        mockResponse.setFilterName("testFilter");
//        mockResponse.setUserId("user123");
//        mockResponse.setServiceName("node");
//
//        when(service.addQuery(any(Filters.class))).thenReturn(mockResponse);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/vta/api/v1/create")
//                .content(objectMapper.writeValueAsString(filter))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(true))
//                .andExpect(jsonPath("$.message").value(ResponseStatus.SUCCESS.getMessage()));
//    }
//
//    @Test
//    void testAddNodeFilters_ResourceExists() throws Exception {
//        Filters filter = new Filters();
//        filter.setFilterName("testFilter");
//        filter.setUserId("user123");
//        filter.setServiceName("node");
//
//        when(service.addQuery(any(Filters.class))).thenReturn(null);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/vta/api/v1/create")
//                .content(objectMapper.writeValueAsString(filter))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isConflict())
//                .andExpect(jsonPath("$.status").value(false))
//                .andExpect(jsonPath("$.message").value(Constants.ALREADY_EXISTS));
//    }
//
//    @Test
//    void testAddNodeFilters_InvalidData() throws Exception {
//        Filters filter = new Filters();
//        filter.setFilterName("testFilter");
//        filter.setUserId("user123");
//        filter.setServiceName("node");
//
//        when(service.addQuery(any(Filters.class))).thenThrow(new RuntimeException("invalid data"));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/vta/api/v1/create")
//                .content(objectMapper.writeValueAsString(filter))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError())
//                .andExpect(jsonPath("$.status").value(false))
//                .andExpect(jsonPath("$.message").value(ResponseStatus.INVALID_DATA.getMessage()));
//    }
//
//    @Test
//    void testSaveAndBuildQuery_Success() throws Exception {
//        Filters filter = new Filters();
//        filter.setFilterName("testFilter");
//        filter.setUserId("user123");
//        filter.setServiceName("node");
//
//        Object[] mockResponse = new Object[]{"testData"};
//
//        when(service.addQuerybuild(any(Filters.class))).thenReturn(mockResponse);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/vta/api/v1/buildQuery")
//                .content(objectMapper.writeValueAsString(filter))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(true))
//                .andExpect(jsonPath("$.message").value(Constants.QUERY_SUCCESS));
//    }
//
//    @Test
//    void testSaveAndBuildQuery_NoData() throws Exception {
//        Filters filter = new Filters();
//        filter.setFilterName("testFilter");
//        filter.setUserId("user123");
//        filter.setServiceName("node");
//
//        Object[] mockResponse = new Object[]{};
//
//        when(service.addQuerybuild(any(Filters.class))).thenReturn(mockResponse);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/vta/api/v1/buildQuery")
//                .content(objectMapper.writeValueAsString(filter))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(false))
//                .andExpect(jsonPath("$.message").value(Constants.NODATA));
//    }
//
//    @Test
//    void testSaveAndBuildQuery_InvalidData() throws Exception {
//        Filters filter = new Filters();
//        filter.setFilterName("testFilter");
//        filter.setUserId("user123");
//        filter.setServiceName("node");
//
//        when(service.addQuerybuild(any(Filters.class))).thenThrow(new RuntimeException("invalid data"));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/vta/api/v1/buildQuery")
//                .content(objectMapper.writeValueAsString(filter))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError())
//                .andExpect(jsonPath("$.status").value(false))
//                .andExpect(jsonPath("$.message").value(Constants.FAILURE_BUILD));
//    }
//
//    @Test
//    void testDeleteTable_Success() throws Exception {
//        String userId = "user123";
//        String serviceName = "node";
//        String filters = "testFilter";
//
//        when(service.deleteTable(serviceName, userId, filters)).thenReturn(true);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/vta/api/v1/delete/{tableSetting}", filters)
//                .param("userId", userId)
//                .param("serviceName", serviceName)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(true))
//                .andExpect(jsonPath("$.message").value(Constants.TABLE_DELETED));
//    }
//
//    @Test
//    void testDeleteTable_NoData() throws Exception {
//        String userId = "user123";
//        String serviceName = "node";
//        String filters = "testFilter";
//
//        when(service.deleteTable(serviceName, userId, filters)).thenReturn(false);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/vta/api/v1/delete/{tableSetting}", filters)
//                .param("userId", userId)
//                .param("serviceName", serviceName)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(false))
//                .andExpect(jsonPath("$.message").value("the data not found in database"));
//    }
//
//    @Test
//    void testDeleteTable_InvalidData() throws Exception {
//        String userId = "user123";
//        String serviceName = "node";
//        String filters = "testFilter";
//
//        when(service.deleteTable(serviceName, userId, filters)).thenThrow(new RuntimeException("invalid data"));
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/vta/api/v1/delete/{tableSetting}", filters)
//                .param("userId", userId)
//                .param("serviceName", serviceName)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError())
//                .andExpect(jsonPath("$.status").value(false))
//                .andExpect(jsonPath("$.message").value(Constants.FAILURE_DELETE));
//    }
//
//    @Test
//    void testGetDefaultTableSettings_Success() throws Exception {
//        String userId = "user123";
//        String serviceName = "node";
//
//        Filters mockResponse = new Filters();
//        mockResponse.setFilterName("defaultFilter");
//        mockResponse.setUserId(userId);
//        mockResponse.setServiceName(serviceName);
//
//        when(service.getEntityFilterForUser(userId, serviceName)).thenReturn(mockResponse);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/vta/api/v1/Default/{userId}/{serviceName}", userId, serviceName)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(true))
//                .andExpect(jsonPath("$.message").value(ResponseStatus.OK.getMessage()));
//    }
//
//    @Test
//    void testGetDefaultTableSettings_NoData() throws Exception {
//        String userId = "user123";
//        String serviceName = "node";
//
//        when(service.getEntityFilterForUser(userId, serviceName)).thenReturn(null);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/vta/api/v1/Default/{userId}/{serviceName}", userId, serviceName)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(false))
//                .andExpect(jsonPath("$.message").value(ResponseStatus.NODATA.getMessage()));
//    }
//
//    @Test
//    void testGetDefaultTableSettings_InvalidData() throws Exception {
//        String userId = "user123";
//        String serviceName = "node";
//
//        when(service.getEntityFilterForUser(userId, serviceName)).thenThrow(new RuntimeException("invalid data"));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/vta/api/v1/Default/{userId}/{serviceName}", userId, serviceName)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError())
//                .andExpect(jsonPath("$.status").value(false))
//                .andExpect(jsonPath("$.message").value(ResponseStatus.FAILURE.getMessage()));
//    }
//
//    @Test
//    void testGetDefaultTableSettingsForUser_Success() throws Exception {
//        String userId = "user123";
//        String serviceName = "node";
//
//        Filters mockResponse = new Filters();
//        mockResponse.setFilterName("defaultFilter");
//        mockResponse.setUserId(userId);
//        mockResponse.setServiceName(serviceName);
//
//        when(service.getEntityFilterForUser(userId, serviceName)).thenReturn(mockResponse);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/vta/api/v1/getEntityFilterForUser/{userId}/{serviceName}", userId, serviceName)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(true))
//                .andExpect(jsonPath("$.message").value(ResponseStatus.OK.getMessage()));
//    }
//
//    @Test
//    void testGetDefaultTableSettingsForUser_NoData() throws Exception {
//        String userId = "user123";
//        String serviceName = "node";
//
//        when(service.getEntityFilterForUser(userId, serviceName)).thenReturn(null);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/vta/api/v1/getEntityFilterForUser/{userId}/{serviceName}", userId, serviceName)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(false))
//                .andExpect(jsonPath("$.message").value(ResponseStatus.NODATA.getMessage()));
//    }
//
//    @Test
//    void testGetDefaultTableSettingsForUser_InvalidData() throws Exception {
//        String userId = "user123";
//        String serviceName = "node";
//
//        when(service.getEntityFilterForUser(userId, serviceName)).thenThrow(new RuntimeException("invalid data"));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/vta/api/v1/getEntityFilterForUser/{userId}/{serviceName}", userId, serviceName)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError())
//                .andExpect(jsonPath("$.status").value(false))
//                .andExpect(jsonPath("$.message").value(ResponseStatus.FAILURE.getMessage()));
//    }
//
//    @Test
//    void testGetAllEntitiesFilterForUser_Success() throws Exception {
//        String userId = "user123";
//        String serviceName = "node";
//
//        List<Filters> mockResponse = Collections.singletonList(new Filters());
//
//        when(service.getEntityFilterForUser(userId, serviceName)).thenReturn((Filters) mockResponse);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/vta/api/v1/getAllEntitiesFilterForUser/{userId}/{serviceName}", userId, serviceName)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(true))
//                .andExpect(jsonPath("$.message").value(ResponseStatus.OK.getMessage()));
//    }
//
//    @Test
//    void testGetAllEntitiesFilterForUser_NoData() throws Exception {
//        String userId = "user123";
//        String serviceName = "node";
//
//        when(service.getEntityFilterForUser(userId, serviceName)).thenReturn((Filters) Collections.emptyList());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/vta/api/v1/getAllEntitiesFilterForUser/{userId}/{serviceName}", userId, serviceName)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(false))
//                .andExpect(jsonPath("$.message").value(ResponseStatus.NODATA.getMessage()));
//    }
//
//    @Test
//    void testGetAllEntitiesFilterForUser_InvalidData() throws Exception {
//        String userId = "user123";
//        String serviceName = "node";
//
//        when(service.getEntityFilterForUser(userId, serviceName)).thenThrow(new RuntimeException("invalid data"));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/vta/api/v1/getAllEntitiesFilterForUser/{userId}/{serviceName}", userId, serviceName)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError())
//                .andExpect(jsonPath("$.status").value(false))
//                .andExpect(jsonPath("$.message").value(ResponseStatus.FAILURE.getMessage()));
//    }
//}












































































package com.fujitsu.fnc.vta.settingsmanager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fujitsu.fnc.vta.settingsmanager.enums.ResponseStatus;
import com.fujitsu.fnc.vta.settingsmanager.model.Constants;
import com.fujitsu.fnc.vta.settingsmanager.model.Filters;
import com.fujitsu.fnc.vta.settingsmanager.model.Response;
import com.fujitsu.fnc.vta.settingsmanager.service.SettingsService;

@ExtendWith(MockitoExtension.class)
class SettingControllerTest {

    @Mock
    private SettingsService service;

    @InjectMocks
    private SettingController controller;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testAddNodeFilters_Success() throws Exception {
        Filters filters = new Filters();
        filters.setServiceName("serviceName");
        filters.setUserId("user123");
        filters.setTableSettings("settings");
        filters.setSave(true);

        when(service.addQuery(filters)).thenReturn(filters);

        mockMvc.perform(MockMvcRequestBuilders.post("/vta/api/v1/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filters)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.message").value(ResponseStatus.SUCCESS.getMessage()));
    }

//    @Test
//    void testAddNodeFilters_ResourceExistsException() throws Exception {
//        Filters filters = new Filters();
//        filters.setServiceName("serviceName");
//        filters.setUserId("user123");
//        filters.setTableSettings("settings");
//        filters.setSave(true);
//
//        when(service.addQuery(filters)).thenThrow(new IllegalStateException(Constants.RESOURCE));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/vta/api/v1/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(filters)))
//                .andExpect(status().isConflict())
//                .andExpect(jsonPath("$.status").value(true))
//                .andExpect(jsonPath("$.message").value(ResponseStatus.RESOURCE_EXISTS.getMessage()));
//    }

    @Test
    void testAddNodeFilters_InvalidDataException() throws Exception {
        Filters filters = new Filters();
        filters.setServiceName("serviceName");
        filters.setUserId("user123");
        filters.setTableSettings("settings");
        filters.setSave(true);

        when(service.addQuery(filters)).thenThrow(new IllegalArgumentException("Invalid data"));

        mockMvc.perform(MockMvcRequestBuilders.post("/vta/api/v1/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filters)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.message").value("Save parameter must be true to save data."));
    }

    @Test
    void testAddNodeFilters_GeneralException() throws Exception {
        Filters filters = new Filters();
        filters.setServiceName("serviceName");
        filters.setUserId("user123");
        filters.setTableSettings("settings");
        filters.setSave(true);

        when(service.addQuery(filters)).thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(MockMvcRequestBuilders.post("/vta/api/v1/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filters)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.message").value(ResponseStatus.INVALID_DATA.getMessage()));
    }

    @Test
    void testSaveAndBuildQuery() throws Exception {
        Filters filters = new Filters();
        filters.setServiceName("serviceName");
        filters.setUserId("user123");
        filters.setTableSettings("settings");
        filters.setSave(true);

        String data = service.addQuerybuild(filters);

        when(service.addQuerybuild(filters)).thenReturn(data);

        mockMvc.perform(MockMvcRequestBuilders.post("/vta/api/v1/buildQuery")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filters)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.message").value("No Data Found"));
              //  .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testSaveAndBuildQuery_Exception() throws Exception {
        Filters filters = new Filters();
        filters.setServiceName("serviceName");
        filters.setUserId("user123");
        filters.setTableSettings("settings");
        filters.setSave(true);

        when(service.addQuerybuild(filters)).thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(MockMvcRequestBuilders.post("/vta/api/v1/buildQuery")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filters)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.message").value(Constants.FAILURE_BUILD));
    }

//    @Test
//    void testDeleteTable() throws Exception {
//        String userId = "user123";
//        String serviceName = "serviceName";
//        String filters = "settings";
//
//        when(service.deleteTable(serviceName, userId, filters)).thenReturn(true);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/vta/api/v1/delete/{tableSetting}", filters)
//                .param("userId", userId)
//                .param("serviceName", serviceName))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(true))
//                .andExpect(jsonPath("$.message").value(Constants.TABLE_DELETED));
//    }

//				    @Test
//				    void testDeleteTable_Exception() throws Exception {
//				        String userId = "user123";
//				        String serviceName = "serviceName";
//				        String filters = "settings";
//				
//				        when(service.deleteTable(serviceName, userId, filters)).thenThrow(new RuntimeException("Unexpected error"));
//				
//				        mockMvc.perform(MockMvcRequestBuilders.delete("/vta/api/v1/delete/{tableSetting}", filters)
//				                .param("userId", userId)
//				                .param("serviceName", serviceName))
//				                .andExpect(status().isInternalServerError())
//				                .andExpect(jsonPath("$.status").value(false))
//				                .andExpect(jsonPath("$.message").value(Constants.FAILURE_DELETE));
//				    }

    @Test
    void testGetDefaultTableSettings() throws Exception {
        String userId = "user123";
        String serviceName = "serviceName";
        Filters filters = new Filters();

        when(service.getEntityFilterForUser(userId, serviceName)).thenReturn(filters);

        mockMvc.perform(MockMvcRequestBuilders.get("/vta/api/v1/Default/{userId}/{serviceName}", userId, serviceName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.message").value(ResponseStatus.OK.getMessage()))
                .andExpect(jsonPath("$.data").exists());
    }

    @Test
    void testGetDefaultTableSettings_Exception() throws Exception {
        String userId = "user123";
        String serviceName = "serviceName";

        when(service.getEntityFilterForUser(userId, serviceName)).thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(MockMvcRequestBuilders.get("/vta/api/v1/Default/{userId}/{serviceName}", userId, serviceName))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.message").value(ResponseStatus.INVALID_DATA.getMessage()));
    }

    @Test
    void testGetAllTableSettingsForUser() throws Exception {
        String userId = "user123";
        String serviceName = "serviceName";
        Filters filters = new Filters();

        when(service.getAllTableSettingsForUser(userId, serviceName)).thenReturn(Collections.singletonList(filters));

        mockMvc.perform(MockMvcRequestBuilders.get("/vta/api/v1/Filters/{userId}/{serviceName}", userId, serviceName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.message").value(ResponseStatus.OK.getMessage()))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testGetAllTableSettingsForUser_Exception() throws Exception {
        String userId = "user123";
        String serviceName = "serviceName";

        when(service.getAllTableSettingsForUser(userId, serviceName)).thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(MockMvcRequestBuilders.get("/vta/api/v1/Filters/{userId}/{serviceName}", userId, serviceName))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.message").value(ResponseStatus.INVALID_DATA.getMessage()));
    }
    
  
//    @Test
//    void testDeleteTable_Success() throws Exception {
//        String userId = "user123";
//        String serviceName = "serviceName";
//        String filters = "settings";
//
//        when(service.deleteTable(serviceName, userId, filters)).thenReturn(true);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/vta/api/v1/delete/{tableSetting}", filters)
//                .param("userId", userId)
//                .param("serviceName", serviceName))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(true))
//                .andExpect(jsonPath("$.message").value(Constants.TABLE_DELETED))
//                .andExpect(jsonPath("$.data").value(filters));
//    }

//    @Test
//    void testDeleteTable_NoData() throws Exception {
//        String userId = "user123";
//        String serviceName = "serviceName";
//        String filters = "settings";
//
//        when(service.deleteTable(serviceName, userId, filters)).thenReturn(false);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/vta/api/v1/delete/{tableSetting}", filters)
//                .param("userId", userId)
//                .param("serviceName", serviceName))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.status").value(false))
//                .andExpect(jsonPath("$.message").value("the data not found in database"))
//                .andExpect(jsonPath("$.data").value(filters));
//    }

    @Test
    void testDeleteTable_Exception() throws Exception {
        String userId = "user123";
        String serviceName = "serviceName";
        String filters = "settings";

        when(service.deleteTable(serviceName, userId, filters)).thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/vta/api/v1/delete/{tableSetting}", filters)
                .param("userId", userId)
                .param("serviceName", serviceName))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(false))
                .andExpect(jsonPath("$.message").value(Constants.FAILURE_DELETE));
                //.andExpect(jsonPath("$.data").value(filters));
    }
    
    
    
    
    
    
    
    
    @Test
    void testDeleteTableSuccess() {
        String userId = "user1";
        String serviceName = "service1";
        String filters = "filters";

        when(service.deleteTable(serviceName, userId, filters)).thenReturn(true);

        ResponseEntity<Response> response = controller.deleteTable(userId, serviceName, filters);
 //       assertEquals(ResponseStatus.OK, response.getBody().isStatus());
        assertEquals("Table setting deleted successfully", response.getBody().getMessage());
    }

    @Test
    void testDeleteTableNoData() {
        String userId = "user1";
        String serviceName = "service1";
        String filters = "filters";

        when(service.deleteTable(serviceName, userId, filters)).thenReturn(false);

        ResponseEntity<Response> response = controller.deleteTable(userId, serviceName, filters);
       // assertEquals(ResponseStatus.NODATA, response.getBody().isStatus());
        assertEquals("the data not found in database", response.getBody().getMessage());
    }
    
    
    
    
    
    
    
    @Test
    void testAddNodeFiltersAlreadyExists() {
        Filters filters = new Filters();
        // Simulate the service returning null, indicating the filter already exists
        when(service.addQuery(filters)).thenReturn(null);

        ResponseEntity<Response> response = controller.addNodeFilters(filters);
        assertEquals(ResponseStatus.ALREADY_EXISTS.getHttpStatus(), response.getStatusCode());
        assertEquals(Constants.ALREADY_EXISTS, response.getBody().getMessage());
        assertEquals(null, response.getBody().getData());
    }
    
    
    @Test
    void testAddNodeFilters_ResourceExistsException() throws Exception {
        Filters filters = new Filters();
        filters.setServiceName("serviceName");
        filters.setUserId("user123");
        filters.setTableSettings("settings");
        filters.setSave(true);

        // Ensure the mock service throws the correct exception
        when(service.addQuery(filters)).thenThrow(new IllegalStateException(Constants.RESOURCE));

        mockMvc.perform(MockMvcRequestBuilders.post("/vta/api/v1/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filters)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.message").value(ResponseStatus.RESOURCE_EXISTS.getMessage()));
    }

}
