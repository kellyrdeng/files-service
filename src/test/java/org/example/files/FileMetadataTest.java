package org.example.files;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class FileMetadataTest {

    @Test
    public void testConstructor() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("name=jack");
        labels.add("location=nyc");

        FileMetadata fmd = new FileMetadata(0, labels);

        assertEquals(fmd.getSize(), Long.valueOf(0));
        assertEquals(fmd.getLabels(), labels);
    }
}
