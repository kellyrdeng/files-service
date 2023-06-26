package org.example.files.storage.memory;

import org.example.files.File;
import org.example.files.storage.Storage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MapStorage implements Storage {
    private HashMap<String, ArrayList<File>> labelsMap; //label -> <File1, File2, File3>
    private HashMap<Long, File> idMap; // id -> File
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Lock writeLock = rwLock.writeLock();
    private Lock readLock = rwLock.readLock();

    public MapStorage() {
        this.labelsMap = new HashMap<>();
        this.idMap = new HashMap<>();
    }

    public File store(Long counter, byte[] bytes, List<String> labels) {
        File newFile;
        writeLock.lock();
 
        try {
            newFile = new File(counter, bytes, labels);
            idMap.put(counter, newFile);

            for (String l : labels) {
                if (!labelsMap.containsKey(l)) {
                    labelsMap.put(l, new ArrayList<>());
                }
                labelsMap.get(l).add(newFile);
            }
        } finally {
            writeLock.unlock();
        }

        return newFile;
    }

    public boolean fileExists(Long parsedID) {
        boolean exists = false;
        readLock.lock();
 
        try {
            exists = idMap.containsKey(parsedID);
        } finally {
            readLock.unlock();
        }
        return exists;
    }

    public File getFile(Long parsedID) {
        File f = null;
        readLock.lock();
 
        try {
            if (idMap.containsKey(parsedID)) {
                f = idMap.get(parsedID);
            }
        } finally {
            readLock.unlock();
        }
        return f;
    }

    public List<File> getFilesByLabel(String label) {
        List<File> l = List.of();
        readLock.lock();
 
        try {
            if (labelsMap.containsKey(label)) {
                l = labelsMap.get(label);
            }
        } finally {
            readLock.unlock();
        }
        
        return l;
    }
}