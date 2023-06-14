package org.example.files;

import java.util.ArrayList;
import java.util.HashMap;

public class FileDescriptor extends FileMetadata {
    private Long id;

    public FileDescriptor(Long counter, long size, ArrayList<String> labels) {
        super(size, labels);
        this.id = counter;
    }

    public Long getID() {
        return id;
    }
}