package org.example.files.storage.memory;

import org.example.files.File;
import java.util.HashMap;
import java.util.Map;

public class MapStorage {
    private HashMap<Integer, File> map;

    public MapStorage() {
        this.map = new HashMap<>();
    }

    public File store(Integer id, byte[] bytes, HashMap<String, String> labels) {
        File newFile = new File(bytes, labels);
        map.put(id, newFile);
        return newFile;
    }

    public boolean fileExists(Integer id) {
        return map.containsKey(id);
    }

    public File getFile(Integer id) {
        return map.get(id);
    }
}