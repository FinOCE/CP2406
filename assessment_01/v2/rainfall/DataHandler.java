package rainfall;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import rainfall.EntryHandler.NoEntriesException;

public class DataHandler {
    private HashMap<String, EntryHandler> datasets;

    public DataHandler() {
        datasets = new HashMap<String, EntryHandler>();
    }

    /**
     * Add a file to the dataset
     * 
     * @param file The file to add to the dataset
     */
    public void addFile(File file) throws IllegalArgumentException, IOException, NoEntriesException {
        EntryHandler dataset = new EntryHandler(file);
        datasets.put(file.getName(), dataset);
    }

    /**
     * Remove a file from the dataset
     * 
     * @param fileName The name of the file in the dataset
     * @throws IllegalArgumentException Occurs when the specified file did not exist
     */
    public void removeFile(String fileName) throws IllegalArgumentException {
        EntryHandler removal = datasets.remove(fileName);

        if (removal == null)
            throw new IllegalArgumentException("The provided file name does not exist in the dataset");
    }

    /**
     * Get the names of all files in the dataset
     * 
     * @return The names of all files
     */
    public Set<String> getFileNames() {
        return datasets.keySet();
    }

    /**
     * Get the entries from a given file from the dataset
     * 
     * @param fileName The name of the file to fetch entries from
     * @return The EntryHandler associated with the file
     * @throws IllegalArgumentException Occurs when the given file name does not
     *                                  exist
     */
    public EntryHandler getFileData(String fileName) throws IllegalArgumentException {
        var data = datasets.get(fileName);

        if (data == null)
            throw new IllegalArgumentException("The provided file name does not exist in the dataset");

        return data;
    }
}
