package com.mycompany.app;

import java.io.IOException;

public class App 
{
    public static void main(String[] args) {
        try {
            DailyReportGenerator generatorCSV = FactoryDailyReportGenerator.create(ReportType.CSV);
            generatorCSV.generate();

            DailyReportGenerator generatorJSON = FactoryDailyReportGenerator.create(ReportType.JSON);
            generatorJSON.generate();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
