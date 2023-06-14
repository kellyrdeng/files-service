package org.example.files;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class FileTest {

    @Test
    public void testConstructor() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("name=jack");
        labels.add("location=nyc");
        File f = new File(Long.valueOf(1), new byte[0], labels);

        assertEquals(f.getID(), Long.valueOf(1));
        assertEquals(f.getContents().length, 0);
        assertEquals(f.getMetadata().getSize(), 0);
        assertEquals(f.getMetadata().getLabels(), labels);
    }
    
}
