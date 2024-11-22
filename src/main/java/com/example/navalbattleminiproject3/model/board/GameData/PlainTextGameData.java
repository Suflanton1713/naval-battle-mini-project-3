package com.example.navalbattleminiproject3.model.board.GameData;

import java.io.*;
import java.util.Arrays;

/**
 * Class for handling game data operations in plain text files.
 * Extends {@code DataFileHandler} and implements the methods for reading, writing, and rewriting files.
 * Specifically designed to handle player data in plain text format.
 * @author Maria Juliana Saavedra, Libardo Alejandro Quintero, Juan David Rincon Lopez
 * @version 1.0
 */
public class PlainTextGameData extends DataFileHandler {
    private File directory;

    /**
     * Constructor that initializes the directory where game data files are stored.
     * Creates the directory if it does not already exist.
     * @version 1.0
     */
    public PlainTextGameData(){
        directory = new File("src/main/resources/com/example/navalbattleminiproject3/playerData");
        // Creates a directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Rewrites the content of a specified file with the new content.
     * If the file does not exist, it will be created.
     * @param fileName the name of the file to rewrite.
     * @param content the new content to write into the file.
     * @version 1.0
     */
    @Override
    public void rewriteFile(String fileName, String content){
        // Build the full path with fileName
        File file = new File(directory, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes new content to a file. If the content contains an existing player's name,
     * it updates the score. Otherwise, it appends the new player's data to the file.
     * @param fileName the name of the file to write to.
     * @param newContent the new content to write, consisting of the player's name and score.
     * @version 1.0
     */
    @Override
    public void writeToFile(String fileName, String newContent){
        String[] content;
        String newName = (newContent.split(","))[0].trim();
        String newPuntuation = (newContent.split(","))[1];
        boolean needAppend = true;
        System.out.println(newName + newPuntuation);
        // Build the full path with fileName
        File file = new File(directory, fileName);

        try {
            content = readFromFile(fileName);

            if (Arrays.asList(content).contains(newName)) {
                System.out.println("true si contiene nombre repetido en indice" + Arrays.asList(content).indexOf(newName));
                System.out.println("el contenido traído tiene" + Arrays.toString(content));
                int modificatorIndex = Arrays.asList(content).indexOf(newName);
                content[modificatorIndex + 1] = newPuntuation;
                needAppend = false;

                System.out.println(Arrays.toString(content).replaceAll("[\\[\\]\\s]", ""));
                newContent = Arrays.toString(content).replaceAll("[\\[\\]\\s]", "");
                System.out.println("modifique puntuacion en indice " + (modificatorIndex + 1));
            } else {
                if (Arrays.asList(content).size() != 1) {
                    newContent = "," + newName + "," + newPuntuation;
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, needAppend))) {
                System.out.println("el contenido a guardar tiene" + newContent);
                writer.write(newContent.toString());
                writer.flush();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Reads the content of a file and returns it as an array of strings.
     * Each element in the array represents a piece of content separated by commas.
     * @param fileName the name of the file to read.
     * @return an array of strings containing the content of the file.
     * @version 1.0
     */
    @Override
    public String[] readFromFile(String fileName){
        StringBuilder content = new StringBuilder();
        File file = new File(directory, fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line.trim()).append(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString().split(",");
    }
}
