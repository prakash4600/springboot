package com.fujitsu.fnc.vta.settingsmanager.service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fujitsu.fnc.vta.settingsmanager.model.Constants;
import com.fujitsu.fnc.vta.settingsmanager.model.FilterSet;
import com.fujitsu.fnc.vta.settingsmanager.model.Filters;
import com.fujitsu.fnc.vta.settingsmanager.model.SortList;
import com.mongodb.MongoException;
import com.mongodb.client.result.DeleteResult;

@Service
public class SettingsService {
	private MongoTemplate mongoTemplate;
	
	@Value("${scheduler.past.months}")
	int pastMonths;
    String serviceName1 = "serviceName";
    String userId1 = "userId";
    String makeMyDefault1 = "makeMyDefault";
	
	public SettingsService(MongoTemplate mongoTemplate) {
		super();
		this.mongoTemplate = mongoTemplate;
	}
	
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Date date = new Date();

	
	public Filters addQuery(Filters filters) {

		Criteria criteria = new Criteria().orOperator(
				Criteria.where(serviceName1).is(filters.getServiceName())
				.and(userId1).is(filters.getUserId())
				.and("tableSettings").is(filters.getTableSettings()));
		
		Query query = Query.query(criteria);
		Filters existingSetting = mongoTemplate.findOne(query, Filters.class);
		if (existingSetting != null) {
		    return null;
		}
	
		if (filters.getSave() && filters.getMakeMyDefault()) {
	        // Check if there are other tablesettings with makeMyDefault true
			Criteria criteria1 = new Criteria().orOperator(
		        // Default settings condition
					Criteria.where(serviceName1).is(filters.getServiceName())
					.and(userId1).is(filters.getUserId())
					.and("makeMyDefault").is(true));
	        Query makeDefaultQuery = Query.query(criteria1);
	        Filters settingsWithMakeDefault = mongoTemplate.findOne(makeDefaultQuery, Filters.class);

	       
	        if(settingsWithMakeDefault != null) {
	            settingsWithMakeDefault.setMakeMyDefault(false);
	            mongoSaving(settingsWithMakeDefault);
	        }  
	      }
	
		
		if (filters.getSave()) {
            return mongoSaving(filters);
       }
		
		return filters;
	}
	
	
	
	public List<Filters> getAllTableSettingsForUser(String userId, String serviceName) {
	Query query = Query.query(Criteria.where(Constants.USERID).is(userId).andOperator(Criteria.where(Constants.TABLENAME).is(serviceName)));
	return mongoTemplate.find(query,Filters.class);
	
}
	

	public Filters getEntityFilterForUser(String userId, String serviceName) {
        
        Query query ;
        // Check if there exists a default setting for the user and service
        Filters defaultSettings = mongoTemplate.findOne(
                Query.query(
                        Criteria.where(userId1).is(userId)
                                .and(serviceName1).is(serviceName)
                                .and(makeMyDefault1).is(true)
                ),
                Filters.class
        );

        if (defaultSettings != null) {
        	return defaultSettings;
        } 
        else {
                // If no default settings exist, return system / other table settings
                Filters systemSettings = mongoTemplate.findOne(
                        Query.query(
                                Criteria.where(userId1).is(userId)
                                        .and(serviceName1).is(serviceName)
                                        .and("systemDefault").is(true)
                                       
                        ),
                        Filters.class
                );

               
              // Return the system settings if found, otherwise return null or handle as needed
                if(systemSettings != null ){
                	return systemSettings;
                }else { 
                	return null;
                }
                
        	}
		
	}
	
	
	public String addQuerybuild(Filters filters) throws Exception {
		try {
		if (filters.getSave() != null && filters.getSave()) {
	        mongoSaving(filters);
	    }
	        Query query =  buildQuery(filters);
	      	
	          String response = query.toString();
	          
	            return response;
	         
	   
		} catch (NullPointerException e) {
	        throw new NullPointerException("Null value encountered");
		} catch (MongoException e) {
	        throw new IOException("MongoDB exception occurred", e);
	    }
	}
	
