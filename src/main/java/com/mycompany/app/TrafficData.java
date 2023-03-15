package com.mycompany.app;

public class TrafficData implements Comparable<TrafficData> {
    private String ipAddress;
    private int requests;
    private long bytesSent;
    private double requestPercentage;
    private double bytesPercentage;
    
    public TrafficData() {
        this.ipAddress = "";
        this.requests = 0;
        this.bytesSent = 0;
    }

    public TrafficData(String ipAddress) {
        this.ipAddress = ipAddress;
        this.requests = 0;
        this.bytesSent = 0;
        this.requestPercentage = 0;
        this.bytesPercentage = 0;
    }

    public TrafficData(String ipAddress, int requests, long bytesSent) {
        this.ipAddress = ipAddress;
        this.requests = requests;
        this.bytesSent = bytesSent;
        this.requestPercentage = 0;
        this.bytesPercentage = 0;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getRequests() {
        return requests;
    }

    public long getBytesSent() {
        return bytesSent;
    }

    public double getRequestPercentage() {
        return requestPercentage;
    }

    public void setRequestPercentage(double requestPercentage) {
        this.requestPercentage = requestPercentage;
    }

    public double getBytesPercentage() {
        return bytesPercentage;
    }

    public void setBytesPercentage(double bytesPercentage) {
        this.bytesPercentage = bytesPercentage;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }

    public void setBytesSent(long bytesSent) {
        this.bytesSent = bytesSent;
    }

    @Override
    public int compareTo(TrafficData other) {
        return Integer.compare(other.requests, this.requests);
    }
}
