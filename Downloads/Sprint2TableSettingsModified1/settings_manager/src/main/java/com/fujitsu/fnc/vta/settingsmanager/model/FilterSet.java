package com.fujitsu.fnc.vta.settingsmanager.model;

import java.util.List;

import lombok.Data;

@Data
public class FilterSet {
    private String condition;  
    private List<Rule> rules; 

    @Data
    public static class Rule {
        private String field;
        private String operator;
        private String type;
        private Object value;
    }
}
