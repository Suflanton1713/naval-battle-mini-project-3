package com.example.navalbattleminiproject3.model.board.GameData;

import com.example.navalbattleminiproject3.model.board.Board.BoardAdapter;
import com.example.navalbattleminiproject3.model.board.Board.BotBoard;
import com.example.navalbattleminiproject3.model.board.Board.PlayerBoard;
import com.example.navalbattleminiproject3.model.board.Exception.GameException;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for handling player data, including loading and saving profiles, scores, and board states.
 * This class interacts with both plain text files and serialized game data files to manage player profiles and matches.
 * @author Maria Juliana Saavedra, Libardo Alejandro Quintero, Juan David Rincon Lopez
 * @version 1.0
 */
public class PlayerDataHandler {
    private List<String> nicknamesData;
    private List<String> pointsData;
    private File directory;
    private PlainTextGameData plainData;
    private SerializableGameData serializableData;

    /**
     * Constructor that initializes the player data handler, reading player profiles and points from a file.
     * If no profiles are found, it throws an exception.
     * @version 1.0
     */
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
                    nicknamesData.add(temporalArray[i].trim());
                } else {
                    pointsData.add(temporalArray[i].trim());
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("An error has occurred. " + e.getMessage());
        }
    }

    /**
     * Returns a list of nicknames of all created profiles.
     * @return a list of player nicknames.
     * @version 1.0
     */
    public List<String> getNicknamesData() {
        return nicknamesData;
    }

    /**
     * Sets the list of nicknames of all created profiles.
     * @param nicknamesData the list of nicknames to set.
     * @version 1.0
     */
    public void setNicknamesData(List<String> nicknamesData) {
        this.nicknamesData = nicknamesData;
    }

    /**
     * Returns a list of points associated with each player profile.
     * @return a list of player points.
     * @version 1.0
     */
    public List<String> getPointsData() {
        return pointsData;
    }

    /**
     * Sets the list of points associated with each player profile.
     * @param pointsData the list of points to set.
     * @version 1.0
     */
    public void setPointsData(List<String> pointsData) {
        this.pointsData = pointsData;
    }

    /**
     * Loads the profile data for the specified profile name. If the profile does not exist, it creates it.
     * @param profileName the name of the profile to load.
     * @return the board data for the profile as an object.
     * @version 1.0
     */
    public Object loadBoardProfile(String profileName) {
        try {
            if (!(isProfileCreated(profileName))) {
                throw new IllegalArgumentException("Profile " + profileName + " is not created");
            }
            return (Object) serializableData.deserializeData(profileName + ".ply");
        } catch (IllegalArgumentException e1) {
            System.out.println("An error has occurred. " + e1.getMessage());
            createProfile(profileName);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    /**
     * Saves the board data for a specific profile.
     * @param profileName the name of the profile to save.
     * @param boardAdapter the board data to save.
     * @return {@code true} if the save was successful, {@code false} otherwise.
     * @version 1.0
     */
    public boolean saveBoardProfile(String profileName, BoardAdapter boardAdapter) {
        try {
            if (!(isProfileCreated(profileName))) {
                throw new IllegalArgumentException("Profile " + profileName + " is not created");
            }
            serializableData.serializeData(profileName + ".ply", boardAdapter);
            return true;
        } catch (IllegalArgumentException e1) {
            System.out.println("Oh it seems like we will have to do it again. " + e1.getMessage());
            createProfile(profileName);
            return saveBoardProfile(profileName, boardAdapter);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    /**
     * Loads the match data for a specific profile, including both player and bot boards.
     * @param profileName the name of the profile to load.
     * @return an array containing the player board and bot board.
     * @version 1.0
     */
    public Object[] loadMatch(String profileName) {
        Object[] returnBoards = new Object[2];
        try {
            if (!(isProfileCreated(profileName))) {
                throw new GameException.CantLoadMatch("Profile " + profileName + " is not created");
            }
            returnBoards[0] = (PlayerBoard) serializableData.deserializeData(profileName + ".ply");
            returnBoards[1] = (BotBoard) serializableData.deserializeData(profileName + ".bot");
            return returnBoards;
        } catch (GameException.CantLoadMatch e1) {
            System.out.println("An error has occurred. " + e1.getMessage());
            createProfile(profileName);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    /**
     * Ends a match by updating the profile with the final score and deleting the match files.
     * @param profileName the name of the profile to update.
     * @param profilePoints the points to update for the profile.
     * @version 1.0
     */
    public void endMatch(String profileName, int profilePoints) {
        updateProfile(profileName, profilePoints);

        try {
            File file = new File(directory, profileName + ".ply");
            File file2 = new File(directory, profileName + ".bot");

            if (!(file.delete() && file2.delete())) {
                throw new GameException.CantDeleteFile("Can't delete serializable when ending match.");
            }

        } catch (GameException.CantDeleteFile e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the match data, including both the player board and the bot board.
     * @param profileName the name of the profile to save.
     * @param playerBoard the player board to save.
     * @param botBoard the bot board to save.
     * @param profilePoints the player's points.
     * @return {@code true} if the save was successful, {@code false} otherwise.
     * @version 1.0
     */
    public boolean saveMatch(String profileName, PlayerBoard playerBoard, BotBoard botBoard, int profilePoints) {
        try {
            if (!(isProfileCreated(profileName))) {
                throw new IllegalArgumentException("Profile " + profileName + " is not created");
            }

            updateProfile(profileName, profilePoints);

            serializableData.serializeData(profileName + ".ply", playerBoard);
            serializableData.serializeData(profileName + ".bot", botBoard);

            return true;

        } catch (IllegalArgumentException e1) {
            System.out.println("Oh it seems like we will have to do it again. " + e1.getMessage());
            createProfile(profileName, profilePoints);
            return saveMatch(profileName, playerBoard, botBoard, profilePoints);
        } catch (Exception e2) {
            e2.printStackTrace();
            System.out.println("A big error has occurred.");
        }
        return false;
    }

    /**
     * Creates a new profile with the specified profile name. If the profile already exists, throws an exception.
     * @param profileName the name of the profile to create.
     * @version 1.0
     */
    public void createProfile(String profileName) {
        try {
            if (nicknamesData.contains(profileName)) {
                throw new IllegalArgumentException("Profile already exists");
            }
            plainData.writeToFile("data_panda.csv", profileName + "," + 0);
            nicknamesData.add(profileName);
            pointsData.add("0");

        } catch (IllegalArgumentException e) {
            createProfile(profileName + "New");
            System.out.println("An error has occurred. " + e.getMessage());
        }
    }

    /**
     * Creates a new profile with the specified profile name and points. If the profile already exists, throws an exception.
     * @param profileName the name of the profile to create.
     * @param profilePoints the points for the new profile.
     * @version 1.0
     */
    public void createProfile(String profileName, int profilePoints) {
        try {
            if (nicknamesData.contains(profileName)) {
                throw new IllegalArgumentException("Profile already exists");
            }
            plainData.writeToFile("data_panda.csv", profileName + "," + 0);
            nicknamesData.add(profileName);
            pointsData.add(String.valueOf(profilePoints));

        } catch (IllegalArgumentException e) {
            createProfile(profileName + "New", profilePoints);
            System.out.println("An error has occurred. " + e.getMessage());
        }
    }

    /**
     * Updates the profile with the new points.
     * @param profileName the name of the profile to update.
     * @param profilePoints the new points for the profile.
     * @version 1.0
     */
    public void updateProfile(String profileName, int profilePoints) {
        try {
            System.out.println("Los perfiles existentes son " + nicknamesData);
            if (!(nicknamesData.contains(profileName))) {
                throw new GameException.profilesDoesNotExist("Profile does not exist");
            }

            plainData.writeToFile("data_panda.csv", profileName + "," + String.valueOf(profilePoints));
            pointsData.set(nicknamesData.indexOf(profileName), String.valueOf(profilePoints));

        } catch (GameException.profilesDoesNotExist e) {
            System.out.println("An error has occurred. " + e.getMessage());
            System.out.println("Creating new profile");
            createProfile(profileName);
        } catch (Exception e1) {
            System.out.println("An error has occurred. " + e1.getMessage());
            System.out.println("Creating new profile");
            createProfile(profileName);
        }
    }

    /**
     * Checks if a profile with the given name already exists.
     * @param profileName the name of the profile to check.
     * @return {@code true} if the profile exists, {@code false} otherwise.
     * @version 1.0
     */
    public boolean isProfileCreated(String profileName) {
        return nicknamesData.contains(profileName);
    }
}
