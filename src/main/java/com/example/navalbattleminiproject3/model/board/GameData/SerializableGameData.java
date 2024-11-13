package com.example.navalbattleminiproject3.model.board.GameData;

import java.io.*;

public class SerializableGameData extends dataFileHandler{

    public void serializeData(String fileName, String element){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            oos.writeObject(element);
        } catch (IOException e) {
            e.printStackTrace();
        }

    };

    public Object deserializeData(String fileName){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            return (Object) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
