package org.example.files.storage;

import org.example.files.File;
import java.util.List;

/**
 * An interface for storing files with their metadata, and querying/retrieving files
 */

public interface Storage {
    /**
     * Stores a specified file and returns the stored File object
     * 
     * @param id unique id number of the file to be stored
     * @param bytes byte array of the file's content
     * @param labels List of labels where each is of the format "key:value" lowercased
     * @return the stored File object
     */
    public File store(Long id, byte[] bytes, List<String> labels);

    /**
     * Checks if a file exists by its ID
     * 
     * @param id unique id number of the file to be checked
     * @return true if the file has previously been stored and false if not
     */
    public boolean fileExists(Long id);

    /**
     * Retrieves the File object with the specified id and null if it doesn't exist
     * 
     * @param id unique id number of the file to be retrieved
     * @return the retrieved File object or null if no such file exists
     */
    public File getFile(Long id);

    /**
     * Retreives all files that contain the specified label
     * 
     * @param label string of format "key:value" to search for files with
     * @return List of all files that contain the specified label
     */
    public List<File> getFilesByLabel(String label);
}
