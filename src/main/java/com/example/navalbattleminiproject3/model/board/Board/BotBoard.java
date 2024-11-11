package com.example.navalbattleminiproject3.model.board.Board;

import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;
import jdk.swing.interop.SwingInterOpUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BotBoard extends BoardAdapter {

    List<List<Integer>> board;

    List<List<Boats>> boardWithBoats;

    List<Integer> allBoatsUsed;

    public BotBoard() {
        board = new ArrayList<>(10);
        boardWithBoats = new ArrayList<>(10);
        allBoatsUsed = new ArrayList<>(10);
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

        for(int i = 9; i>0;i--){
            String[] boatPosition = randomBoatGeneration(allBoatsUsed.get(i));
            System.out.println("Left boat generation");
            for (String position : boatPosition ) {
                System.out.println(position);
                setNumberByIndex(board, i, Integer.parseInt("" + position.charAt(0)), Integer.parseInt("" + position.charAt(1)));
                System.out.println("HI");
                //setObjectByIndex(boardWithBoats, new Boats(i, boatPosition), Integer.parseInt("" + position.charAt(0)), Integer.parseInt("" + position.charAt(1)));
                System.out.println("HI2");
            }

        }

    }

    public List<List<Boats>> getBoardWithBoats() {
        return boardWithBoats;
    }

    public void setBoardWithBoats(List<List<Boats>> boardWithBoats) {
        this.boardWithBoats = boardWithBoats;
    }

    public List<List<Integer>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Integer>> board) {
        this.board = board;
    }

    //    public int RandomBoatSelector() {
//        Random random = new Random();
//        int randomBoatNumber = 0;
//        int randomIndex = 0;
//        randomIndex = random.nextInt(allBoatsUsed.toArray().length);
//        do {
//            randomBoatNumber = (allBoatsUsed.get(randomIndex) != 0 ? allBoatsUsed.get(randomIndex) : 0);
//            if (randomBoatNumber == 0) {
//                randomIndex = (randomIndex == allBoatsUsed.toArray().length - 1 ? 0 : randomIndex + 1);
//            }
//        } while (randomBoatNumber == 0);
//
//        allBoatsUsed.set(randomIndex, 0);
//
//        return randomBoatNumber;
//    }

    public String[] randomBoatGeneration(int boatNumber) {
        Random random = new Random();
        String[] boatPositions = new String[boatNumber];
        int randomCol = 0;
        int randomRow = 0;
        int randomDirection = 0;
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

            System.out.println("Creating boat: Initial row"+ randomRow + " initial col "+ randomCol + " Direction " +randomDirection);
            for(int i=0; i<boatNumber; i++){
                switch (randomDirection) {
                    case 0:
                        if (allowedBoatPosition(randomRow, randomCol + i, randomDirection)) {
                            System.out.println("Boat part putted");
                            needPositionRestart = false;
                            boatPositions[i] = String.valueOf(randomRow) + String.valueOf(randomCol);
                        }else{
                            positionTryCounter++;
                            needPositionRestart = true;
                        }
                        break;


                    case 1:
                        if (allowedBoatPosition(randomRow - i, randomCol, randomDirection)) {
                            System.out.println("Boat part putted");
                            needPositionRestart = false;
                            boatPositions[i] = String.valueOf(randomRow) + String.valueOf(randomCol);
                        }else{
                            positionTryCounter++;
                            needPositionRestart = true;
                        }
                        break;

                    case 2:
                        if (allowedBoatPosition(randomRow, randomCol - i, randomDirection)) {
                            System.out.println("Boat part putted");
                            needPositionRestart = false;
                            boatPositions[i] = String.valueOf(randomRow) + String.valueOf(randomCol);
                        }else{
                            positionTryCounter++;
                            needPositionRestart = true;
                        }
                        break;

                    case 3:
                        if (allowedBoatPosition(randomRow + i, randomCol, randomDirection)) {
                            System.out.println("Boat part putted");
                            needPositionRestart = false;
                            boatPositions[i] = String.valueOf(randomRow) + String.valueOf(randomCol);
                        }else{
                            positionTryCounter++;
                            needPositionRestart = true;
                        }
                        break;
                    default:
                        System.out.println("Invalid direction");
                }
                if(needPositionRestart && positionTryCounter!=4) {
                    System.out.println("Restart boat putting");
                    randomDirection++;
                    i = 0;
                }
            }
            }while(needPositionRestart);
        System.out.println("I almost left");
            return boatPositions;
        }


    public boolean allowedBoatPosition(int row, int column, int direction) {
        System.out.println("Row" +row);
        System.out.println("Column" +column);
        try {
            if (getNumberByIndex(board, row, column) != 0) {
                throw new IllegalArgumentException("Matrix position inaccessible");
            }
        } catch (IllegalArgumentException e1) {
            System.out.println("The try-catch dropped" + e1.getMessage());
            return false;
        } catch (Exception e2){
            System.out.println("The try-catch dropped" + e2.getMessage());
        }
        return true;
    }
}
