package org.example.files;

import java.util.HashMap;

public class FileDescriptor extends FileMetadata {
    private int id;

    public FileDescriptor(int id, long size, HashMap<String, String> labels) {
        super(size, labels);
        this.id = id;
    }

    public int getID() {
        return id;
    }
}