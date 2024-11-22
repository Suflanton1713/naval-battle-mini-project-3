package com.example.navalbattleminiproject3.model.board.Board;

import com.example.navalbattleminiproject3.model.board.Exception.GameException;
import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;

import java.util.List;

/**
 * Interface representing the operations available on a game board.
 * Provides methods for spawning boats, shooting, and managing the board state.
 * Default implementations for some methods are provided.
 * @author Maria Juliana Saavedra, Libardo Alejandro Quintero, Juan David Rincon Lopez
 * @version 1.0
 */
public interface IBoard {

    /**
     * Shoots at another board at the specified row and column.
     * @param attackedBoard the {@code BoardAdapter} representing the attacked board.
     * @param row the row index of the target cell.
     * @param column the column index of the target cell.
     * @return {@code true} if the shot was successful, {@code false} otherwise.
     * @version 1.0
     */
    boolean shootInOtherBoard(BoardAdapter attackedBoard, int row, int column);

    /**
     * Performs a random shot on another board and returns the coordinates of the shot.
     * @param attackedBoard the {@code BoardAdapter} representing the attacked board.
     * @return an array containing the row and column of the shot.
     * @version 1.0
     */
    int[] randomShootInOtherBoard(BoardAdapter attackedBoard);

    /**
     * Spawns a boat at a specified position with a given direction and type.
     * @param row the row index to spawn the boat.
     * @param column the column index to spawn the boat.
     * @param direction the direction of the boat (0 for horizontal, 1 for vertical).
     * @param boatType the type of boat to spawn.
     * @return {@code true} if the boat was successfully spawned, {@code false} otherwise.
     * @version 1.0
     */
    boolean spawnBoat(int row, int column, int direction, int boatType);

    /**
     * Generates a random boat position on the board.
     * @param boatNumber the number of positions to generate for the boat.
     * @return an array of strings representing the boat's positions.
     * @version 1.0
     */
    String[] randomBoatGeneration(int boatNumber);

    /**
     * Retrieves the current state of the board as a list of lists of integers.
     * @return a {@code List<List<Integer>>} representing the current state of the board.
     * @version 1.0
     */
    List<List<Integer>> getBoard();

    /**
     * Retrieves the current state of the board as a list of lists of {@code Boats}.
     * @return a {@code List<List<Boats>>} representing the board with the boats.
     * @version 1.0
     */
    List<List<Boats>> getBoardWithBoats();

