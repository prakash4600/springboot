package com.fujitsu.fnc.vta.settingsmanager.component;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.fujitsu.fnc.vta.settingsmanager.service.SettingsService;

@ExtendWith(MockitoExtension.class)
class ComponentTest {

    @Mock
    private SettingsService service;

    @InjectMocks
    private ComponentScheduler ComponentScheduler;

    @BeforeEach
    void setUp() {
        // Inject the value of the interval into the component using ReflectionTestUtils
        ReflectionTestUtils.setField(ComponentScheduler, "interval", 1000L);
    }

    @Test
    void testPerformTaskWithFixedRate() {
        ComponentScheduler.performTaskWithFixedRate();
        verify(service).runSchedulerjob();
    }
}
