package com.mycompany.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class DailyReportGenerator {
    protected Map<String, TrafficData> readFile() throws IOException {

        Map<String, TrafficData> data = new HashMap<>();

        int totalRequests = 0;
        long totalBytes = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("src/logfiles/requests.log"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 4) {
                    // Invalid data, skip this line
                    continue;
                }

                // Parse the fields
                long bytes = Long.parseLong(parts[1].trim());
                String status = parts[2].trim();
                String ipAddress = parts[3].trim();

                if (!status.equals("OK")) {
                    // Invalid status, skip this line
                    continue;
                }

                TrafficData trafficData = null;

                // If it was inserted in Hashmap
                if (data.get(ipAddress) != null) {
                    trafficData = data.get(ipAddress);
                    int requests = trafficData.getRequests();
                    int requestSum = requests + 1;
                    trafficData.setRequests(requestSum);
                    long bytesSent = trafficData.getBytesSent() + bytes;
                    trafficData.setBytesSent(bytesSent);
                } else {
                    // Update the traffic data for this IP address
                    trafficData = new TrafficData(ipAddress);
                    int requests = trafficData.getRequests();
                    int requestSum = requests + 1;
                    trafficData.setRequests(requestSum);
                    long bytesSent = trafficData.getBytesSent() + bytes;
                    trafficData.setBytesSent(bytesSent);
                }

                data.put(ipAddress, trafficData);

                // Update the total traffic data
                totalRequests++;
                totalBytes += bytes;
            }
        }

        // Compute the percentage of traffic data for each IP address
        for (TrafficData trafficData : data.values()) {
            trafficData.setRequestPercentage((double) trafficData.getRequests() / totalRequests * 100.0);
            trafficData.setBytesPercentage((double) trafficData.getBytesSent() / totalBytes * 100.0);
        }

        // Sort the data by the number of requests
        Map<String, TrafficData> sortedData = new HashMap<>();
        data.entrySet().stream()
                .sorted(Map.Entry.<String, TrafficData>comparingByValue().reversed())
                .forEachOrdered(e -> sortedData.put(e.getKey(), e.getValue()));

        return sortedData;
    }

    public abstract void generate() throws IOException;

}
