package org.example.files;

public class FileMetadata {
    private long size;
    private String fileName;
    private int id;

    public FileMetadata(int id, long size, String fileName) {
        this.id = id;
        this.size = size;
        this.fileName = fileName;
    }

    public long getSize() {
        return size;
    }

    public String getFilename() {
        return fileName;
    }

    public int getID() {
        return id;
    }
}