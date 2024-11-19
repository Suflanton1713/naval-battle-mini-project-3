package com.example.navalbattleminiproject3.model.board.Board;

import com.example.navalbattleminiproject3.model.board.Exception.GameException;
import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;
import jdk.swing.interop.SwingInterOpUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BotBoard extends BoardAdapter {

    List<List<Integer>> board;

    List<List<Boats>> boardWithBoats;

    List<Integer> allBoatsUsed;

    int actualGameBoatsSunk;

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

            Boats actualBoat = new Boats(allBoatsUsed.get(i),getDirectionByPosition(boatPosition), boatPosition);
            System.out.println("Left boat generation");
            for (String position : boatPosition ) {
                System.out.println("Boat position" + position);
                setNumberByIndex(board, allBoatsUsed.get(i), Integer.parseInt("" + position.charAt(0)), Integer.parseInt("" + position.charAt(1)));
                System.out.println("HI");
                setObjectByIndex(boardWithBoats, actualBoat, Integer.parseInt("" + position.charAt(0)), Integer.parseInt("" + position.charAt(1)));
                System.out.println(showBoard(board));
            }
            allBoatsUsed.set(i,0);

        }

        System.out.println("Los botes usados son " + allBoatsUsed);

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

    public String[] randomBoatGeneration(int boatNumber) {
        System.out.println("Generating " + boatNumber+ " boat type");
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
            System.out.println(positionTryCounter + " try direction");
            if(positionTryCounter == 4){
                System.out.println("All tries of direction have been done");
                positionTryCounter = 0;
                randomCol++;
                System.out.println("Adding 1 to column "+ randomCol);
                if((randomCol == (board.toArray().length))) {
                    randomCol = 0;
                    randomRow++;
                    System.out.println("Column is overIndex adding 1 to row and resetting column" + randomRow);
                    if((randomRow == (board.toArray().length))) {
                        randomRow = 0;
                        System.out.println("Row is overIndex resetting row" + randomRow + randomCol);
                    }
                }

            }

            System.out.println("Creating boat: Initial row"+ randomRow + " initial col "+ randomCol + " Direction " +randomDirection);
            if(allowedBoatPositionByNumber(board,randomRow, randomCol, randomDirection, boatNumber)) {

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
                        default:
                            System.out.println("Wrong direction");

                    }

                    needPositionRestart = false;
                }
                System.out.println(Arrays.toString(boatPositions));
            }else{
                needPositionRestart = true;
            }

            randomDirection++;
            if(randomDirection==4){
                randomDirection=0;
            }

            }while(needPositionRestart);
        System.out.println("I almost left");
            return boatPositions;
        }

    public int[] randomShootInOtherBoard(BoardAdapter attackedBoard){
        Random random = new Random();
        int randomCol = 0;
        int randomRow = 0;
        int boxNumber = 0;
        Boats modifiedBoatPart;
        boolean isBoatDestroyed = false;
        boolean allowedPosition = true;
        int[] destroyedPart = new int[2];
        do{
            try{
            randomCol = random.nextInt(attackedBoard.getBoard().toArray().length);
            randomRow = random.nextInt(attackedBoard.getBoard().toArray().length);


                boxNumber = getNumberByIndex(attackedBoard.getBoard(), randomRow, randomCol);
                if(getNumberByIndex(attackedBoard.getBoard(),randomRow,randomCol)<0){
                    throw new GameException.BoxAlreadyActivated("Box already shooted");
                }

                allowedPosition = true;

            }catch(GameException.BoxAlreadyActivated e){
                System.out.println("Wrong box number" + e.getMessage());
                allowedPosition = false;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }while(!allowedPosition);


        //Higher than 0 coz all the negative numbers represent already shooted boxes
        if(boxNumber > 0){
            setNumberByIndex(attackedBoard.getBoard(),((-1)*boxNumber), randomRow, randomCol);
            modifiedBoatPart = getObjectByIndex(attackedBoard.getBoardWithBoats(), randomRow, randomCol);
            modifiedBoatPart.destroyBoatParts(randomRow, randomCol);
            isBoatDestroyed = modifiedBoatPart.getBoatDestroyed();
            destroyedPart[0] = randomRow;
            destroyedPart[1] = randomCol;
            return destroyedPart;
        }else{
            System.out.println("Shoot on water");
            setNumberByIndex(attackedBoard.getBoard(),(-6), randomRow, randomCol);
            destroyedPart[0] = randomRow;
            destroyedPart[1] = randomCol;
            return destroyedPart;
        }
    }

    public boolean isWinnner(){
        return (actualGameBoatsSunk == 10);
    }
    public void boatSunk(){
        actualGameBoatsSunk++;
    }

    public void restartGame(){
        allBoatsUsed.clear();
        Collections.addAll(allBoatsUsed,1,1,1,1,2,2,2,3,3,4);
        System.out.println(allBoatsUsed);
        actualGameBoatsSunk = 0;

        for(int i = 0; i<10;i++){
            for(int j = 0; j<10;j++){
                board.get(i).set(i,0);
                boardWithBoats.get(i).set(i,null);
            }
        }
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


}
