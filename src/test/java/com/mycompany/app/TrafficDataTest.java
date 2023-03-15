package com.mycompany.app;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TrafficDataTest {

    @Test
    public void testConstructor() {
        TrafficData td = new TrafficData();
        assertEquals("", td.getIpAddress());
        assertEquals(0, td.getRequests());
        assertEquals(0, td.getBytesSent());
        assertEquals(0.0, td.getRequestPercentage(),  0.001);
        assertEquals(0.0, td.getBytesPercentage(),  0.001);

        td = new TrafficData("192.168.0.1");
        assertEquals("192.168.0.1", td.getIpAddress());
        assertEquals(0, td.getRequests());
        assertEquals(0, td.getBytesSent());
        assertEquals(0.0, td.getRequestPercentage(),  0.001);
        assertEquals(0.0, td.getBytesPercentage(),  0.001);

        td = new TrafficData("192.168.0.1", 10, 1024L);
        assertEquals("192.168.0.1", td.getIpAddress());
        assertEquals(10, td.getRequests());
        assertEquals(1024L, td.getBytesSent());
        assertEquals(0.0, td.getRequestPercentage(),  0.001);
        assertEquals(0.0, td.getBytesPercentage(),  0.001);
    }

    @Test
    public void testAccessors() {
        TrafficData td = new TrafficData("192.168.0.1", 10, 1024L);
        td.setRequestPercentage(50.0);
        td.setBytesPercentage(25.0);
        assertEquals("192.168.0.1", td.getIpAddress());
        assertEquals(10, td.getRequests());
        assertEquals(1024L, td.getBytesSent());
        assertEquals(50.0, td.getRequestPercentage(),  0.001);
        assertEquals(25.0, td.getBytesPercentage(),  0.001);

        td.setIpAddress("192.168.0.2");
        td.setRequests(20);
        td.setBytesSent(2048L);
        assertEquals("192.168.0.2", td.getIpAddress());
        assertEquals(20, td.getRequests());
        assertEquals(2048L, td.getBytesSent());
    }

}