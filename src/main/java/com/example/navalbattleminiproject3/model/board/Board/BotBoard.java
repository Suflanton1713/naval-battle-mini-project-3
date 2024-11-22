package com.example.navalbattleminiproject3.model.board.Board;

import com.example.navalbattleminiproject3.model.board.Exception.GameException;
import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;

import java.util.*;

/**
 * Represents the bot's game board, extending {@code BoardAdapter}.
 * Provides methods for boat spawning, shooting at the player's board, and managing game state.
 * @author Maria Juliana Saavedra, Libardo Alejandro Quintero, Juan David Rincon Lopez
 * @version 1.0
 */
public class BotBoard extends BoardAdapter {

    List<List<Integer>> board;
    List<List<Boats>> boardWithBoats;
    List<Integer> allBoatsUsed;
    int actualGameBoatsSunk;
    boolean winner;

    /**
     * Constructor initializes the bot's board and randomly places the boats.
     * Also sets up the list of boats and their initial positions.
     * @version 1.0
     */
    public BotBoard() {
        board = new ArrayList<>(10);
        boardWithBoats = new ArrayList<>(10);
        allBoatsUsed = new ArrayList<>(10);
        actualGameBoatsSunk = 0;
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

        for(int i = 9 ; i>=0 ; i--){
            String[] boatPosition = randomBoatGeneration(allBoatsUsed.get(i));
            Boats actualBoat = new Boats(allBoatsUsed.get(i), getDirectionByPosition(boatPosition), boatPosition);
            for (String position : boatPosition ) {
                setNumberByIndex(board, allBoatsUsed.get(i), Integer.parseInt("" + position.charAt(0)), Integer.parseInt("" + position.charAt(1)));
                setObjectByIndex(boardWithBoats, actualBoat, Integer.parseInt("" + position.charAt(0)), Integer.parseInt("" + position.charAt(1)));
            }
            allBoatsUsed.set(i, 0);
        }
    }

    /**
     * Retrieves the bot's board with boats.
     * @return a list of lists representing the bot's board with boats.
     * @version 1.0
     */
    public List<List<Boats>> getBoardWithBoats() {
        return boardWithBoats;
    }

    /**
     * Sets the number of boats sunk in the current game.
     * @param actualGameBoatsSunk the number of boats sunk.
     * @version 1.0
     */
    public void setActualGameBoatsSunk(int actualGameBoatsSunk) {
        this.actualGameBoatsSunk = actualGameBoatsSunk;
    }

    /**
     * Gets the current number of boats sunk in the game.
     * @return the number of boats sunk.
     * @version 1.0
     */
    public int getActualGameBoatsSunk() {
        return actualGameBoatsSunk;
    }

    /**
     * Sets the bot's board with boats.
     * @param boardWithBoats a list of lists representing the board with boats.
     * @version 1.0
     */
    public void setBoardWithBoats(List<List<Boats>> boardWithBoats) {
        this.boardWithBoats = boardWithBoats;
    }

    /**
     * Retrieves the bot's board as a grid of integers representing the board's state.
     * @return a list of lists representing the bot's board.
     * @version 1.0
     */
    public List<List<Integer>> getBoard() {
        return board;
    }

    /**
     * Sets the bot's board state with the specified grid.
     * @param board a list of lists representing the board's new state.
     * @version 1.0
     */
    public void setBoard(List<List<Integer>> board) {
        this.board = board;
    }

