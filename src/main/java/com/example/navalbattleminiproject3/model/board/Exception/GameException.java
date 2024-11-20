package com.example.navalbattleminiproject3.model.board.Exception;

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

    public static class BoxAlreadyActivated extends Exception {
        public BoxAlreadyActivated() {
            super();
        }

        public BoxAlreadyActivated(String mensaje) {
            super(mensaje);
        }

        public BoxAlreadyActivated(String message, Throwable cause) {
            super(message, cause);
        }

        public BoxAlreadyActivated(Throwable cause) {
            super(cause);
        }
    }


    public static class boatAlreadyUsed extends Exception {
        public boatAlreadyUsed() {
            super();
        }

        public boatAlreadyUsed(String mensaje) {
            super(mensaje);
        }

        public boatAlreadyUsed(String message, Throwable cause) {
            super(message, cause);
        }

        public boatAlreadyUsed(Throwable cause) {
            super(cause);
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



    public static class CantSaveProfile extends Exception {
        public CantSaveProfile() {
            super("Fatal error occurred on saving profile. ");
        }

        public CantSaveProfile(String message) {
            super("Fatal error occurred on saving profile. "+message);
        }

        public CantSaveProfile(String message, Throwable cause) {
            super("Fatal error occurred on saving profile." + message, cause);
        }

        public CantSaveProfile(Throwable cause) {
            super(cause);
        }
    }

    public static class CantLoadProfile extends Exception {
        public CantLoadProfile() {
            super("Fatal error occurred on loading profile. ");
        }

        public CantLoadProfile(String message) {
            super("Fatal error occurred on loading profile. " + message);
        }

        public CantLoadProfile(String message, Throwable cause) {
            super("Fatal error occurred on loading profile. " + message, cause);
        }

        public CantLoadProfile(Throwable cause) {
            super(cause);
        }
    }


}

