package com.example.navalbattleminiproject3.controller;

import com.example.navalbattleminiproject3.model.board.Board.BotBoard;
import com.example.navalbattleminiproject3.model.board.Board.PlayerBoard;
import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;
import com.example.navalbattleminiproject3.view.GameView;
import com.example.navalbattleminiproject3.view.WelcomeView;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class GameController {
    private PlayerBoard playerBoard;
    private BotBoard botBoard;
    private List<Boats> usedBotBoats = new ArrayList<>(10);
    private List<Pane> usedPlayerPanes = new ArrayList<>(10);
    private List<Pane> playerShips = new ArrayList<>(10);
    private List<Pane> botShips = new ArrayList<>(10);
    private List<Integer> temporaryNumberBoats = new ArrayList<>(10);
    int positionBotShipsToSetNull = 0;
    int positionPlayerShipsToSetNull = 0;
    private int rotationCounter = 0;
    private double mouseAnchorX;
    private double mouseAnchorY;


    @FXML
    private VBox shipsVBox;

    @FXML
    private GridPane botGridPane;

    @FXML
    private GridPane playerGridPane;
    @FXML
    private StackPane stackPane1;

    @FXML
    private StackPane stackPane2;

    @FXML
    private StackPane stackPane3;

    @FXML
    private StackPane stackPane4;

    @FXML
    void handleClickExit(ActionEvent event) {
        GameView.deleteInstance();
        WelcomeView.getInstance();
    }


    @FXML
    public void initialize() {
        Collections.addAll(temporaryNumberBoats, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4);
        playerBoard = new PlayerBoard();
        botBoard = new BotBoard();
        createBoardShips();
        for (int i = 1; i < 5; i++) {
            organizePlayerShipsInVBox(i);
        }
        System.out.println("botShips: "+ showArrayBotShips());

    }

    public void organizePlayerShipsInVBox(int type) {
          switch (type) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    if (!(usedPlayerPanes.contains(playerShips.get(i)))) {
                        stackPane1.getChildren().add(playerShips.get(i));
                        break;
                    }
                }
                break;
            case 2:
                for (int i = 4; i < 7; i++) {
                    if (!(usedPlayerPanes.contains(playerShips.get(i)))) {
                        stackPane2.getChildren().add(playerShips.get(i));
                        break;
                    }
                }
                break;
            case 3:
                for (int i = 7; i < 9; i++) {
                    if (!(usedPlayerPanes.contains(playerShips.get(i)))) {
                        stackPane3.getChildren().add(playerShips.get(i));
                        break;
                    }
                }
                break;
            case 4:
                for (int i = 9; i < 10; i++) {
                    if (!(usedPlayerPanes.contains(playerShips.get(i)))) {
                        stackPane4.getChildren().add(playerShips.get(i));
                        break;
                    }
                }
                break;
            default:
                System.err.println("Error: Tipo de barco no válido: " + type);
        }
    }

    public String showArrayBotShips() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < botShips.size(); i++) {
            Pane pane = botShips.get(i);
            result.append("Pane ").append(i + 1).append(": ")
                    .append(pane != null ? pane.toString() : "null").append("\n");
        }
        String output = result.toString();
        return output;
    }

    public void createBoardShips(){
        for(int i = 0; i<10; i++){
            Pane playerShip = createShip(temporaryNumberBoats.get(i),1);
            Pane botShip = createShip(temporaryNumberBoats.get(i),2);
            playerShips.add(playerShip);
            botShips.add(botShip);
        }
    }

    public Pane createShip(int type, int playerOrBot) {
        Pane root = new Pane();
        root.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-style: solid;");

        for (int i = 0; i < type; i++) {
            Rectangle rect = new Rectangle(35, 35);
            rect.setFill(Color.PINK);
            rect.setStroke(Color.RED);

            rect.setX(i * 35);
            rect.setY(0);

            root.getChildren().add(rect);
        }

        root.setPrefSize(type * 35, 35);

        root.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        if(playerOrBot == 1){

            root.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.R) {
                    rotateBoat(root,1);

                }
            });

            root.setOnMouseClicked(event -> {
                root.requestFocus();
            });

            root.focusedProperty().addListener((observable, oldValue, newValue) -> {
                for (Node node : root.getChildren()) {
                    if (node instanceof Rectangle) {
                        Rectangle rect = (Rectangle) node;
                        if (newValue) {
                            rect.setFill(Color.LAWNGREEN);
                        } else {
                            rect.setFill(Color.PINK);
                        }
                    }
                }
            });

            playerGridPane.setOnMouseClicked(event -> {
                for (Pane ship : playerShips) {
                    if (ship != null && ship.isFocused()) {

                        int col = (int) (event.getX() / 35);
                        int row = (int) (event.getY() / 35);

                        if (col < playerGridPane.getColumnCount() && row < playerGridPane.getRowCount()) {

                            if(playerBoard.spawnBoat(row,col,getShipDirection(ship),getPlayerShipType(ship))){
                                placeBoatInCell(ship, row, col, playerGridPane);
                                ship.setDisable(true);
                                usedPlayerPanes.add(ship);
                                organizePlayerShipsInVBox(getPlayerShipType(ship));
                            }

                            System.out.println("-------------AQUIIIII---------------------------------------------------------");
                            System.out.println(playerBoard.showBoard(playerBoard.getBoard()));
                            System.out.println("direction: "+getShipDirection(ship));
                            System.out.println("type: "+getPlayerShipType(ship));
                        }

                        break;
                    }
                }
            });
