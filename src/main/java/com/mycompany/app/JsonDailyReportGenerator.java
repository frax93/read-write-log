package com.mycompany.app;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDailyReportGenerator extends DailyReportGenerator {
    @Override
    public void generate() throws IOException {
        Map<String, TrafficData> data = super.readFile();

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("src/reports/ipaddr.json"), data);
    }
}
