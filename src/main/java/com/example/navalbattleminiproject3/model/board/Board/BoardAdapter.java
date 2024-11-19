package com.example.navalbattleminiproject3.model.board.Board;

import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;

import java.io.Serializable;
import java.util.List;

public abstract class BoardAdapter implements IBoard, Serializable {


    public boolean shootInOtherBoard(BoardAdapter attackedBoard, int row, int column){return false;}

    public int[] randomShootInOtherBoard(BoardAdapter attackedBoard) {
        return new int[0];
    }

    public boolean spawnBoat(int row, int column, int direction, int boatType){return false;}

    public String[] randomBoatGeneration(int boatNumber){return new String[0];}

    public List<List<Integer>> getBoard(){return null;}

    public List<List<Boats>> getBoardWithBoats(){return null;}
}
