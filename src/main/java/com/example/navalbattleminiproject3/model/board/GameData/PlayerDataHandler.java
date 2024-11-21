package com.example.navalbattleminiproject3.model.board.GameData;

import com.example.navalbattleminiproject3.model.board.Board.BoardAdapter;
import com.example.navalbattleminiproject3.model.board.Board.BotBoard;
import com.example.navalbattleminiproject3.model.board.Board.PlayerBoard;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class PlayerDataHandler {
    private List<String> nicknamesData;
    private List<String> pointsData;
    private File directory;
    private PlainTextGameData plainData;
    private SerializableGameData serializableData;

    public PlayerDataHandler() {
        try {
            directory = new File("src/main/resources/com/example/navalbattleminiproject3/playerData");
            String[] temporalArray;
            plainData = new PlainTextGameData();
            serializableData = new SerializableGameData();
            temporalArray = plainData.readFromFile("data_panda.csv");
            if (temporalArray == null || temporalArray.length == 0) {
                throw new IllegalArgumentException("No loadable profiles found");
            }
            nicknamesData = new LinkedList<>();
            pointsData = new LinkedList<>();
            for (int i = 0; i < temporalArray.length; i++) {
                if (i % 2 == 0) {
                    nicknamesData.add(temporalArray[i]);
                } else {
                    pointsData.add(temporalArray[i]);
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("An error has been ocurred. " + e.getMessage());

        }
    }

    public List<String> getNicknamesData() {
        return nicknamesData;
    }

    public void setNicknamesData(List<String> nicknamesData) {
        this.nicknamesData = nicknamesData;
    }

    public List<String> getPointsData() {
        return pointsData;
    }

    public void setPointsData(List<String> pointsData) {
        this.pointsData = pointsData;
    }

    public Object loadBoardProfile(String profileName) {
        try {

            if(!(isProfileCreated(profileName))) {
                throw new IllegalArgumentException("Profile " + profileName + " is not created");
            }

            return (Object) serializableData.deserializeData(profileName + ".ply");

        } catch (IllegalArgumentException e1){
            System.out.println("An error has been ocurred. " + e1.getMessage());
            createProfile(profileName);

        } catch (Exception e2) {
            e2.printStackTrace();
        }

        return null;
    }

    public boolean saveBoardProfile(String profileName, BoardAdapter boardAdapter) {
        try{

            if(!(isProfileCreated(profileName))) {
                throw new IllegalArgumentException("Profile " + profileName + " is not created");
            }



            serializableData.serializeData(profileName + ".ply", boardAdapter);

            return true;

        } catch (IllegalArgumentException e1){
            System.out.println("Oh it seems like we will have to do it again. " + e1.getMessage());
            createProfile(profileName);
            return saveBoardProfile(profileName, boardAdapter);

        } catch (Exception e2) {
            e2.printStackTrace();
            System.out.println("A big error has been ocurred.");
        }
        return false;
    }

    public Object loadMatch(String profileName) {
        Object[] returnBoards = new Object[2];
        try {

            if(!(isProfileCreated(profileName))) {
                throw new IllegalArgumentException("Profile " + profileName + " is not created");
            }

            returnBoards[1] = (Object) serializableData.deserializeData(profileName + ".ply");
            returnBoards[0] = (Object) serializableData.deserializeData(profileName + ".bot");

            return returnBoards;

        } catch (IllegalArgumentException e1){
            System.out.println("An error has been ocurred. " + e1.getMessage());
            createProfile(profileName);

        } catch (Exception e2) {
            e2.printStackTrace();
        }

        return null;
    }

    public boolean saveMatch(String profileName, PlayerBoard playerBoard, BotBoard botBoard, int profilePoints) {
        try{

            if(!(isProfileCreated(profileName))) {
                throw new IllegalArgumentException("Profile " + profileName + " is not created");
            }

            updateProfile(profileName, profilePoints);

            serializableData.serializeData(profileName + ".ply", playerBoard);
            serializableData.serializeData(profileName + ".bot", botBoard);

            return true;

        } catch (IllegalArgumentException e1){
            System.out.println("Oh it seems like we will have to do it again. " + e1.getMessage());
            createProfile(profileName, profilePoints);
            return saveMatch(profileName, playerBoard, botBoard, profilePoints);

        } catch (Exception e2) {
            e2.printStackTrace();
            System.out.println("A big error has been ocurred.");
        }
        return false;
    }

    public void createProfile(String profileName) {
        try {
            if(nicknamesData.contains(profileName)) {
                throw new IllegalArgumentException("Profile already exists");
            }
            plainData.writeToFile("data_panda.csv", profileName + "," + 0);
            nicknamesData.add(profileName);
            pointsData.add("0");

        }catch(IllegalArgumentException e) {
            createProfile(profileName + "New");
            System.out.println("An error has been ocurred. " + e.getMessage());
        }


    }

    public void createProfile(String profileName, int profilePoints) {
        try {
            if(nicknamesData.contains(profileName)) {
                throw new IllegalArgumentException("Profile already exists");
            }
            plainData.writeToFile("data_panda.csv", profileName + "," + 0);
            nicknamesData.add(profileName);
            pointsData.add(String.valueOf(profilePoints));

        }catch(IllegalArgumentException e) {
            createProfile(profileName + "New", profilePoints);
            System.out.println("An error has been ocurred. " + e.getMessage());
        }


    }

    public void updateProfile(String profileName, int profilePoints){
        try {
            if(!(nicknamesData.contains(profileName))){
                throw new IllegalArgumentException("Profile does not exist");
            }

            plainData.writeToFile("data_panda.csv", profileName + "," + String.valueOf(profilePoints));
            pointsData.set(nicknamesData.indexOf(profileName), String.valueOf(profilePoints));

        }catch(IllegalArgumentException e) {
            System.out.println("An error has been ocurred. " + e.getMessage());
            System.out.println("Creating new profile");
            createProfile(profileName);
        }

    }

    public boolean isProfileCreated(String profileName){
        return nicknamesData.contains(profileName);
    }








}
