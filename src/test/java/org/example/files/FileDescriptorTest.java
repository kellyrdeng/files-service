package org.example.files;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class FileDescriptorTest {
    
    @Test
    public void testConstructor() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("name=jack");
        labels.add("location=nyc");
        FileDescriptor fd = new FileDescriptor(Long.valueOf(1), 0, labels);

        assertEquals(fd.getID(), Long.valueOf(1));
        assertEquals(fd.getSize(), Long.valueOf(0));
        assertEquals(labels, fd.getLabels());
    }
}
