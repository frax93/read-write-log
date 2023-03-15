package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.IOException;
import java.io.File;
import java.util.Map;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonDailyReportGeneratorTest {

    @Test
    public void testGenerateException() {
        try {
            JsonDailyReportGenerator reportGenerator = new JsonDailyReportGenerator();
            reportGenerator.generate();
    
            // Assert that the file was created
            File file = new File("src/reports/ipaddr.json");
            assertTrue(file.exists());
    
            // Assert that the file is not empty
            assertTrue(file.length() > 0);
        } catch (IOException e) {
            fail("IOException thrown: " + e.getMessage());
        }
    }

    @Test
    public void testGenerate() throws IOException {
        // Generate the report
        JsonDailyReportGenerator reportGenerator = new JsonDailyReportGenerator();
        reportGenerator.generate();

        // Read the generated JSON file
        File jsonFile = new File("src/reports/ipaddr.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, TrafficData> data = objectMapper.readValue(jsonFile, new TypeReference<Map<String, TrafficData>>(){});

        // Check the content of the JSON file
        assertEquals(2, data.size());
        assertTrue(data.containsKey("192.168.1.1"));
        assertTrue(data.containsKey("192.168.1.2"));
        assertEquals(3, data.get("192.168.1.1").getRequests());
        assertEquals(1, data.get("192.168.1.2").getRequests());
        assertEquals(6144, data.get("192.168.1.1").getBytesSent());
        assertEquals(512, data.get("192.168.1.2").getBytesSent());
    }

}