package com.fujitsu.fnc.vta.settingsmanager.controller;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fujitsu.fnc.vta.settingsmanager.enums.ResponseStatus;
import com.fujitsu.fnc.vta.settingsmanager.model.Constants;
import com.fujitsu.fnc.vta.settingsmanager.model.Filters;
import com.fujitsu.fnc.vta.settingsmanager.model.Response;
import com.fujitsu.fnc.vta.settingsmanager.service.SettingsService;


@RequestMapping("/vta/api/v1")
@RestController
public class SettingController {
	
private static Logger logger = LogManager.getLogger(SettingController.class);
	
	private SettingsService service;
	
	public SettingController(SettingsService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Response> addNodeFilters(@RequestBody Filters filters) {
	try {
		logger.info(Constants.BRACKETS, Constants.FILTER, filters);
		
		ResponseStatus responseStatus = null;

		Filters	 resp  = service.addQuery(filters);
		logger.info(Constants.BRACKETS, Constants.FILTER,!ObjectUtils.isEmpty(resp));
		if(resp != null) {
         responseStatus = !ObjectUtils.isEmpty(resp) ? ResponseStatus.SUCCESS : ResponseStatus.FAILURE;
		}else {
			responseStatus = ResponseStatus.ALREADY_EXISTS;	
			return ResponseEntity.status(responseStatus.getHttpStatus()) 
	                .body(Response.builder()
	                        .status(!ObjectUtils.isEmpty(resp))
	                        .message(Constants.ALREADY_EXISTS)
	                        .data(resp)
	                        .build());
		}
        
      return ResponseEntity.status(responseStatus.getHttpStatus()) 
                .body(Response.builder()
                        .status(!ObjectUtils.isEmpty(resp))
                        .message(resp.getSave()?responseStatus.getMessage():"data not saved into database")
                        .data(resp)
                        .build());
	}
    catch (IllegalArgumentException e) {
      logger.error(Constants.BRACKETS, Constants.ERROR, e.getMessage(), e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(Response.builder()
                      .status(false)
                      .message("Save parameter must be true to save data.")
                      .build());
  } catch (Exception e) {
      ResponseStatus responseStatus;
      if (e.getMessage() != null && e.getMessage().contains(Constants.RESOURCE)) {
          responseStatus = ResponseStatus.RESOURCE_EXISTS;
      } else {
          responseStatus = ResponseStatus.INVALID_DATA;
      }
      logger.error(Constants.BRACKETS, Constants.ERROR, e.getMessage(), e);
      return ResponseEntity.status(responseStatus.getHttpStatus())
              .body(Response.builder()
                      .status(true)
                      .message(responseStatus.getMessage())
                      .build());
        }
    }
	

	@PostMapping("/buildQuery")
	public ResponseEntity<Response> saveAndBuildQuery(@RequestBody Filters filters) {
	    try {
	        logger.info(Constants.BRACKETS, Constants.BUILD_QUERY, filters);
	        
	        
	        String savedSettings = service.addQuerybuild(filters);
	       
	        return ResponseEntity.ok(Response.builder()
	                .status(!ObjectUtils.isEmpty(savedSettings)) 
	                .message(!ObjectUtils.isEmpty(savedSettings) ?Constants.QUERY_SUCCESS:Constants.NODATA)
	                .data(savedSettings) 
	                .build());
	    } catch (Exception e) {
	        logger.error(Constants.BRACKETS, Constants.ERROR_BUILD, e.getMessage(), e);
	        
	        
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Response.builder()
	                        .status(false)
	                        .message(Constants.FAILURE_BUILD )
	                        .build());
	    }
	}

	
	@DeleteMapping("/delete/{tableSetting}")
    public ResponseEntity<Response> deleteTable(String userId, String serviceName , String filters) {
        try {
            logger.info(Constants.BRACKETS, Constants.DELETE_TABLE, filters);

            boolean resp = service.deleteTable(serviceName,userId,filters);
            
            if(resp) {
            return ResponseEntity.ok(Response.builder()
                    .status(!ObjectUtils.isEmpty(resp))
                    .message(!ObjectUtils.isEmpty(resp)?Constants.TABLE_DELETED:Constants.FAILURE_DELETE)
                    .data(filters) 
                    .build());
            }
            else {
            	return ResponseEntity.ok(Response.builder()
                        .status(!ObjectUtils.isEmpty(resp))
                        .message("the data not found in database")
                        .data(filters) 
                        .build());
            }
        } catch (Exception e) {
            logger.error(Constants.BRACKETS, Constants.ERROR_DELETE, e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Response.builder()
                            .status(false)
                            .message(Constants.FAILURE_DELETE)
                            .data(filters) 
                            .build());
        }
    }
	

	
	@GetMapping("/Default/{userId}/{serviceName}")
	public ResponseEntity<Response> getDefaultTableSettings(@PathVariable String userId, @PathVariable String serviceName) {
	    try {
	    	logger.info(Constants.BRACKETS,Constants.FILTER_GET_ID , serviceName);
	        Filters resp = service.getEntityFilterForUser(userId, serviceName);
	        logger.info(Constants.BRACKETS, Constants.FILTER_GET, !ObjectUtils.isEmpty(resp));
	        ResponseStatus status = !ObjectUtils.isEmpty(resp) ? ResponseStatus.OK : ResponseStatus.NODATA;
	        
	        return ResponseEntity.status(HttpStatus.OK) 
	                .body(Response.builder()
	                        .status(!ObjectUtils.isEmpty(resp))
	                        .message(status.getMessage())
	                        .data(resp)
	                        .build());
	    
	    } catch (Exception e) {
	    	ResponseStatus responseStatus = ResponseStatus.INVALID_DATA;
          logger.error(Constants.BRACKETS,Constants.ERROR ,e.getMessage(), e); 
          return ResponseEntity.status(responseStatus.getHttpStatus())
                  .body(Response.builder()
                          .status(false)
                          .message(responseStatus.getMessage())
                          .build());
	    }
	}

		
	@GetMapping("/Filters/{userId}/{serviceName}")
	public ResponseEntity<Response> getAllTableSettingsForUser(@PathVariable String userId, @PathVariable String serviceName) {
	    try {
	    	logger.info(Constants.BRACKETS,Constants.FILTER_GET_ID , serviceName);
	        List<Filters> resp = service.getAllTableSettingsForUser(userId, serviceName);
	        logger.info(Constants.BRACKETS, Constants.FILTER_GET, !ObjectUtils.isEmpty(resp));
	        ResponseStatus status = !ObjectUtils.isEmpty(resp) ? ResponseStatus.OK : ResponseStatus.NODATA;
	        
	        return ResponseEntity.status(HttpStatus.OK) 
	                .body(Response.builder()
	                        .status(!ObjectUtils.isEmpty(resp))
	                        .message(status.getMessage())
	                        .data(resp)
	                        .build());
	    
	    } catch (Exception e) {
	    	ResponseStatus responseStatus = ResponseStatus.INVALID_DATA;
          logger.error(Constants.BRACKETS,Constants.ERROR ,e.getMessage(), e); 
          return ResponseEntity.status(responseStatus.getHttpStatus())
                  .body(Response.builder()
                          .status(false)
                          .message(responseStatus.getMessage())
                          .build());
	    }
	}	

}
