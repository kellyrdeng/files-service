package org.example.files;

import java.util.ArrayList;
import java.util.HashMap;

public class File {
    private int id;
    private byte[] contents;
    private FileMetadata metadata;

    public File(int id, byte[] contents, ArrayList<String> labels) {
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

    public int getID() {
        return id;
    }
}