    /**
     * Generates a random boat position on the bot's board.
     * Attempts to place the boat in an empty space with the specified length and direction.
     * @param boatNumber the length of the boat.
     * @return an array of strings representing the positions of the boat on the grid.
     * @version 1.0
     */
    public String[] randomBoatGeneration(int boatNumber) {
        Random random = new Random();
        String[] boatPositions = new String[boatNumber];
        int randomCol;
        int randomRow;
        int randomDirection;
        int positionTryCounter = 0;
        boolean needPositionRestart = false;
        randomCol = random.nextInt(board.toArray().length);
        randomRow = random.nextInt(board.toArray().length);
        randomDirection = random.nextInt(4);

        //randomBoatNumber also represents the boat length
        do{
            positionTryCounter++;
            if(positionTryCounter == 4){
                positionTryCounter = 0;
                randomCol++;
                if((randomCol == (board.toArray().length))) {
                    randomCol = 0;
                    randomRow++;
                    if((randomRow == (board.toArray().length))) {
                        randomRow = 0;
                    }
                }

            }
            if(allowedBoatPositionByNumber(board, randomRow, randomCol, randomDirection, boatNumber)) {
                for (int i = 0; i < boatNumber; i++) {
                    boatPositions[i] = String.valueOf(randomRow) + String.valueOf(randomCol);
                    switch (randomDirection) {
                        case 0:
                            randomCol = randomCol + 1;
                            break;
                        case 1:
                            randomRow = randomRow - 1;
                            break;
                        case 2:
                            randomCol = randomCol - 1;
                            break;
                        case 3:
                            randomRow = randomRow + 1;
                            break;
                    }

                    needPositionRestart = false;
                }
            } else {
                needPositionRestart = true;
            }
            randomDirection++;
            if(randomDirection == 4){
                randomDirection = 0;
            }
        } while(needPositionRestart);

        return boatPositions;
    }

    /**
     * Performs a random shoot on the attacked board.
     * @param attackedBoard the {@code BoardAdapter} representing the board being attacked.
     * @return an array of integers representing the row and column where the shot occurred.
     * @version 1.0
     */
    public int[] randomShootInOtherBoard(BoardAdapter attackedBoard) {
        Random random = new Random();
        int randomCol = 0;
        int randomRow = 0;
        int boxNumber = 0;
        Boats modifiedBoatPart;
        boolean isBoatDestroyed = false;
        boolean allowedPosition = true;
        int[] destroyedPart = new int[2];
        do {
            try {
                randomCol = random.nextInt(attackedBoard.getBoard().toArray().length);
                randomRow = random.nextInt(attackedBoard.getBoard().toArray().length);

                boxNumber = getNumberByIndex(attackedBoard.getBoard(), randomRow, randomCol);
                if (getNumberByIndex(attackedBoard.getBoard(), randomRow, randomCol) < 0) {
                    throw new GameException.BoxAlreadyActivated("Box already shooted");
                }

                allowedPosition = true;

            } catch (GameException.BoxAlreadyActivated e) {
                System.out.println("Wrong box number" + e.getMessage());
                allowedPosition = false;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } while (!allowedPosition);

        if (boxNumber > 0) {
            setNumberByIndex(attackedBoard.getBoard(), ((-1) * boxNumber), randomRow, randomCol);
            modifiedBoatPart = getObjectByIndex(attackedBoard.getBoardWithBoats(), randomRow, randomCol);
            modifiedBoatPart.destroyBoatParts(randomRow, randomCol);
            destroyedPart[0] = randomRow;
            destroyedPart[1] = randomCol;
            return destroyedPart;
        } else {
            setNumberByIndex(attackedBoard.getBoard(), (-6), randomRow, randomCol);
            destroyedPart[0] = randomRow;
            destroyedPart[1] = randomCol;
            return destroyedPart;
        }
    }

    /**
     * Checks if the bot has won the game by sinking all of the player's boats.
     * @return {@code true} if the bot has won, {@code false} otherwise.
     * @version 1.0
     */
    public boolean isWinnner() {
        if (actualGameBoatsSunk >= 10) {
            winner = true;
        }
        return winner;
    }

    /**
     * Increments the number of boats sunk by the bot in the current game.
     * @version 1.0
     */
    public void boatSunk() {
        actualGameBoatsSunk++;
    }

    /**
     * Resets the bot's board and game state, clearing all boats and resetting the sunk boat count.
     * @version 1.0
     */
    public void restartGame() {
        allBoatsUsed.clear();
        Collections.addAll(allBoatsUsed, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4);
        actualGameBoatsSunk = 0;
        winner = false;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board.get(i).set(i, 0);
                boardWithBoats.get(i).set(i, null);
            }
        }
    }
}
