package org.example.files;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class File {
    private Long id;
    private byte[] contents;
    private FileMetadata metadata;

    public File(Long id, byte[] contents, List<String> labels) {
        this.id = id;
        this.contents = contents;
        metadata = new FileMetadata(contents.length, labels);
    }

    public byte[] getContents() {
        return this.contents;
    }

    public FileMetadata getMetadata() {
        return this.metadata;
    }

    public Long getID() {
        return id;
    }
}