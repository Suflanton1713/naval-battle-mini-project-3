package com.example.navalbattleminiproject3.model.board.GameData;

import java.io.*;

public class SerializableGameData extends dataFileHandler{

    private File directory;

    public SerializableGameData(){
        directory = new File("src/main/resources/com/example/navalbattleminiproject3/playerData");
        // creates a directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void serializeData(String fileName, Object element){
        File file = new File(directory, fileName);
        System.out.println("Serializing object in " + file);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(element);
            System.out.println("The object has been already serialized " + file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    };

    public Object deserializeData(String fileName){
        File file = new File(directory, fileName);
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            return (Object) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
