package org.example.files.storage.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.files.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class MapStorageTest {

    @Test
    public void testStore() {
        MapStorage storage = new MapStorage();

        ArrayList<String> labels = new ArrayList<>();
        labels.add("name=jack");
        labels.add("location=nyc");
        storage.store(Long.valueOf(1), new byte[0], labels);

        File jackFile = storage.getFilesByLabel("name=jack").get(0);
        File nycFile = storage.getFilesByLabel("location=nyc").get(0);
        
        assertEquals(storage.getFilesByLabel("name=jack").size(), 1);
        assertEquals(storage.getFilesByLabel("location=nyc").size(), 1);
        assertEquals(jackFile, nycFile);
    }

    @Test
    public void testFileExists() {
        MapStorage storage = new MapStorage();

        ArrayList<String> labels = new ArrayList<>();
        labels.add("name=jack");
        storage.store(Long.valueOf(1), new byte[0], labels);

        assertTrue(storage.fileExists(Long.valueOf(1)));
        assertFalse(storage.fileExists(Long.valueOf(2)));
    }

    @Test
    public void testGetFilesByLabel() {
        MapStorage storage = new MapStorage();

        assertEquals(storage.getFilesByLabel("location=nyc"), new ArrayList<File>());

        ArrayList<String> labels = new ArrayList<>();
        labels.add("name=jack");
        labels.add("location=nyc");
        File f = storage.store(Long.valueOf(1), new byte[0], labels);

        assertEquals(storage.getFilesByLabel("name=jack").get(0), f);
        assertEquals(storage.getFilesByLabel("location=nyc").get(0), f);
    }
}
