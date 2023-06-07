package org.example.files.storage.memory;

import java.util.HashMap;
import java.util.Map;

public class MapStorage {
    private HashMap<Integer, byte[]> map;

    public MapStorage() {
        this.map = new HashMap<>();
    }

    public void store(Integer id, byte[] bytes) {
        map.put(id, bytes);
    }

    public boolean fileExists(Integer id) {
        return map.containsKey(id);
    }

    public long fileSize(Integer id) {
        return map.get(id).length;
    }
}