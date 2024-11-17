package com.example.navalbattleminiproject3.controller;

import com.example.navalbattleminiproject3.model.board.Board.BotBoard;
import com.example.navalbattleminiproject3.model.board.Board.PlayerBoard;
import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;
import com.example.navalbattleminiproject3.view.GameView;
import com.example.navalbattleminiproject3.view.WelcomeView;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    List<Boats> usedBotBoats = new ArrayList<>(10);
    List<Boats> usedPlayerBoats = new ArrayList<>(10);
    List<Pane> playerShips = new ArrayList<>(10);
    List<Pane> botShips = new ArrayList<>(10);
    int positionBotShipsToSetNull = 0;

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
        playerBoard = new PlayerBoard();
        botBoard = new BotBoard();
        createBoardShips();
        organizePlayerShipsInVBox();
        System.out.println("botShips: "+ showArrayBotShips());

    }

    public void organizePlayerShipsInVBox() {
        for (int i = 0; i < 4; i++) {
            stackPane1.getChildren().add(playerShips.get(i));
        }
        for (int i = 4; i < 7; i++) {
            stackPane2.getChildren().add(playerShips.get(i));
        }
        for (int i = 7; i < 9; i++) {
            stackPane3.getChildren().add(playerShips.get(i));
        }
        for (int i = 9; i < 10; i++) {
            stackPane4.getChildren().add(playerShips.get(i));
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
        List<Integer> temporaryNumberBoats = new ArrayList<>(10);
        Collections.addAll(temporaryNumberBoats, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4);
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
            root.setOnMouseClicked(event -> rotateBoat(root));
        }

        return root;
    }

    private void rotateBoat(Pane boat) {

        boat.setDisable(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(300), boat);
        rotateTransition.setByAngle(90);

        rotateTransition.setOnFinished(event -> boat.setDisable(false));

        rotateTransition.play();
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
            rotateBoat(ship);

            System.out.println("antes de posicionar: "+ "pane: "+ ship + "fil: "+ row+ "col: "+col);
            placeBoatInCell(ship,row,col,botGridPane);
            botShips.set(positionBotShipsToSetNull, null);
            System.out.println("despues de posicionar: "+ showArrayBotShips());
        }
     }

    public Pane getPaneOfShip(int type, int playerOrBot) {

        int startIndex = 0;
        int endIndex = 0;

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
//                shipsList.set(i, null);
//                System.out.println("pane que se va a poner: " + ship+ "de tipo: " + type);
//                System.out.println(showArrayBotShips());
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
        playerGridPane.add(playerShips.get(5), 0, 0);
    }

    public void handleMouseEnter(Rectangle rect) {
        rect.setFill(Color.DARKBLUE);  // Cambiar color al pasar el mouse
    }

    public void handleMouseExit(Rectangle rect) {
        rect.setFill(Color.LIGHTBLUE);  // Restaurar color original
    }

    public void placeBoatInCell(Pane ship, int row, int col, GridPane grid) {
        grid.add(ship, col, row);
//        System.out.println("poniendo barco en row: "+row+" y col: "+col+" :"
//                +botBoard.getBoardWithBoats().get(row).get(col).getBoatDirection());
    }


    public void startPlay() {
        createBotTable();
//        botGridPane.add(botShips.get(9), 5, 5);
//        rotateBoat(botShips.get(9));
//        placeBoatInCell(botShips.get(9), 2, 5, botGridPane);
        createPlayerTable();
    }

}
