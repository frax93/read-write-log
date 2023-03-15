package com.mycompany.app;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

public class CsvDailyReportGenerator extends DailyReportGenerator {
    @Override
    public void generate() throws IOException {
        Map<String, TrafficData> data = super.readFile();

        // Write the sorted data to a CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/reports/ipaddr.csv"))) {
            bw.write(
                    "IP Address,Number of requests,Percentage of total requests,Total Bytes sent,Percentage of total bytes\n");
            for (Map.Entry<String, TrafficData> entry : data.entrySet()) {
                String ipAddress = entry.getKey();
                TrafficData trafficData = entry.getValue();
                DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.getDefault());
                decimalFormatSymbols.setDecimalSeparator('.');
                DecimalFormat fDecimalFormat = new DecimalFormat("#.##", decimalFormatSymbols);
                String percentageRequestsFormatted = fDecimalFormat.format(trafficData.getRequestPercentage());
                String percentageBytesFormatted = fDecimalFormat.format(trafficData.getBytesPercentage());

                bw.write(String.format("%s,%d,%s%%,%d,%s%%\n", ipAddress, trafficData.getRequests(),
                        percentageRequestsFormatted, trafficData.getBytesSent(),
                        percentageBytesFormatted));
            }
        }
    }
}
