package com.mycompany.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppTest {

    @Test
    public void testApp() throws IOException {
        // Genera report CSV
        DailyReportGenerator generatorCSV = FactoryDailyReportGenerator.create(ReportType.CSV);
        generatorCSV.generate();

        // Verifica la presenza e il contenuto del file CSV generato
        assertTrue(Files.exists(Paths.get("src/reports/ipaddr.csv")));
        try (BufferedReader br = new BufferedReader(new FileReader("src/reports/ipaddr.csv"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                switch (i) {
                    case 0:
                        assertEquals(
                                "IP Address,Number of requests,Percentage of total requests,Total Bytes sent,Percentage of total bytes",
                                line);
                        break;
                    case 1:
                        assertEquals("192.168.1.1,3,75%,6144,92.31%", line);
                        break;
                    case 2:
                        assertEquals("192.168.1.2,1,25%,512,7.69%", line);
                        break;
                    default:
                        break;
                }
                i++;
            }
        }

        // Genera report JSON
        DailyReportGenerator generatorJSON = FactoryDailyReportGenerator.create(ReportType.JSON);
        generatorJSON.generate();

        // Verifica la presenza e il contenuto del file JSON generato
        assertTrue(Files.exists(Paths.get("src/reports/ipaddr.json")));
        String json = new String(Files.readAllBytes(Paths.get("src/reports/ipaddr.json")));
        assertEquals(
                "{\"192.168.1.1\":{\"ipAddress\":\"192.168.1.1\",\"requests\":3,\"bytesSent\":6144,\"requestPercentage\":75.0,\"bytesPercentage\":92.3076923076923},\"192.168.1.2\":{\"ipAddress\":\"192.168.1.2\",\"requests\":1,\"bytesSent\":512,\"requestPercentage\":25.0,\"bytesPercentage\":7.6923076923076925}}",
                json);
    }

    @Test
    public void testMain() {
        String[] args = {};
        App.main(args);
        assertTrue(new File("src/reports/ipaddr.csv").exists());
        assertTrue(new File("src/reports/ipaddr.json").exists());
    }
}
