package com.fujitsu.fnc.vta.settingsmanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;
import org.springframework.boot.SpringApplication;

@SpringBootTest
class SettingsManagerApplicationTest {

    @Test
    void contextLoads() {
        // This method is used to check if the application context loads successfully
    	assertNotNull("");
    }

    @Test
    void mainTest() {
        // Mocking SpringApplication to verify that it runs the application without starting the actual context
        try (var mockedSpringApplication = mockStatic(SpringApplication.class)) {
            SettingsManagerApplication.main(new String[] {});
            mockedSpringApplication.verify(() -> SpringApplication.run(SettingsManagerApplication.class, new String[] {}));
        }
    }
}
