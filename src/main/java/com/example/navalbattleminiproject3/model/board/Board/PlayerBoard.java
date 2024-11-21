package com.example.navalbattleminiproject3.model.board.Board;

import com.example.navalbattleminiproject3.model.board.Exception.GameException;
import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the player's game board, extending {@code BoardAdapter}.
 * Provides methods for boat spawning, shooting at the bot's board, and managing game state.
 * @author Maria Juliana Saavedra, Libardo Alejandro Quintero, Juan David Rincon Lopez
 * @version 1.0
 */
public class PlayerBoard extends BoardAdapter {

    private List<List<Integer>> board;
    private List<List<Boats>> boardWithBoats;
    private List<Integer> allBoatsUsed;
    private String nickname;
    private int boatsSunkEver;
    private int actualGameBoatsSunk;
    private boolean winner;

    /**
     * Default constructor for the PlayerBoard.
     * Initializes the board, boat list, and player data.
     * Sets up the initial board state and available boats.
     * @version 1.0
     */
    public PlayerBoard(){
        board = new ArrayList<>(10);
        boardWithBoats = new ArrayList<>(10);
        allBoatsUsed = new ArrayList<>(10);
        boatsSunkEver = 0;
        actualGameBoatsSunk = 0;
        nickname = "Player";
        winner = false;
        for (int i = 0; i < 10; i++) {
            List<Integer> row = new ArrayList<>(10);
            List<Boats> rowBoats = new ArrayList<>(10);
            for (int x = 0; x < 10; x++) {
                row.add(0);
                rowBoats.add(null);
            }
            board.add(row);
            boardWithBoats.add(rowBoats);
        }
        Collections.addAll(allBoatsUsed, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4);
    }

    /**
     * Constructor that allows setting a custom nickname for the player.
     * @param nickname the nickname of the player.
     * @version 1.0
     */
    public PlayerBoard(String nickname){
        board = new ArrayList<>(10);
        boardWithBoats = new ArrayList<>(10);
        allBoatsUsed = new ArrayList<>(10);
        boatsSunkEver = 0;
        actualGameBoatsSunk = 0;
        this.nickname = nickname;
        winner = false;
        for (int i = 0; i < 10; i++) {
            List<Integer> row = new ArrayList<>(10);
            List<Boats> rowBoats = new ArrayList<>(10);
            for (int x = 0; x < 10; x++) {
                row.add(0);
                rowBoats.add(null);
            }
            board.add(row);
            boardWithBoats.add(rowBoats);
        }
        Collections.addAll(allBoatsUsed, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4);
    }

    /**
     * Gets the player's nickname.
     * @return the nickname of the player.
     * @version 1.0
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets the player's nickname.
     * @param nickname the nickname to set for the player.
     * @version 1.0
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Gets the board represented by a list of lists of integers.
     * @return a {@code List<List<Integer>>} representing the board's state.
     * @version 1.0
     */
    public List<List<Integer>> getBoard() {
        return board;
    }

    /**
     * Sets the board with a new state represented by a list of lists of integers.
     * @param board the new state of the board.
     * @version 1.0
     */
    public void setBoard(List<List<Integer>> board) {
        this.board = board;
    }

    /**
     * Gets the board with boats represented by a list of lists of {@code Boats}.
     * @return a {@code List<List<Boats>>} representing the board with boats.
     * @version 1.0
     */
    public List<List<Boats>> getBoardWithBoats() {
        return boardWithBoats;
    }

    /**
     * Sets the board with boats with a new state represented by a list of lists of {@code Boats}.
     * @param boardWithBoats the new state of the board with boats.
     * @version 1.0
     */
    public void setBoardWithBoats(List<List<Boats>> boardWithBoats) {
        this.boardWithBoats = boardWithBoats;
    }

    /**
     * Gets the list of all boats used by the player.
     * @return a {@code List<Integer>} representing the types of boats used.
     * @version 1.0
     */
    public List<Integer> getAllBoatsUsed() {
        return allBoatsUsed;
    }

    /**
     * Sets the list of all boats used by the player.
     * @param allBoatsUsed the list of boat types to set.
     * @version 1.0
     */
    public void setAllBoatsUsed(List<Integer> allBoatsUsed) {
        this.allBoatsUsed = allBoatsUsed;
    }

    /**
     * Gets the number of boats sunk by the player in all games.
     * @return the total number of boats sunk.
     * @version 1.0
     */
    public int getBoatsSunkEver() {
        return boatsSunkEver;
    }

    /**
     * Sets the number of boats sunk by the player in all games.
     * @param boatsSunkEver the number of boats sunk to set.
     * @version 1.0
     */
    public void setBoatsSunkEver(int boatsSunkEver) {
        this.boatsSunkEver = boatsSunkEver;
    }

