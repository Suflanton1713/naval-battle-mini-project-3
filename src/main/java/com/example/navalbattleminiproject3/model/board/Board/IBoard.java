package com.example.navalbattleminiproject3.model.board.Board;


import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;

import java.util.List;


public interface IBoard {


    boolean shootInOtherBoard(BoardAdapter attackedBoard, int row, int column);

    boolean randomShootInOtherBoard(BoardAdapter attackedBoard);

    boolean spawnBoat(int row, int column, int direction, int boatType);

    String[] randomBoatGeneration(int boatNumber);

    default boolean isBoatHitted(List<List<Integer>> hittedBoard, int row, int column) {
        return hittedBoard.get(row).get(column) != 0;
    }

    List<List<Integer>> getBoard();

    List<List<Boats>> getBoardWithBoats();

    default void setNumberByIndex(List<List<Integer>> board, int number, int row, int column) {
        board.get(row).set(column, number);
    }

    default int getNumberByIndex(List<List<Integer>> board, int row, int column) {
        return board.get(row).get(column);
    }

    default <T> void setObjectByIndex(List<List<T>> board, T object, int row, int column) {
        board.get(row).set(column, object);
    }

    default <T> T getObjectByIndex(List<List<T>> board, int row, int column) {
        return board.get(row).get(column);
    }

    default boolean allowedBoatPositionByNumber(List<List<Integer>> board, int row, int column, int direction, int boatType){
        System.out.println("Watching if boat is in allowed position, type of boat " + boatType + " initial Row " + row + " , column " + column + " and direction " + direction);

        for (int i = 0; i < boatType; i++) {
            try {
                if (getNumberByIndex(board, row, column) != 0) {
                    throw new IllegalArgumentException("Matrix position filled");
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
                        System.out.println("Wrong direction");
                        return false;
                }
            } catch (IllegalArgumentException e1) {
                System.out.println("The try-catch dropped" + e1.getMessage());
                return false;
            } catch (Exception e2) {
                System.out.println("The try-catch dropped" + e2.getMessage());
                return false;
            }

        }
        return true;
    }

    default <T> String showBoard(List<List<T>> board) {
        StringBuilder finalMessage = new StringBuilder();
        for (int i = 0; i < board.toArray().length; i++) {
            for (int j = 0; j < board.toArray().length; j++) {
                if ((board.get(i).get(j) instanceof Integer) || board.get(i).get(j) == null) {
                    finalMessage.append(board.get(i).get(j)).append(" ");
                } else {
                    finalMessage.append("objt").append(" ");
                }
            }
            finalMessage.append("\n");
        }

        return finalMessage.toString();
    }
}
