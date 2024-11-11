package com.example.navalbattleminiproject3.model.board.Board;

public abstract class BoardAdapter implements IBoard {


    public int destroyedBoatPart(int row, int column){return 0;}

    public int spawnBoat(int row, int column, int direction){return 0;}

    public boolean allowedBoatPosition(int row, int column, int direction){return false;}

    public int[] randomBoatGeneration(){return new int[0];}
}
