package org.example.files;

import java.util.HashMap;

public class File {
    private byte[] contents;
    private FileMetadata metadata;

    public File(byte[] contents, HashMap<String, String> labels) {
        this.contents = contents;
        metadata = new FileMetadata(contents.length, labels);
    }

    public byte[] getContents() {
        return this.contents;
    }

    public FileMetadata getMetadata() {
        return this.metadata;
    }
}