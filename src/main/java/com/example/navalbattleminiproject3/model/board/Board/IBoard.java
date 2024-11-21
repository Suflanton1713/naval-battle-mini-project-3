package com.example.navalbattleminiproject3.model.board.Board;


import com.example.navalbattleminiproject3.model.board.Exception.GameException;
import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.List;


public interface IBoard {


    boolean shootInOtherBoard(BoardAdapter attackedBoard, int row, int column);

    int[] randomShootInOtherBoard(BoardAdapter attackedBoard);

    boolean spawnBoat(int row, int column, int direction, int boatType);

    String[] randomBoatGeneration(int boatNumber);

    List<List<Integer>> getBoard();

    List<List<Boats>> getBoardWithBoats();

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

    default <T> T getObjectByIndex(List<List<T>> board, int row, int column) {
        try {

            System.out.println("Antes de la comprobación de getObjectByIndex");

            accessingBoardComprobation(board, row, column);

            System.out.println("Después y return,  " + board.get(row).get(column));

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

    default int getDirectionByPosition(String[] boatPosition){

        try{
            if(boatPosition==null || boatPosition.length==0 || boatPosition.length>4){
                throw new NullPointerException("BoatPosition is null or BoatPosition has some length errors.");
            }

            if(boatPosition.length==1){
                return 0;
            }

            if(boatPosition[0].charAt(0) == boatPosition[1].charAt(0)){
                //Row doesnt change
                if(Integer.parseInt(""+boatPosition[0].charAt(1)) < Integer.parseInt(""+boatPosition[1].charAt(1))){
                    return 0;
                }else{
                    return 2;
                }

            }else{
                //Column doesnt change

                if(Integer.parseInt(""+boatPosition[0].charAt(0)) < Integer.parseInt(""+boatPosition[1].charAt(0))){
                    return 3;
                }else{
                    return 1;
                }

            }

        }catch(NullPointerException e){
            System.out.println(e.getMessage());
            return 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default boolean allowedBoatPositionByNumber(List<List<Integer>> board, int row, int column, int direction, int boatType){
        System.out.println("Watching if boat is in allowed position, type of boat " + boatType + " initial Row " + row + " , column " + column + " and direction " + direction);

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
                        System.out.println("Wrong direction");
                        return false;
                }
            } catch (GameException.NoBoardFound | GameException.OutOfBoardPosition e) {
                System.out.println("Can't access to matrix position.");
                System.out.println(e.getMessage());
                return false;
            } catch (GameException.OccupiedBox e1) {
                System.out.println("Fatal error on setting boat on occupied box" + e1.getMessage());
                return false;
            } catch (Exception e2) {
                System.out.println("Fatal unknown error has been occurred about spawning boat on box. " + e2.getMessage());
                return false;
            }

        }
        return true;
    }

    default <T> String showBoard(List<List<T>> board){
        try{
            if(board == null) {
                throw new GameException.NoBoardFound("No board to show.");
            }

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

            }catch(GameException.NoBoardFound e){
                System.out.println(e.getMessage());
                return e.getMessage();

            }catch (Exception e){
                System.out.println("Cant show board");
                e.printStackTrace();
                return e.getMessage();
        }


    }

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
