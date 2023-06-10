package org.example.files;

public class Status {
    private String status;
    private int count;

    public Status(String status, int count) {
        this.status = status;
        this.count = count;
    }

    public String getStatus() {
        return this.status;
    }

    public int getCount() {
        return this.count;
    }
}