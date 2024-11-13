package com.example.navalbattleminiproject3.model.board.GameData;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PlainTextGameData extends dataFileHandler {
    private String plainDataSaver;
    private Object serializableDataSaver;

    public void rewriteFile(String fileName, String content){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public void writeToFile(String fileName, String newContent){
        String[] content;
        String newName = (newContent.split(","))[0].trim();
        String newPuntuation = (newContent.split(","))[1];
        boolean needAppend = true;
        System.out.println(newName+newPuntuation);

        try{
            content = readFromFile(fileName);

            if(Arrays.asList(content).contains(newName)) {
                System.out.println("true si contiene nombre repetido en indice" +  Arrays.asList(content).indexOf(newName));
                int modificatorIndex = Arrays.asList(content).indexOf(newName);
                content[modificatorIndex + 1] = newPuntuation;
                needAppend = false;
                System.out.println("modifique puntuacion en indice "+(modificatorIndex + 1));
            }else{

                if(Arrays.asList(content).size()!=1){

                    newContent = "," + newName + "," + newPuntuation;
                }
            }





            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, needAppend))) {
                writer.write(newContent.toString());
                writer.flush();
            }

        }catch(IOException e1){
            e1.printStackTrace();
        }

    };


    public String[] readFromFile(String fileName){
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null){
                content.append(line.trim()).append(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString().split(",");
    }
}