    /**
     * Gets the number of boats sunk in the current game.
     * @return the number of boats sunk in the current game.
     * @version 1.0
     */
    public int getActualGameBoatsSunk() {
        return actualGameBoatsSunk;
    }

    /**
     * Sets the number of boats sunk in the current game.
     * @param actualGameBoatsSunk the number of boats sunk to set.
     * @version 1.0
     */
    public void setActualGameBoatsSunk(int actualGameBoatsSunk) {
        this.actualGameBoatsSunk = actualGameBoatsSunk;
    }

    /**
     * Checks if the player has won the game by sinking all boats.
     * @return {@code true} if the player has won, {@code false} otherwise.
     * @version 1.0
     */
    public boolean isWinnner(){
        if(actualGameBoatsSunk >= 10){
            winner = true;
        }
        return winner;
    }

    /**
     * Increments the number of boats sunk by the player in the current game.
     * Also increments the total number of boats sunk by the player.
     * @version 1.0
     */
    public void boatSunk(){
        actualGameBoatsSunk++;
        boatsSunkEver++;
    }

    /**
     * Resets the player's game state, clearing all boats and resetting counters.
     * @version 1.0
     */
    public void restartGame(){
        allBoatsUsed.clear();
        Collections.addAll(allBoatsUsed, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4);
        actualGameBoatsSunk = 0;
        winner = false;

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                board.get(i).set(i, 0);
                boardWithBoats.get(i).set(i, null);
            }
        }
    }

    /**
     * Spawns a boat on the board at the specified location with the given direction.
     * @param row the starting row index for the boat.
     * @param column the starting column index for the boat.
     * @param direction the direction of the boat (0 for horizontal, 1 for vertical).
     * @param boatType the type of the boat (size).
     * @return {@code true} if the boat was successfully spawned, {@code false} otherwise.
     * @version 1.0
     */
    @Override
    public boolean spawnBoat(int row, int column, int direction, int boatType) {
        String[] boatPositions = new String[boatType];
        try {
            if (!allowedBoatPositionByNumber(board, row, column, direction, boatType)) {
                throw new GameException.OutOfBoardAction("Cannot spawn boat by that position.");
            }

            if (!allBoatsUsed.contains(boatType)) {
                throw new GameException.NoBoardFound("Not available boat");
            }

            for (int i = 0; i < boatType; i++) {
                boatPositions[i] = String.valueOf(row) + String.valueOf(column);
                switch (direction) {
                    case 0: column = column + 1; break;
                    case 1: row = row - 1; break;
                    case 2: column = column - 1; break;
                    case 3: row = row + 1; break;
                }
            }

            Boats actualBoat = new Boats(boatType, direction, boatPositions);
            for (String position : boatPositions) {
                setNumberByIndex(board, boatType, Integer.parseInt("" + position.charAt(0)), Integer.parseInt("" + position.charAt(1)));
                setObjectByIndex(boardWithBoats, actualBoat, Integer.parseInt("" + position.charAt(0)), Integer.parseInt("" + position.charAt(1)));
            }

            allBoatsUsed.set(allBoatsUsed.indexOf(boatType), 0);
        } catch (GameException.OutOfBoardAction e) {
            System.out.println("Cannot spawn boat in this position: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Fatal error occurred while spawning the boat: " + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Shoots at a specified location on another board.
     * @param attackedBoard the {@code BoardAdapter} representing the board being attacked.
     * @param row the row index of the target cell.
     * @param column the column index of the target cell.
     * @return {@code true} if the shot hits and sinks a boat, {@code false} otherwise.
     * @version 1.0
     */
    public boolean shootInOtherBoard(BoardAdapter attackedBoard, int row, int column){
        int boxNumber;
        Boats modifiedBoatPart;
        boolean isBoatDestroyed = false;

        try {
            boxNumber = getNumberByIndex(attackedBoard.getBoard(), row, column);
            if (getNumberByIndex(attackedBoard.getBoard(), row, column) < 0) {
                throw new IllegalArgumentException("Box already shot");
            }
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Wrong box number: " + e.getMessage());
            return false;
        }

        // If the number is greater than 0, it means a boat is present in that position
        if (boxNumber > 0) {
            setNumberByIndex(attackedBoard.getBoard(), (-1) * boxNumber, row, column);
            modifiedBoatPart = getObjectByIndex(attackedBoard.getBoardWithBoats(), row, column);
            modifiedBoatPart.destroyBoatParts(row, column);
            isBoatDestroyed = modifiedBoatPart.getBoatDestroyed();
            return isBoatDestroyed;
        } else {
            setNumberByIndex(attackedBoard.getBoard(), (-6), row, column);
            return false;
        }
    }
}
