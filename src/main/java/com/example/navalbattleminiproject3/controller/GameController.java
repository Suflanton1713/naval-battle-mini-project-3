package com.example.navalbattleminiproject3.controller;

import com.example.navalbattleminiproject3.model.board.Board.BotBoard;
import com.example.navalbattleminiproject3.model.board.Board.PlayerBoard;
import com.example.navalbattleminiproject3.view.GameView;
import com.example.navalbattleminiproject3.view.WelcomeView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;


public class GameController {
    private PlayerBoard playerBoard;
    private BotBoard botBoard;
    int shipNum1 = 4;

    @FXML
    private Button ship1;

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

    }

    public void createBotTable(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle rect = new Rectangle(35, 35);
                rect.setFill(Color.LIGHTBLUE);
                rect.setStroke(Color.BLACK);
                botGridPane.add(rect, j, i);
                if (botBoard.getNumberByIndex(botBoard.getBoard(),i,j)!= 0) {
                    placeBoatInCell(i,j,botGridPane);
                }
            }
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
                rect.setOnMouseClicked(event -> handleCellClick(row, col, playerGridPane));
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

    public void handleCellClick(int row, int col, GridPane grid) {
        System.out.println("Clicked on cell: (" + row + ", " + col + ")");
        playerBoard.spawnBoat(row,col,0,2);
        System.out.println(playerBoard.showBoard(playerBoard.getBoard()));
        placeBoatInCell(row, col, grid);
    }
    public void placeBoatInCell(int row, int col, GridPane grid) {

        Ellipse boat = new Ellipse(10,10);
        boat.setFill(Color.GRAY);  // Color del barco
        boat.setStroke(Color.BLACK);  // Borde negro

        grid.add(boat, col, row);
    }


    public void startPlay() {
        createBotTable();
        createPlayerTable();
    }

}
