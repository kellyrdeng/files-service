package org.example.files;

public class FileMetadata {
    private int size;
    private String fileName;

    public FileMetadata(int size, String fileName) {
        this.size = size;
        this.fileName = fileName;
    }

    public int getSize() {
        return size;
    }

    public String getFilename() {
        return fileName;
    }
}