package com.example.navalbattleminiproject3.model.board.Board;

import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract class representing a board adapter for the game.
 * Provides default implementations for common board-related operations.
 * Implements {@code IBoard} and is {@code Serializable}.
 * @author Maria Juliana Saavedra, Libardo Alejandro Quintero, Juan David Rincon Lopez
 * @version 1.0
 */
public abstract class BoardAdapter implements IBoard, Serializable {

    /**
     * Performs a shot on another board at the specified location.
     * @param attackedBoard the {@code BoardAdapter} representing the board being attacked.
     * @param row the row index where the shot is targeted.
     * @param column the column index where the shot is targeted.
     * @return {@code true} if the shot was successful, {@code false} otherwise.
     * @version 1.0
     */
    public boolean shootInOtherBoard(BoardAdapter attackedBoard, int row, int column) {
        return false;
    }

    /**
     * Performs a random shot on another board.
     * @param attackedBoard the {@code BoardAdapter} representing the board being attacked.
     * @return an array of integers representing the row and column of the shot.
     * @version 1.0
     */
    public int[] randomShootInOtherBoard(BoardAdapter attackedBoard) {
        return new int[0];
    }

    /**
     * Spawns a boat on the board at the specified location and orientation.
     * @param row the starting row index for the boat.
     * @param column the starting column index for the boat.
     * @param direction the direction of the boat (e.g., 0 for horizontal, 1 for vertical).
     * @param boatType the type of the boat being spawned.
     * @return {@code true} if the boat was successfully spawned, {@code false} otherwise.
     * @version 1.0
     */
    public boolean spawnBoat(int row, int column, int direction, int boatType) {
        return false;
    }

    /**
     * Generates a random arrangement of boats on the board.
     * @param boatNumber the number of boats to generate.
     * @return an array of strings representing the positions and configurations of the boats.
     * @version 1.0
     */
    public String[] randomBoatGeneration(int boatNumber) {
        return new String[0];
    }

    /**
     * Retrieves the current state of the board as a grid of integers.
     * @return a {@code List} of {@code List<Integer>} representing the board's state.
     * @version 1.0
     */
    public List<List<Integer>> getBoard() {
        return null;
    }

    /**
     * Retrieves the current state of the board as a grid of boats.
     * @return a {@code List} of {@code List<Boats>} representing the board with boats.
     * @see Boats
     * @version 1.0
     */
    public List<List<Boats>> getBoardWithBoats() {
        return null;
    }
}