    /**
     * Checks if a boat is hit at the specified position on the board.
     * @param hittedBoard the board to check.
     * @param row the row index of the target cell.
     * @param column the column index of the target cell.
     * @return {@code true} if the boat is hit, {@code false} otherwise.
     * @throws GameException.NoBoardFound if the board is not found.
     * @throws GameException.OutOfBoardPosition if the position is out of board bounds.
     * @version 1.0
     */
    default boolean isBoatHitted(List<List<Integer>> hittedBoard, int row, int column) {
        try {
            accessingBoardComprobation(hittedBoard, row, column);
            return hittedBoard.get(row).get(column) != 0;
        } catch (GameException.NoBoardFound | GameException.OutOfBoardPosition e) {
            System.out.println("Can't say if a boat is Hitted.");
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Can't say if a boat is Hitted.");
            System.out.println("Error: An unexpected error has occurred - ");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Sets a value at a specific index on the board.
     * @param board the board where the value will be set.
     * @param number the number to set.
     * @param row the row index where the number will be set.
     * @param column the column index where the number will be set.
     * @version 1.0
     */
    default void setNumberByIndex(List<List<Integer>> board, int number, int row, int column) {
        try {
            accessingBoardComprobation(board, row, column);
            board.get(row).set(column, number);
        } catch (GameException.NoBoardFound | GameException.OutOfBoardPosition e) {
            System.out.println("Can't set number by index.");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Can't set number by index.");
            System.out.println("Error: An unexpected error has occurred - ");
            e.printStackTrace();
        }
    }

    /**
     * Gets the number at a specific index on the board.
     * @param board the board to get the value from.
     * @param row the row index to get the value from.
     * @param column the column index to get the value from.
     * @return the value at the specified position on the board.
     * @version 1.0
     */
    default int getNumberByIndex(List<List<Integer>> board, int row, int column) {
        try {
            accessingBoardComprobation(board, row, column);
            return board.get(row).get(column);
        } catch (GameException.NoBoardFound | GameException.OutOfBoardPosition e) {
            System.out.println("Can't get number by index. Returning 255.");
            System.out.println(e.getMessage());
            return 255;
        } catch (Exception e) {
            System.out.println("Can't set number by index. Returning 255.");
            System.out.println("Error: An unexpected error has occurred - ");
            e.printStackTrace();
            return 255;
        }
    }

    /**
     * Sets an object at a specific index on the board.
     * @param board the board where the object will be set.
     * @param object the object to set.
     * @param row the row index where the object will be set.
     * @param column the column index where the object will be set.
     * @param <T> the type of the object.
     * @version 1.0
     */
    default <T> void setObjectByIndex(List<List<T>> board, T object, int row, int column) {
        try {
            accessingBoardComprobation(board, row, column);
            board.get(row).set(column, object);
        } catch (GameException.NoBoardFound | GameException.OutOfBoardPosition e) {
            System.out.println("Can't set object by index.");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Can't set object by index.");
            System.out.println("Error: An unexpected error has occurred - ");
            e.printStackTrace();
        }
    }

    /**
     * Gets an object at a specific index on the board.
     * @param board the board to get the object from.
     * @param row the row index to get the object from.
     * @param column the column index to get the object from.
     * @param <T> the type of the object.
     * @return the object at the specified position on the board.
     * @version 1.0
     */
    default <T> T getObjectByIndex(List<List<T>> board, int row, int column) {
        try {
            accessingBoardComprobation(board, row, column);
            return board.get(row).get(column);
        } catch (GameException.NoBoardFound | GameException.OutOfBoardPosition e) {
            System.out.println("Can't get number by index. Returning 255.");
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Can't get number by index. Returning 255.");
            System.out.println("Error: An unexpected error has occurred - ");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Determines the direction of the boat based on its positions.
     * @param boatPosition an array of boat positions represented as strings.
     * @return an integer representing the direction (0: horizontal, 1: vertical).
     * @version 1.0
     */
    default int getDirectionByPosition(String[] boatPosition) {
        try {
            if (boatPosition == null || boatPosition.length == 0 || boatPosition.length > 4) {
                throw new NullPointerException("BoatPosition is null or BoatPosition has some length errors.");
            }

            if (boatPosition.length == 1) {
                return 0;
            }

            if (boatPosition[0].charAt(0) == boatPosition[1].charAt(0)) {
                if (Integer.parseInt("" + boatPosition[0].charAt(1)) < Integer.parseInt("" + boatPosition[1].charAt(1))) {
                    return 0;
                } else {
                    return 2;
                }
            } else {
                if (Integer.parseInt("" + boatPosition[0].charAt(0)) < Integer.parseInt("" + boatPosition[1].charAt(0))) {
                    return 3;
                } else {
                    return 1;
                }
            }

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if a boat can be placed at a specified position on the board.
     * @param board the board to check for available positions.
     * @param row the starting row index for placing the boat.
     * @param column the starting column index for placing the boat.
     * @param direction the direction of the boat (0: horizontal, 1: vertical).
     * @param boatType the size of the boat.
     * @return {@code true} if the boat can be placed at the specified position, {@code false} otherwise.
     * @version 1.0
     */
    default boolean allowedBoatPositionByNumber(List<List<Integer>> board, int row, int column, int direction, int boatType) {
        for (int i = 0; i < boatType; i++) {
            try {
                accessingBoardComprobation(board, row, column);
                if (getNumberByIndex(board, row, column) != 0) {
                    throw new GameException.OccupiedBox("Matrix position filled");
                }

                switch (direction) {
                    case 0:
                        column = column + 1;
                        break;
                    case 1:
                        row = row - 1;
                        break;
                    case 2:
                        column = column - 1;
                        break;
                    case 3:
                        row = row + 1;
                        break;
                    default:
                        return false;
                }
            } catch (GameException.NoBoardFound | GameException.OutOfBoardPosition e) {
                return false;
            } catch (GameException.OccupiedBox e1) {
                return false;
            } catch (Exception e2) {
                return false;
            }
        }
        return true;
    }

    /**
     * Displays the current state of the board.
     * @param board the board to display.
     * @param <T> the type of the elements on the board.
     * @return a string representation of the board.
     * @version 1.0
     */
    default <T> String showBoard(List<List<T>> board) {
        try {
            if (board == null) {
                throw new GameException.NoBoardFound("No board to show.");
            }

            StringBuilder finalMessage = new StringBuilder();
            for (int i = 0; i < board.size(); i++) {
                for (int j = 0; j < board.get(i).size(); j++) {
                    finalMessage.append(board.get(i).get(j) == null ? "objt" : board.get(i).get(j)).append(" ");
                }
                finalMessage.append("\n");
            }

            return finalMessage.toString();

        } catch (GameException.NoBoardFound e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Verifies if the board, row, and column are valid.
     * @param board the board to verify.
     * @param row the row index to verify.
     * @param column the column index to verify.
     * @param <T> the type of the elements on the board.
     * @throws GameException.NoBoardFound if the board is null.
     * @throws GameException.OutOfBoardPosition if the row or column is out of bounds.
     * @version 1.0
     */
    default <T> void accessingBoardComprobation(List<List<T>> board, int row, int column) {
        if (board == null) {
            throw new GameException.NoBoardFound();
        }

        if (row < 0 || row >= board.size()) {
            throw new GameException.OutOfBoardPosition("Row out of board bounds.");
        }

        if (column < 0 || column >= board.get(row).size()) {
            throw new GameException.OutOfBoardPosition("Column out of board bounds.");
        }
    }
}
