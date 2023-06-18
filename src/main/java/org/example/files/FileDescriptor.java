package org.example.files;

import java.util.List;

public class FileDescriptor extends FileMetadata {
    private Long id;

    public FileDescriptor(Long counter, long size, List<String> labels) {
        super(size, labels);
        this.id = counter;
    }

    public Long getID() {
        return id;
    }
}