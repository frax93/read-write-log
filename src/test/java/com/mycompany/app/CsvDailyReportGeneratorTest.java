package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CsvDailyReportGeneratorTest {

    private final static String TEST_FILENAME = "src/reports/ipaddr.csv";

    private CsvDailyReportGenerator csvDailyReportGenerator;

    @Before
    public void setUp() {
        csvDailyReportGenerator = new CsvDailyReportGenerator();
    }

    @After
    public void tearDown() {
        csvDailyReportGenerator = null;
    }

    @Test
    public void testGenerateContent() throws IOException {
        csvDailyReportGenerator.generate();

        try (BufferedReader br = new BufferedReader(new FileReader(TEST_FILENAME))) {
            // Check the header row
            assertEquals(
                    "IP Address,Number of requests,Percentage of total requests,Total Bytes sent,Percentage of total bytes",
                    br.readLine());

            // Check the first data row
            String expectedFirstRow = "192.168.1.1,3,75%,6144,92.31%";
            String actualFirstRow = br.readLine();
            assertEquals(expectedFirstRow, actualFirstRow);

            // Check the second data row
            String expectedSecondRow = "192.168.1.2,1,25%,512,7.69%";
            String actualSecondRow = br.readLine();
            assertEquals(expectedSecondRow, actualSecondRow);

            // Check that there are no more rows
            assertEquals(null, br.readLine());
        }
    }

    @Test
    public void testGenerateException() {
        CsvDailyReportGenerator reportGenerator = new CsvDailyReportGenerator();
        try {
            reportGenerator.generate();
        } catch (IOException e) {
            fail("IOException was thrown");
        }
    }

}
