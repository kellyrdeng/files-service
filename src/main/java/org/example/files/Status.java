package org.example.files;

public class Status {
    private String status;
    private long count;

    public Status(String status, long count) {
        this.status = status;
        this.count = count;
    }

    public String getStatus() {
        return this.status;
    }

    public long getCount() {
        return this.count;
    }
}