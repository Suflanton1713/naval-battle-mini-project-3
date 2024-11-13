package com.example.navalbattleminiproject3.model.board.Board;

import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;

import java.io.Serializable;
import java.util.List;

public abstract class BoardAdapter implements IBoard, Serializable {


    public boolean shootInOtherBoard(List<List<Integer>> board, List<List<Boats>> boardWithBoats, int row, int column){return false;}

    public boolean randomShootInOtherBoard(List<List<Integer>> board, List<List<Boats>> boardWithBoats){return false;}

    public boolean spawnBoat(int row, int column, int direction, int boatType){return false;}

    public String[] randomBoatGeneration(int boatNumber){return new String[0];}
}
