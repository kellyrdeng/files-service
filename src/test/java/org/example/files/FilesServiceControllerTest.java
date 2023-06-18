package org.example.files;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

public class FilesServiceControllerTest {

    @Test
    public void testLabelsToList_singleLabel() {
        String input = "name:kelly";
        ArrayList<String> result = FilesServiceController.labelsToList(input);
        
        assertEquals(1, result.size());
        assertEquals("name:kelly", result.get(0));
    }

    @Test
    public void testLabelsToList_multipleLabel() {
        String input = "name:kelly,location:nyc,mood:happy";
        ArrayList<String> result = FilesServiceController.labelsToList(input);
        
        assertEquals(3, result.size());
        assertEquals("name:kelly", result.get(0));
        assertEquals("location:nyc", result.get(1));
        assertEquals("mood:happy", result.get(2));
    }

    @Test
    public void testLabelsToList_invalidFormat() {
        String input = "name:kellylocation:nyc";
        Assertions.assertThrows(
            ResponseStatusException.class,
            () -> FilesServiceController.labelsToList(input));
    }
}
