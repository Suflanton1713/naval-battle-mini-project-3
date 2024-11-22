package com.example.navalbattleminiproject3.model.board.GamePieces;

import com.example.navalbattleminiproject3.model.board.Exception.GameException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a boat in the game, including its type, location, and damaged parts.
 * Provides methods to manage the boat's condition, including damage and destruction.
 * This class is used to manage boat objects in the game.
 * @author Maria Juliana Saavedra, Libardo Alejandro Quintero, Juan David Rincon Lopez
 * @version 1.0
 */
public class Boats implements Serializable {

    private int boatType;
    private boolean boatDestroyed;
    private int partsDestroyed;
    private int boatDirection;
    private List<List<Integer>> boatUbication;
    private List<String> boatDamaged;

    /**
     * Default constructor that initializes a boat of type 1 with no damage and no direction.
     * It also initializes the boat's location and damage tracking lists.
     * @version 1.0
     */
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

    /**
     * Constructor that initializes a boat with a specific type, direction, and location based on provided positions.
     * @param boatType the type of the boat (e.g., 1, 2, 3, etc.)
     * @param boatDirection the direction of the boat (0: right, 1: up, 2: left, 3: down)
     * @param positionList an array of strings representing the positions of the boat parts.
     * @version 1.0
     */
    public Boats(int boatType, int boatDirection, String[] positionList){

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

    /**
     * Gets the type of the boat.
     * @return the boat type.
     * @version 1.0
     */
    public int getBoatType() {
        return boatType;
    }

    /**
     * Sets the type of the boat.
     * @param boatType the boat type to set.
     * @version 1.0
     */
    public void setBoatType(int boatType) {
        this.boatType = boatType;
    }

    /**
     * Gets the location of the boat as a list of coordinates.
     * @return the boat's location.
     * @version 1.0
     */
    public List<List<Integer>> getBoatUbication() {
        return boatUbication;
    }

    /**
     * Sets the location of the boat.
     * @param boatUbication the new location of the boat.
     * @version 1.0
     */
    public void setBoatUbication(List<List<Integer>> boatUbication) {
        this.boatUbication = boatUbication;
    }

    /**
     * Gets the list of damaged parts of the boat.
     * @return a list of damaged parts.
     * @version 1.0
     */
    public List<String> getBoatDamaged() {
        return boatDamaged;
    }

    /**
     * Gets the number of parts of the boat that have been destroyed.
     * @return the number of destroyed parts.
     * @version 1.0
     */
    public int getPartsDestroyed() {
        return partsDestroyed;
    }

    /**
     * Sets the number of destroyed parts.
     * @param partsDestroyed the number of parts destroyed.
     * @version 1.0
     */
    public void setPartsDestroyed(int partsDestroyed) {
        this.partsDestroyed = partsDestroyed;
    }

    /**
     * Gets the direction of the boat.
     * @return the boat's direction.
     * @version 1.0
     */
    public int getBoatDirection() {
        return boatDirection;
    }

    /**
     * Sets the direction of the boat.
     * @param boatDirection the boat's direction.
     * @version 1.0
     */
    public void setBoatDirection(int boatDirection) {
        this.boatDirection = boatDirection;
    }

    /**
     * Sets the list of damaged parts of the boat.
     * @param boatDamaged the new list of damaged parts.
     * @version 1.0
     */
    public void setBoatDamaged(List<String> boatDamaged) {
        this.boatDamaged = boatDamaged;
    }

    /**
     * Sets whether the boat is destroyed.
     * @param boatDestroyed the destroyed status of the boat.
     * @version 1.0
     */
    public void setBoatDestroyed(boolean boatDestroyed) {
        this.boatDestroyed = boatDestroyed;
    }

    /**
     * Gets whether the boat is destroyed.
     * @return true if the boat is destroyed, false otherwise.
     * @version 1.0
     */
    public boolean getBoatDestroyed(){
        return boatDestroyed;
    }

    /**
     * Checks if the boat is destroyed by verifying if all its parts are damaged.
     * @return true if the boat is destroyed, false otherwise.
     * @version 1.0
     */
    public boolean isBoatDestroyed(){
        if (!(boatDamaged.contains(""))){
            System.out.println("Boat has all its parts damaged");
            boatDestroyed = true;
        }
        return boatDestroyed;
    }

    /**
     * Damages a specific part of the boat at the given row and column.
     * If the part does not exist, throws an exception.
     * @param row the row of the part to damage.
     * @param column the column of the part to damage.
     * @version 1.0
     */
    public void destroyBoatParts(int row, int column){
        try {
            String damageUbication = String.valueOf(row) + String.valueOf(column);
            boolean isBoatPartExisting = false;
            for (int i = 0; i < boatUbication.size(); i++) {
                if(Integer.parseInt(String.valueOf(boatUbication.get(i).get(0)) + String.valueOf(boatUbication.get(i).get(1))) == Integer.parseInt(damageUbication)){
                    isBoatPartExisting = true;
                }
            }
            if (!(isBoatPartExisting)) {
                throw new GameException.InaccessiblePartInBoat("Trying to destroy a non-existent part in boat");
            }

            if (!boatDamaged.contains(damageUbication)) {
                boatDamaged.set(partsDestroyed, damageUbication);
                partsDestroyed++;
                isBoatDestroyed();
            }

        } catch (GameException.InaccessiblePartInBoat e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Fatal unknown error happened. Can't destroy boat part");
        }
    }
}
