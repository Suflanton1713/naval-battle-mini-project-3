package com.example.navalbattleminiproject3.model.board.GameData;

/**
 * Interface that defines the operations for handling data files, such as reading, writing, and serializing data.
 * Any class implementing this interface should provide concrete implementations of these methods.
 * @author Maria Juliana Saavedra, Libardo Alejandro Quintero, Juan David Rincon Lopez
 * @version 1.0
 */
public interface IData {

    /**
     * Rewrites the content of a file with the given content.
     * @param fileName the name of the file to rewrite.
     * @param content the content to write to the file.
     * @version 1.0
     */
    void rewriteFile(String fileName, String content);

    /**
     * Writes content to a file.
     * @param fileName the name of the file to write to.
     * @param content the content to write to the file.
     * @version 1.0
     */
    void writeToFile(String fileName, String content);

    /**
     * Reads content from a file and returns it as an array of strings.
     * @param fileName the name of the file to read from.
     * @return a {@code String[]} containing the content of the file.
     * @version 1.0
     */
    String[] readFromFile(String fileName);

    /**
     * Serializes the provided content and writes it to a file.
     * @param fileName the name of the file to serialize data into.
     * @param content the content to serialize and save to the file.
     * @version 1.0
     */
    void serializeData(String fileName, String content);

    /**
     * Deserializes data from a file and returns the object.
     * @param fileName the name of the file to deserialize data from.
     * @return the deserialized object from the file.
     * @version 1.0
     */
    Object deserializeData(String fileName);
}
