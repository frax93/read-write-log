package com.mycompany.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class FactoryDailyReportGeneratorTest {

    @Test
    public void testCreateCsvReportGenerator() {
        DailyReportGenerator generator = FactoryDailyReportGenerator.create(ReportType.CSV);
        assertNotNull(generator);
        assertTrue(generator instanceof CsvDailyReportGenerator);
    }

    @Test
    public void testCreateJsonReportGenerator() {
        DailyReportGenerator generator = FactoryDailyReportGenerator.create(ReportType.JSON);
        assertNotNull(generator);
        assertTrue(generator instanceof JsonDailyReportGenerator);
    }

    @Test
    public void testCreateUnknownReportGenerator() {
        DailyReportGenerator generator = FactoryDailyReportGenerator.create(null);
        assertNull(generator);
    }

}
