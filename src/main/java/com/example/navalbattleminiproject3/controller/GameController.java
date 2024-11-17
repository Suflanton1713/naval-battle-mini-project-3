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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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

    @FXML
    private VBox shipsVBox;

    @FXML
    private GridPane botGridPane;

    @FXML
    private GridPane playerGridPane;

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
        for (int i = 0; i < 10; i++) {
            shipsVBox.getChildren().add(playerShips.get(i));
        }

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
                int boardValue = botBoard.getNumberByIndex(botBoard.getBoard(), i, j);
                if(boardValue == 1 || boardValue == 2 || boardValue == 3 || boardValue == 4){
                    if(!(usedBotBoats.contains(botBoard.getObjectByIndex(botBoard.getBoardWithBoats(),i,j)))){
                        positionBotShipWithDirection(botBoard.getNumberByIndex(botBoard.getBoard(),i,j),i,j);
                        usedBotBoats.add(botBoard.getObjectByIndex(botBoard.getBoardWithBoats(),i,j));
                    }
                }

            }
        }
    }
     public void positionBotShipWithDirection(int type, int row, int col){
        Pane ship = getPaneOfShip(type,2);
        if (botBoard.getBoardWithBoats().get(row).get(col).getBoatDirection()==0||
                botBoard.getBoardWithBoats().get(row).get(col).getBoatDirection()==180){
            placeBoatInCell(ship,row,col,botGridPane);
        }else{
            rotateBoat(ship);
            placeBoatInCell(ship,row,col,botGridPane);
        }
     }

    public Pane getPaneOfShip(int type,int playerOrBot) {
        switch (type) {
            case 1:
                if(playerOrBot == 1){
                    for(int i=0; i<4;i++){
                        if(playerShips.get(i) != null){
                            Pane ship = playerShips.get(i);
                            playerShips.set(i, null);
                            return ship;
                        }
                    }
                }else{
                    for(int i=0; i<4;i++){
                        if(botShips.get(i) != null){
                            Pane ship = botShips.get(i);
                            botShips.set(i, null);
                            return ship;
                        }
                    }
                }
                break;
            case 2:
                if(playerOrBot == 1){
                    for(int i=4; i<7;i++){
                        if(playerShips.get(i) != null){
                            Pane ship = playerShips.get(i);
                            playerShips.set(i, null);
                            return ship;
                        }
                    }
                }else{
                    for(int i=4; i<7;i++){
                        if(botShips.get(i) != null){
                            Pane ship = botShips.get(i);
                            botShips.set(i, null);
                            return ship;
                        }
                    }
                }
                break;
            case 3:
                if(playerOrBot == 1){
                    for(int i=7; i<9;i++){
                        if(playerShips.get(i) != null){
                            Pane ship = playerShips.get(i);
                            playerShips.set(i, null);
                            return ship;
                        }
                    }
                }else{
                    for(int i=7; i<9;i++){
                        if(botShips.get(i) != null){
                            Pane ship = botShips.get(i);
                            botShips.set(i, null);
                            return ship;
                        }
                    }
                }
                break;
            case 4:
                if(playerOrBot == 1){
                    if(playerShips.get(9) != null){
                        Pane ship = playerShips.get(9);
                        playerShips.set(9, null);
                        return ship;
                    }
                } else{
                    if(botShips.get(9) != null){
                        Pane ship = botShips.get(9);
                        botShips.set(9, null);
                        return ship;
                    }
                    break;
                }
            default:
                System.err.println("Error: Tipo de barco no válido: " + type);
                return null;
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

//    public void handleCellClick(int row, int col, GridPane grid) {
//        System.out.println("Clicked on cell: (" + row + ", " + col + ")");
//        playerBoard.spawnBoat(row,col,0,2);
//        System.out.println(playerBoard.showBoard(playerBoard.getBoard()));
//        placeBoatInCell(row, col, grid);
//    }
    public void placeBoatInCell(Pane shipType, int row, int col, GridPane grid) {
        grid.add(shipType, col, row);
    }


    public void startPlay() {
        createBotTable();
        createPlayerTable();
    }

}
