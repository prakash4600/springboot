package com.fujitsu.fnc.vta.settingsmanager.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class FilterSetTest {

    @Test
    void testDefaultConstructor() {
        FilterSet filterSet = new FilterSet();
        assertNull(filterSet.getCondition());
        assertNull(filterSet.getRules());
    }

    @Test
    void testParameterizedConstructorAndGetters() {
        FilterSet filterSet = new FilterSet();
        filterSet.setCondition("condition");
        
        // Create a valid Rule object
        FilterSet.Rule rule = new FilterSet.Rule();
        rule.setOperator("equal");
        rule.setField("testField");
        rule.setType("String");
        rule.setValue("testValue");
        
        List<FilterSet.Rule> rules = Collections.singletonList(rule);
        filterSet.setRules(rules);

        assertEquals("condition", filterSet.getCondition());
        assertEquals(rules, filterSet.getRules());
    }

    @Test
    void testRuleGettersAndSetters() {
        FilterSet.Rule rule = new FilterSet.Rule();
        rule.setOperator("equal");
        rule.setField("testField");
        rule.setType("String");
        rule.setValue("testValue");

        assertEquals("equal", rule.getOperator());
        assertEquals("testField", rule.getField());
        assertEquals("String", rule.getType());
        assertEquals("testValue", rule.getValue());
    }

    @Test
    void testToString() {
        FilterSet filterSet = new FilterSet();
        filterSet.setCondition("condition");
        
        FilterSet.Rule rule = new FilterSet.Rule();
        rule.setOperator("equal");
        rule.setField("testField");
        rule.setType("String");
        rule.setValue("testValue");
        
        List<FilterSet.Rule> rules = Collections.singletonList(rule);
        filterSet.setRules(rules);

        String expectedToString = "FilterSet(condition=condition, rules=[FilterSet.Rule(field=testField, operator=equal, type=String, value=testValue)])";
        assertEquals(expectedToString, filterSet.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        FilterSet.Rule rule1 = new FilterSet.Rule();
        rule1.setOperator("equal");
        rule1.setField("testField");
        rule1.setType("String");
        rule1.setValue("testValue");

        FilterSet.Rule rule2 = new FilterSet.Rule();
        rule2.setOperator("equal");
        rule2.setField("testField");
        rule2.setType("String");
        rule2.setValue("testValue");

        assertEquals(rule1, rule2);
        assertEquals(rule1.hashCode(), rule2.hashCode());

        FilterSet filterSet1 = new FilterSet();
        filterSet1.setCondition("condition");
        filterSet1.setRules(Collections.singletonList(rule1));

        FilterSet filterSet2 = new FilterSet();
        filterSet2.setCondition("condition");
        filterSet2.setRules(Collections.singletonList(rule2));

        assertEquals(filterSet1, filterSet2);
        assertEquals(filterSet1.hashCode(), filterSet2.hashCode());
    }

    @Test
    void testRuleToString() {
        FilterSet.Rule rule = new FilterSet.Rule();
        rule.setOperator("equal");
        rule.setField("testField");
        rule.setType("String");
        rule.setValue("testValue");

        String expectedToString = "FilterSet.Rule(field=testField, operator=equal, type=String, value=testValue)";
        assertEquals(expectedToString, rule.toString());
    }

    @Test
    void testRuleEqualsAndHashCode() {
        FilterSet.Rule rule1 = new FilterSet.Rule();
        rule1.setOperator("equal");
        rule1.setField("testField");
        rule1.setType("String");
        rule1.setValue("testValue");

        FilterSet.Rule rule2 = new FilterSet.Rule();
        rule2.setOperator("equal");
        rule2.setField("testField");
        rule2.setType("String");
        rule2.setValue("testValue");

        FilterSet.Rule rule3 = new FilterSet.Rule();
        rule3.setOperator("notequal");
        rule3.setField("testField");
        rule3.setType("String");
        rule3.setValue("differentValue");

        assertEquals(rule1, rule2);
        assertNotEquals(rule1, rule3);
        assertEquals(rule1.hashCode(), rule2.hashCode());
        assertNotEquals(rule1.hashCode(), rule3.hashCode());
    }
}
