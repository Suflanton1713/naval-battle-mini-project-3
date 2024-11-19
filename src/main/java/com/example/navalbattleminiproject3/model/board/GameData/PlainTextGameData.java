package com.example.navalbattleminiproject3.model.board.GameData;

import java.io.*;
import java.util.Arrays;

public class PlainTextGameData extends DataFileHandler {
    private File directory;

    public PlainTextGameData(){
        directory = new File("src/main/resources/com/example/navalbattleminiproject3/playerData");
        // creates a directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void rewriteFile(String fileName, String content){
        // build the full route with fileName
        File file = new File(directory, fileName);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
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
        // build the full route with fileName
        File file = new File(directory, fileName);

        try{
            content = readFromFile(fileName);

            if(Arrays.asList(content).contains(newName)) {
                System.out.println("true si contiene nombre repetido en indice" +  Arrays.asList(content).indexOf(newName));
                System.out.println("el contenido traído tiene" + Arrays.toString(content));
                int modificatorIndex = Arrays.asList(content).indexOf(newName);
                content[modificatorIndex + 1] = newPuntuation;
                needAppend = false;

                System.out.println(Arrays.toString(content).replaceAll("[\\[\\]\\s]",""));
                newContent = Arrays.toString(content).replaceAll("[\\[\\]\\s]","");
                System.out.println("modifique puntuacion en indice "+(modificatorIndex + 1));
            }else{

                if(Arrays.asList(content).size()!=1){

                    newContent = "," + newName + "," + newPuntuation;
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, needAppend))) {
                System.out.println("el contenido a guardar tiene" + newContent);
                writer.write(newContent.toString());
                writer.flush();
            }
        }catch(IOException e1){
            e1.printStackTrace();
        }
    };


    public String[] readFromFile(String fileName){
        StringBuilder content = new StringBuilder();
        File file = new File(directory, fileName);
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
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
