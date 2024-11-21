package com.example.navalbattleminiproject3.model.board.GameData;

/**
 * Abstract class for handling data file operations, such as reading, writing, and serializing data.
 * This class provides abstract methods for file management operations that can be implemented in subclasses.
 * @author Maria Juliana Saavedra, Libardo Alejandro Quintero, Juan David Rincon Lopez
 * @version 1.0
 */
public abstract class DataFileHandler {

    /**
     * Rewrites the content of a file with the given content.
     * @param fileName the name of the file to rewrite.
     * @param content the content to write to the file.
     * @version 1.0
     */
    public void rewriteFile(String fileName, String content) {
        // This method can be implemented in a subclass to overwrite a file's content.
    }

    /**
     * Writes content to a file.
     * @param fileName the name of the file to write to.
     * @param content the content to write to the file.
     * @version 1.0
     */
    public void writeToFile(String fileName, String content) {
        // This method can be implemented in a subclass to write to a file.
    }

    /**
     * Reads content from a file and returns it as an array of strings.
     * @param fileName the name of the file to read from.
     * @return a {@code String[]} containing the content of the file.
     * @version 1.0
     */
    public String[] readFromFile(String fileName) {
        // This method can be implemented in a subclass to read from a file.
        return new String[0];
    }

    /**
     * Serializes the provided content and writes it to a file.
     * @param fileName the name of the file to serialize data into.
     * @param content the content to serialize and save to the file.
     * @version 1.0
     */
    public void serializeData(String fileName, String content) {
        // This method can be implemented in a subclass to serialize data into a file.
    }

    /**
     * Deserializes data from a file and returns the object.
     * @param fileName the name of the file to deserialize data from.
     * @return the deserialized object from the file.
     * @version 1.0
     */
    public Object deserializeData(String fileName) {
        // This method can be implemented in a subclass to deserialize data from a file.
        return null;
    }
}
