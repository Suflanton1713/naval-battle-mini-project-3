package com.example.navalbattleminiproject3.model.board.GamePieces;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Boats extends DrawsAdapter{

    int boatType;
    List<List<Integer>> boatUbication;
    LinkedList<Integer> boatDamaged;

    public Boats(){
        boatType = 1;
        boatUbication = new ArrayList<>();
        boatDamaged = new LinkedList<>();

        for (int i = 0; i < boatType; i++) {
            List<Integer> position = new ArrayList<>(2);
            for (int x = 0; x < 2; x++) {
                position.add(0);
            }
            boatUbication.add(position);
        }
    }

    public Boats(int boatType, String[] positionList){
        this.boatType = boatType;
        boatUbication = new ArrayList<>();
        boatDamaged = new LinkedList<>();

        for (int i = 0; i < boatType; i++) {
            List<Integer> position = new ArrayList<>(2);
            position.add(Integer.valueOf("" + positionList[i].charAt(0)));
            position.add(Integer.valueOf("" + positionList[i].charAt(1)));
            boatUbication.add(position);
        }

    }


}
