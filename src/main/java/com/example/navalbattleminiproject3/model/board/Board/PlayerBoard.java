package com.example.navalbattleminiproject3.model.board.Board;

import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerBoard extends BoardAdapter{

    List<List<Integer>> board;

    List<List<Boats>> boardWithBoats;

    List<Integer> allBoatsUsed;

    int boatsSinked;

    public PlayerBoard(){
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

    }

    public List<List<Integer>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Integer>> board) {
        this.board = board;
    }

    public List<List<Boats>> getBoardWithBoats() {
        return boardWithBoats;
    }

    public void setBoardWithBoats(List<List<Boats>> boardWithBoats) {
        this.boardWithBoats = boardWithBoats;
    }

    public List<Integer> getAllBoatsUsed() {
        return allBoatsUsed;
    }

    public void setAllBoatsUsed(List<Integer> allBoatsUsed) {
        this.allBoatsUsed = allBoatsUsed;
    }

    @Override
    public boolean spawnBoat(int row, int column, int direction, int boatType) {
        String[] boatPositions = new String[boatType];
        try{
            if(!(allowedBoatPositionByNumber(board, row, column, direction, boatType)) || !(allBoatsUsed.contains(boatType))){
                throw new IllegalArgumentException("Cannot spawn boat by that position or not available boat ");
            }
            for (int i = 0; i < boatType; i++) {
                boatPositions[i] = String.valueOf(row) + String.valueOf(column);
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

                }


            }

                Boats actualBoat = new Boats(boatType, direction, boatPositions);
                System.out.println("Left boat generation");
                for (String position : boatPositions ) {
                    System.out.println("Boat position" + position);
                    setNumberByIndex(board, boatType, Integer.parseInt("" + position.charAt(0)), Integer.parseInt("" + position.charAt(1)));
                    System.out.println("HI");
                    System.out.println("Actual boat "+ actualBoat);
                    setObjectByIndex(boardWithBoats, actualBoat, Integer.parseInt("" + position.charAt(0)), Integer.parseInt("" + position.charAt(1)));
                    System.out.println(showBoard(board));
                }

            allBoatsUsed.set(allBoatsUsed.indexOf(boatType),0);

        }catch(IllegalArgumentException e){
            System.out.println("Cannot spawn boat in this position" + e.getMessage());

        }

        return true;
    }

    public boolean shootInOtherBoard(List<List<Integer>> oBoard, List<List<Boats>> oBoardWithBoats,int row, int column){
        int boxNumber;
        Boats modifiedBoatPart;
        boolean isBoatDestroyed = false;

        try{
            boxNumber = getNumberByIndex(oBoard, row, column);
            if(getNumberByIndex(oBoard,row,column)<0){
                throw new IllegalArgumentException("Box already shooted");
            }

        }catch(IndexOutOfBoundsException  | IllegalArgumentException e){
            System.out.println("Wrong box number" + e.getMessage());
            return false;
        }


        //Higher than 0 coz all the negative numbers represent already shooted boxes
        if(boxNumber > 0){
            setNumberByIndex(oBoard,((-1)*boxNumber), row, column);
            modifiedBoatPart = getObjectByIndex(oBoardWithBoats, row, column);
            modifiedBoatPart.destroyBoatParts(row,column);
            isBoatDestroyed = modifiedBoatPart.getBoatDestroyed();
            return isBoatDestroyed;
        }else{
            System.out.println("Shoot on water");
            setNumberByIndex(oBoard,(-6), row, column);
            return false;
        }
    }
}