	public Query buildQuery(Filters filters) {
		
        Query query = new Query();
        Criteria criteria = buildCriteria(filters.getFilter());
        query.addCriteria(criteria);	        
        if (filters.getSortingOrder() != null && filters.getSortingOrder().getSortList() != null) {
            for(SortList sort : filters.getSortingOrder().getSortList()) {
                if ("asc".equalsIgnoreCase(sort.getOrder())) {
                    query.with(Sort.by(Sort.Order.asc(sort.getColumnName())));
                } else if ("desc".equalsIgnoreCase(sort.getOrder())) {
                    query.with(Sort.by(Sort.Order.desc(sort.getColumnName())));
                }
            }
        }
        if(filters.getColumnNames() != null ) {
        	filters.getColumnNames().forEach(field -> query.fields().include(field));	
        }
        return query;
    }
	
	
	public Criteria buildCriteria(FilterSet filterSet) {
        Criteria criteria = new Criteria();
        List<FilterSet.Rule> rules = filterSet.getRules();	        
        if ("and".equalsIgnoreCase(filterSet.getCondition())) {
            Criteria[] andCriteria = new Criteria[rules.size()];
            for (int i = 0; i < rules.size(); i++) {
                andCriteria[i] = createCriteriaFromRule(rules.get(i));
            }
            criteria.andOperator(andCriteria);
        } else if ("or".equalsIgnoreCase(filterSet.getCondition())) {
            Criteria[] orCriteria = new Criteria[rules.size()];
            for (int i = 0; i < rules.size(); i++) {
                orCriteria[i] = createCriteriaFromRule(rules.get(i));
            }
            criteria.orOperator(orCriteria);
        }

        return criteria;
    }
	
	
	public Criteria createCriteriaFromRule(FilterSet.Rule rule) {
        validateRule(rule);  

        if (rule.getOperator() == null && rule.getField().isEmpty()) {
            return null;  
        } else {
            switch (rule.getOperator().toLowerCase()) {
                case "equal":
                    return Criteria.where(rule.getField()).is(rule.getValue());
                case "startswith":
                    return Criteria.where(rule.getField()).regex("^" + rule.getValue());
             
                default:
                    throw new UnsupportedOperationException("Unsupported operator: " + rule.getOperator());
            }
        }
    }
	
	public void validateRule(FilterSet.Rule rule) {
        Objects.requireNonNull(rule.getField(), "Field cannot be null");
        Objects.requireNonNull(rule.getOperator(), "Operator cannot be null");
        Objects.requireNonNull(rule.getValue(), "Value cannot be null");
    }
	
	
	
	public boolean deleteTable(String serviceName, String userId,String filters) {
        	
        	Criteria criteria = new Criteria().andOperator(
        			Criteria.where(Constants.USERID).is(userId),
        			Criteria.where(Constants.TABLENAME).is(serviceName),
        			Criteria.where(Constants.TABLE_SETTINGS).is(filters)
                );

                Query query = new Query(criteria);

                DeleteResult exists = mongoTemplate.remove(query, Filters.class);
               
                	return exists.getDeletedCount()>0;
            
    }
	
	public Filters mongoSaving( Filters filters) {
		filters.setTimeStamp(dateFormat.format(date));	
		mongoTemplate.save(filters);
		return filters;
	}
	public void runSchedulerjob() {
	    Calendar cl = Calendar.getInstance();
	    cl.setTime(date);
	    cl.add(Calendar.MONTH, pastMonths); 
	    String ttleave = dateFormat.format(date);
	    Query query = new Query();
	    query.addCriteria(Criteria.where("timeStamp").lte(ttleave)).addCriteria(Criteria.where(makeMyDefault1).is(false));
	    mongoTemplate.remove(query, Filters.class);
	}

}

