package com.example.navalbattleminiproject3.model.board.GameData;

import java.io.*;

/**
 * Class for handling the serialization and deserialization of game data objects.
 * This class provides methods to save and load objects to and from files using Java's Object Streams.
 * @author Maria Juliana Saavedra, Libardo Alejandro Quintero, Juan David Rincon Lopez
 * @version 1.0
 */
public class SerializableGameData extends DataFileHandler {

    private File directory;

    /**
     * Constructor that initializes the directory where serialized game data files are stored.
     * If the directory does not exist, it is created.
     * @version 1.0
     */
    public SerializableGameData(){
        directory = new File("src/main/resources/com/example/navalbattleminiproject3/playerData");
        // Creates a directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Serializes the given object and writes it to a file.
     * If the file already exists, it will be overwritten.
     * @param fileName the name of the file to serialize the object into.
     * @param element the object to serialize.
     * @version 1.0
     */
    public void serializeData(String fileName, Object element){
        File file = new File(directory, fileName);
        System.out.println("Serializing object in " + file);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(element);
            System.out.println("The object has been already serialized in " + file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes the object from a file.
     * If the file does not exist or the deserialization fails, it returns {@code null}.
     * @param fileName the name of the file to deserialize the object from.
     * @return the deserialized object, or {@code null} if an error occurred.
     * @version 1.0
     */
    @Override
    public Object deserializeData(String fileName){
        File file = new File(directory, fileName);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
