package org.example.files.storage.memory;

import org.example.files.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage {
    private HashMap<String, ArrayList<File>> labelsMap; //label -> <File1, File2, File3>
    private HashMap<Long, File> idMap; // id -> File

    public MapStorage() {
        this.labelsMap = new HashMap<>();
        this.idMap = new HashMap<>();
    }

    public File store(Long counter, byte[] bytes, ArrayList<String> labels) {
        File newFile = new File(counter, bytes, labels);
        idMap.put(counter, newFile);

        for (String l : labels) {
            if (!labelsMap.containsKey(l)) {
                labelsMap.put(l, new ArrayList<>());
            }
            labelsMap.get(l).add(newFile);
        }

        return newFile;
    }

    public boolean fileExists(Long parsedID) {
        return idMap.containsKey(parsedID);
    }

    public File getFile(Long parsedID) {
        return idMap.get(parsedID);
    }

    public List<File> getFilesByLabel(String label) {
        if (labelsMap.containsKey(label)) {
            return List.of();
        }
        return labelsMap.get(label);
    }
}