package com.example.navalbattleminiproject3.model.board.Exception;

import java.io.IOException;

public class GameException extends Exception {

    public GameException() {
        super();
    }

    public GameException(String message) {
        super(message);
    }

    public GameException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameException(Throwable cause) {
        super(cause);
    }

    public static class OutOfBoardAction extends IndexOutOfBoundsException {
        public OutOfBoardAction() {
            super("You are making an action out of the board");
        }

        public OutOfBoardAction(String message) {
            super("You are making an action out of the board" + message);
        }
    }

    public static class OccupiedBox extends Exception {
        public OccupiedBox() {
            super();
        }

        public OccupiedBox(String mensaje) {
            super(mensaje);
        }

        public OccupiedBox(String message, Throwable cause) {
            super(message, cause);
        }

        public OccupiedBox(Throwable cause) {
            super(cause);
        }
    }

    public static class BoxAlreadyActivated extends IndexOutOfBoundsException {
        public BoxAlreadyActivated() {
            super();
        }

        public BoxAlreadyActivated(String mensaje) {
            super(mensaje);
        }

    }


    public static class boatAlreadyUsed extends NullPointerException {
        public boatAlreadyUsed() {
            super();
        }

        public boatAlreadyUsed(String mensaje) {
            super(mensaje);
        }
    }

    public static class InaccessiblePartInBoat extends IndexOutOfBoundsException {
        public InaccessiblePartInBoat() {
            super();
        }

        public InaccessiblePartInBoat(String message) {
            super(message);
        }

    }


    public static class OutOfBoardPosition extends IndexOutOfBoundsException {
        public OutOfBoardPosition() {
            super("Fatal error occurred. You tried to access a box out of the board. ");
        }

        public OutOfBoardPosition(String message) {
            super("Fatal error occurred. You tried to access a box out of the board. " + message + OutOfBoardPosition.class.getSimpleName());
        }
    }

    public static class NoBoardFound extends NullPointerException {
        public NoBoardFound() {
            super("Fatal error occurred about board accessing.");
        }

        public NoBoardFound(String message) {
            super("Fatal error occurred about board accessing." + message + NoBoardFound.class.getSimpleName());
        }

    }



    public static class CantSaveProfile extends IOException {
        public CantSaveProfile() {
            super("Fatal error occurred on saving profile. ");
        }

        public CantSaveProfile(String message) {
            super("Fatal error occurred on saving profile. "+message);
        }
    }

    public static class profilesDoesNotExist extends NullPointerException {
        public profilesDoesNotExist() {
            super("Fatal error occurred on searching profile. ");
        }

        public profilesDoesNotExist(String message) {
            super("Fatal error occurred on searching profile. "+message);
        }
    }

    public static class CantLoadProfile extends IOException {
        public CantLoadProfile() {
            super("Fatal error occurred on loading profile. ");
        }

        public CantLoadProfile(String message) {
            super("Fatal error occurred on loading profile. " + message);
        }
    }

    public static class CantDeleteFile extends IOException {
        public CantDeleteFile() {
            super("Fatal error when deleting serializable file. ");
        }

        public CantDeleteFile(String message) {
            super("Fatal error when deleting serializable file. " + message);
        }
    }

    public static class CantLoadMatch extends NullPointerException {
        public CantLoadMatch() {
            super("Fatal error when loading match. ");
        }

        public CantLoadMatch(String message) {
            super("Fatal error when loading match. " + message);
        }
    }



}

