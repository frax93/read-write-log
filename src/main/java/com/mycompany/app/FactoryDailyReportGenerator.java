package com.mycompany.app;

public class FactoryDailyReportGenerator {
  public static DailyReportGenerator create(ReportType type)  {
    DailyReportGenerator reportGenerator = null;
    if (type != null) {

      switch (type) {
        case CSV:
          reportGenerator = new CsvDailyReportGenerator();
          break;
        case JSON:
          reportGenerator = new JsonDailyReportGenerator();
          break;
        default:
          break;
      }
    }

    return reportGenerator;
  }
}
