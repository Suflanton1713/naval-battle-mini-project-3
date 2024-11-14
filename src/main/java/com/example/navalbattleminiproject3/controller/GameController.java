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
import java.util.List;


public class GameController {
    private PlayerBoard playerBoard;
    private BotBoard botBoard;
    List<Boats> usedBoats = new ArrayList<>(10);
    Pane shipType1Num1;
    Pane shipType2Num1;
    Pane shipType3Num1;
    Pane shipType4Num1;

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
        shipType1Num1= createShip(1);
        shipType2Num1= createShip(2);
        shipType3Num1= createShip(3);
        shipType4Num1= createShip(4);

        shipsVBox.getChildren().addAll(shipType1Num1, shipType2Num1, shipType3Num1, shipType4Num1);

    }
    public Pane createShip(int type) {
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

        root.setOnMouseClicked(event -> rotateBoat(root));

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
                int boardValue = botBoard.getObjectByIndex(botBoard.getBoard(), i, j);
                if(boardValue == 1 || boardValue == 2 || boardValue == 3 || boardValue == 4){
                    if(!(usedBoats.contains(botBoard.getObjectByIndex(botBoard.getBoardWithBoats(),i,j)))){
                        positionShipWithDirection(botBoard.getObjectByIndex(botBoard.getBoard(),i,j),i,j);
                        usedBoats.add(botBoard.getObjectByIndex(botBoard.getBoardWithBoats(),i,j));
                    }
                }

            }
        }
    }
     public void positionShipWithDirection(int type, int row, int col){
        if (botBoard.getBoardWithBoats().get(row).get(col).getBoatDirection()==0||
                botBoard.getBoardWithBoats().get(row).get(col).getBoatDirection()==180){
            placeBoatInCell(getPaneOfShip(type),row,col,botGridPane);
        }else{
            rotateBoat(getPaneOfShip(type));
            placeBoatInCell(getPaneOfShip(type),row,col,botGridPane);
        }
     }

    public Pane getPaneOfShip(int type) {
        switch (type) {
            case 1:
                return shipType1Num1;
            case 2:
                return shipType2Num1;
            case 3:
                return shipType3Num1;
            case 4:
                return shipType4Num1;
            default:
                System.err.println("Error: Tipo de barco no válido: " + type);
                return null;
        }
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
