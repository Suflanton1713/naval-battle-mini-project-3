package com.example.navalbattleminiproject3.model.board.Board;


import java.util.List;


public interface IBoard{



    int destroyedBoatPart(int row, int column);

    int spawnBoat(int row, int column, int direction);

    boolean allowedBoatPosition(int row, int column, int direction);

    String[] randomBoatGeneration(int boatNumber);

    default boolean isBoatHitted(List<List<Integer>> hittedBoard,int row, int column){
        return hittedBoard.get(row).get(column)!=0;
    }

    default int shootInBox(List<List<Integer>> hittedBoard, int row, int column){
            switch(getNumberByIndex(hittedBoard, row, column)){
                case 1:
                    System.out.println("Hitted miniboat");
                    setNumberByIndex(hittedBoard, row, column, -1);
                    return -1;

                case 2:
                    System.out.println("Hitted no so miniboat");
                    setNumberByIndex(hittedBoard, row, column, -2);
                    return -2;

                case 3:
                    System.out.println("Hitted medium boat");
                    setNumberByIndex(hittedBoard, row, column, -3);
                    return -3;

                case 4:
                    System.out.println("Hitted biggy boat");
                    setNumberByIndex(hittedBoard, row, column, -4);
                    return -4;

                default:
                    System.out.println("Dayum didnt hit no boat");
                    setNumberByIndex(hittedBoard, row, column, 10);
                    return 10;

            }
    }


    default void setNumberByIndex(List<List<Integer>> board, int number, int row, int column) {
        board.get(column).set(row, number);
    }

    default int getNumberByIndex(List<List<Integer>> board, int row, int column){
        return board.get(column).get(row);
    }

    default <T> void setObjectByIndex(List<List<T>> board, T object, int row, int column) {
        board.get(row).set(column, object);
    }

    default <T> T getObjectByIndex(List<List<T>> board, int row, int column){
        return board.get(row).get(column);
    }

    default <T>  String showBoard(List<List<T>> board) {
        StringBuilder finalMessage = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                finalMessage.append(board.get(j).get(i)).append(" ");
            }
            finalMessage.append("\n");
        }
        return finalMessage.toString();
    }
}