//            makeDraggable(root);
//            root.setOnMouseClicked(event -> rotateBoat(root));
        }


        return root;
    }

    public int getPlayerShipType(Pane ship){
        for (int i = 0; i < playerShips.size(); i++) {
            if (playerShips.get(i) == ship) {
                return temporaryNumberBoats.get(i);
            }
        }
        return -1;
    }

    public void rotateBoat(Pane boat, int  playerOrBot) {

        boat.setDisable(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(300), boat);
        rotateTransition.setByAngle(90);
        rotateTransition.setOnFinished(event -> {
            boat.setDisable(false);
            boat.requestFocus();
        });

        rotateTransition.play();
    }

    public int getShipDirection(Pane ship) {
        int actualAngle = (int) ((ship.getRotate() % 360 + 360) % 360);

        if (actualAngle > 360) {
            actualAngle = actualAngle % 360;
        }

        return switch (actualAngle) {
            case 0 -> 0;
            case 90 -> 3;
            case 180 -> 2;
            case 270 -> 1;
            default -> -1;
        };

    }

    public void createBotTable(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle rect = new Rectangle(35, 35);
                rect.setFill(Color.LIGHTBLUE);
                rect.setStroke(Color.BLACK);
                botGridPane.add(rect, j, i);
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int boardValue = botBoard.getNumberByIndex(botBoard.getBoard(), i, j);
                if(boardValue == 1 || boardValue == 2 || boardValue == 3 || boardValue == 4){
                    if(!(usedBotBoats.contains(botBoard.getObjectByIndex(botBoard.getBoardWithBoats(),i,j)))){
                        positionBotShipWithDirection(boardValue,i,j);
                        usedBotBoats.add(botBoard.getObjectByIndex(botBoard.getBoardWithBoats(),i,j));
                    }
                }
            }
        }
    }
     public void positionBotShipWithDirection(int type, int row, int col){
        Pane ship = getPaneOfShip(type,2);
        int boardDirection = botBoard.getBoardWithBoats().get(row).get(col).getBoatDirection();
         System.out.println("tipo de barco que se agarró: "+type+" direccion: "+boardDirection
         + " fila: "+row+" column: "+col);
        if (boardDirection==0|| boardDirection==2){
            System.out.println("antes de posicionar: "+ "pane: "+ ship + "fil: "+ row+ "col: "+col);
            placeBoatInCell(ship,row,col,botGridPane);
            botShips.set(positionBotShipsToSetNull, null);
            System.out.println("despues de posicionar: "+ showArrayBotShips());

        }else{
            rotateBoat(ship, 2);

            System.out.println("antes de posicionar: "+ "pane: "+ ship + "fil: "+ row+ "col: "+col);
            placeBoatInCell(ship,row,col,botGridPane);
            botShips.set(positionBotShipsToSetNull, null);
            System.out.println("despues de posicionar: "+ showArrayBotShips());
        }
     }

    public Pane getPaneOfShip(int type, int playerOrBot) {

        int startIndex ;
        int endIndex;

        switch (type) {
            case 1:
                startIndex = 0;
                endIndex = 4;
                break;
            case 2:
                startIndex = 4;
                endIndex = 7;
                break;
            case 3:
                startIndex = 7;
                endIndex = 9;
                break;
            case 4:
                startIndex = 9;
                endIndex = 10;
                break;
            default:
                System.err.println("Error: Tipo de barco no válido: " + type);
                return null;
        }

        List<Pane> shipsList = (playerOrBot == 1) ? playerShips : botShips;

        for (int i = startIndex; i < endIndex; i++) {
            if (shipsList.get(i) != null) {
                Pane ship = shipsList.get(i);
                positionBotShipsToSetNull = i;
                return ship;

            }
        }
        return null;
    }


    public void createPlayerTable(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle rect = new Rectangle(35,35);
                rect.setFill(Color.LIGHTBLUE);
                rect.setStroke(Color.BLACK);
                rect.setOnMouseEntered(event -> handleMouseEnter(rect));
                rect.setOnMouseExited(event -> handleMouseExit(rect));
                int row = i;
                int col = j;
//                rect.setOnMouseClicked(event -> handleCellClick(row, col, playerGridPane));
                playerGridPane.add(rect, j, i);
            }
        }
    }

    public void handleMouseEnter(Rectangle rect) {
        rect.setFill(Color.DARKBLUE);
    }

    public void handleMouseExit(Rectangle rect) {
        rect.setFill(Color.LIGHTBLUE);
    }

    public void placeBoatInCell(Pane ship, int row, int col, GridPane grid) {
        grid.add(ship, col, row);

    }

//    public void makeDraggable(Node node) {
//        // Variables para almacenar las coordenadas iniciales del nodo
//        final double[] initialTranslateX = {0};
//        final double[] initialTranslateY = {0};
//
//        // Evento para registrar las coordenadas iniciales
//        node.setOnMousePressed(mouseEvent -> {
//            mouseAnchorX = mouseEvent.getSceneX();
//            mouseAnchorY = mouseEvent.getSceneY();
//            initialTranslateX[0] = node.getTranslateX();
//            initialTranslateY[0] = node.getTranslateY();
//        });
//
//        // Evento para actualizar la posición del nodo mientras se arrastra
//        node.setOnMouseDragged(mouseEvent -> {
//            double deltaX = mouseEvent.getSceneX() - mouseAnchorX;
//            double deltaY = mouseEvent.getSceneY() - mouseAnchorY;
//
//            node.setTranslateX(initialTranslateX[0] + deltaX);
//            node.setTranslateY(initialTranslateY[0] + deltaY);
//        });
//    }

    public void startPlay() {
        createBotTable();
        createPlayerTable();
    }

}
