package com.example.navalbattleminiproject3.model.board.GamePieces;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Boats extends DrawsAdapter{

    int boatType;
    boolean boatDestroyed;
    int partsDestroyed;
    List<List<Integer>> boatUbication;
    List<String> boatDamaged;

    public Boats(){
        boatType = 1;
        boatDestroyed = false;
        boatUbication = new ArrayList<>();
        boatDamaged = new ArrayList<>();

        for (int i = 0; i < boatType; i++) {
            boatDamaged.add("");
            List<Integer> position = new ArrayList<>(2);
            for (int x = 0; x < 2; x++) {
                position.add(0);
            }
            boatUbication.add(position);
        }
    }

    public Boats(int boatType, String[] positionList){
        System.out.println("The size of the boat is " + boatType + " the size of the positionList array is" + positionList.length);
        this.boatType = boatType;
        boatDestroyed = false;
        boatUbication = new ArrayList<>();
        boatDamaged = new ArrayList<>();

        for (int i = 0; i < boatType; i++) {
            boatDamaged.add("");
            List<Integer> position = new ArrayList<>(2);
            position.add(Integer.valueOf("" + positionList[i].charAt(0)));
            position.add(Integer.valueOf("" + positionList[i].charAt(1)));
            boatUbication.add(position);
        }

    }

    public int getBoatType() {
        return boatType;
    }

    public void setBoatType(int boatType) {
        this.boatType = boatType;
    }

    public List<List<Integer>> getBoatUbication() {
        return boatUbication;
    }

    public void setBoatUbication(List<List<Integer>> boatUbication) {
        this.boatUbication = boatUbication;
    }

    public List<String> getBoatDamaged() {
        System.out.println("Boat damaged "+ boatDamaged);
        return boatDamaged;
    }

    public void setBoatDamaged(List<String> boatDamaged) {
        this.boatDamaged = boatDamaged;
    }

    public void setBoatDestroyed(boolean boatDestroyed) {
        this.boatDestroyed = boatDestroyed;
    }

    public boolean getBoatDestroyed(){
        return boatDestroyed;
    }


    public boolean isBoatDestroyed(){
        System.out.println("Boat damaged at" + boatDamaged);
        if(!(boatDamaged.contains(""))){
            System.out.println("Boat has not damaged parts left");
            boatDestroyed = true;
        }
        return boatDestroyed;
    }


    public void destroyBoatParts(int row, int column){
        String damageUbication = String.valueOf(row) + String.valueOf(column);
        boatDamaged.set(partsDestroyed, damageUbication);
        partsDestroyed++;
        System.out.println(boatDamaged);
        isBoatDestroyed();

    }
}
