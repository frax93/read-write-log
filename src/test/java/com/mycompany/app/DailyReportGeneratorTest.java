package com.mycompany.app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;


public class DailyReportGeneratorTest {

    @Test
    public void testReadFile() {
        DailyReportGenerator generator = new DailyReportGenerator() {
            @Override
            public void generate() throws IOException {
                // not needed for this test
            }
        };
        try {
            Map<String, TrafficData> data = generator.readFile();
            assertFalse(data.isEmpty());
        } catch (IOException e) {
            fail("IOException thrown: " + e.getMessage());
        }
    }
}
