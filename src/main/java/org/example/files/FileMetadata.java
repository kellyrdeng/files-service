package org.example.files;

import java.util.HashMap;

public class FileMetadata {
    private long size;
    private HashMap<String, String> labels;

    public FileMetadata(long size, HashMap<String, String> labels) {
        this.size = size;
        this.labels = labels;
    }

    public long getSize() {
        return size;
    }

    public HashMap<String, String> getLabels() {
        return labels;
    }
}