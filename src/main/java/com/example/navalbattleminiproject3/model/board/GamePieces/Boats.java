package com.example.navalbattleminiproject3.model.board.GamePieces;

import com.example.navalbattleminiproject3.model.board.Exception.GameException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Boats implements Serializable {

    private int boatType;
    private boolean boatDestroyed;
    private int partsDestroyed;
    private int boatDirection;
    private List<List<Integer>> boatUbication;
    private List<String> boatDamaged;

    public Boats(){
        boatType = 1;
        boatDestroyed = false;
        boatUbication = new ArrayList<>();
        boatDamaged = new ArrayList<>();
        boatDirection = 0;

        for (int i = 0; i < boatType; i++) {
            boatDamaged.add("");
            List<Integer> position = new ArrayList<>(2);
            for (int x = 0; x < 2; x++) {
                position.add(0);
            }
            boatUbication.add(position);
        }
    }

    public Boats(int boatType, int boatDirection, String[] positionList){
        System.out.println("The size of the boat is " + boatType + " the size of the positionList array is" + positionList.length);
        this.boatType = boatType;
        boatDestroyed = false;
        boatUbication = new ArrayList<>();
        boatDamaged = new ArrayList<>();
        this.boatDirection = boatDirection;

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

    public int getPartsDestroyed() {
        return partsDestroyed;
    }


    public void setPartsDestroyed(int partsDestroyed) {
        this.partsDestroyed = partsDestroyed;
    }

    public int getBoatDirection() {
        return boatDirection;
    }

    public void setBoatDirection(int boatDirection) {
        this.boatDirection = boatDirection;
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
            System.out.println("Boat has all his parts damaged  ");
            boatDestroyed = true;

        }
        return boatDestroyed;
    }


    public void destroyBoatParts(int row, int column){

        try{
            String damageUbication = String.valueOf(row) + String.valueOf(column);
            boolean isBoatPartExisting = false;
            System.out.println("Ubicación del daño, " + damageUbication);
            for (int i = 0; i < boatUbication.size(); i++) {
                System.out.println("Imprimiendo partes del bote" + i);
                System.out.println(boatUbication.get(i).get(0) + boatUbication.get(i).get(1));

                    if(Integer.parseInt(String.valueOf(boatUbication.get(i).get(0)) + String.valueOf(boatUbication.get(i).get(1))) == Integer.parseInt(damageUbication)){
                        isBoatPartExisting = true;
                }
            }
            if(!(isBoatPartExisting)){
                throw new GameException.InaccessiblePartInBoat("Trying to destroy a non-existence part in boat");
            }

            if(!boatDamaged.contains(damageUbication)){
                System.out.println("El bote no ha sido destruído completamente.");
                boatDamaged.set(partsDestroyed, damageUbication);
                partsDestroyed++;
                System.out.println(boatDamaged);
                isBoatDestroyed();
            }

        }catch(GameException.InaccessiblePartInBoat e){
            System.out.println(e.getMessage());

        }catch(Exception e){
            System.out.println("Fatal unknown error happened. Can't destroy boat part");
        }


    }
}
