package org.example.files;

import java.util.ArrayList;
import java.util.HashMap;

public class FileMetadata {
    private long size;
    private ArrayList<String> labels;

    public FileMetadata(long size, ArrayList<String> labels) {
        this.size = size;
        this.labels = labels;
    }

    public long getSize() {
        return size;
    }

    public ArrayList<String> getLabels() {
        return labels;
    }
}