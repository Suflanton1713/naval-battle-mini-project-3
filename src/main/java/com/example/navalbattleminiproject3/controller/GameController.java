package com.example.navalbattleminiproject3.controller;

import com.example.navalbattleminiproject3.model.board.Board.BotBoard;
import com.example.navalbattleminiproject3.model.board.Board.PlayerBoard;
import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;
import com.example.navalbattleminiproject3.view.*;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
    private List<Pane> usedBotPaneShip = new ArrayList<>(10);
    int positionBotShipsToSetNull = 0;
    int positionPlayerShipsToSetNull = 0;
    private int rotationCounter = 0;
    private double mouseAnchorX;
    private double mouseAnchorY;
    private boolean isWatchBotBoardOn = false;


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
    private Button startButton;

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

    public String showBotUsedShips() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < usedBotPaneShip.size(); i++) {
            Pane pane = usedBotPaneShip.get(i);
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
        StackPane root = new StackPane();
        root.setMinSize(type * 35, 35);
        root.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-style: solid;");
        for (int i = 0; i < type; i++) {


            if(type == 1){
                StarView star= new StarView();
                Group starRoot = star.getRoot();
                starRoot.setLayoutX(i*35); // Ajustar la posición relativa si es necesario
                starRoot.setLayoutY(0);
                starRoot.setScaleX(0.17);
                starRoot.setScaleY(0.17);

                // Añadir al barco
                root.getChildren().add(starRoot);

            } else if (type == 2) {
                HalconView halcon = new HalconView();
                Group halconRoot = halcon.getRoot();
                halconRoot.setLayoutX(i*35); // Ajustar la posición relativa si es necesario
                halconRoot.setLayoutY(0);

                // Ajustar el tamaño y la posición dentro de la celda

                halconRoot.setScaleX(0.25);
                halconRoot.setScaleY(0.25);

                // Añadir al barco
                root.getChildren().add(halconRoot);

            } else if (type == 3) {
                AlaView ala = new AlaView();
                Group AlaRoot = ala.getRoot();
                AlaRoot.setLayoutX(i*35); // Ajustar la posición relativa si es necesario
                AlaRoot.setLayoutY(0);

                // Ajustar el tamaño y la posición dentro de la celda

                AlaRoot.setScaleX(0.3);
                AlaRoot.setScaleY(0.25);

                // Añadir al barco
                root.getChildren().add(AlaRoot);


            } else if (type == 4) {
                DestructorView destructor = new DestructorView();
                Group destructorRoot = destructor.getRoot();
                destructorRoot.setLayoutX(i*35); // Ajustar la posición relativa si es necesario
                destructorRoot.setLayoutY(0);

                // Ajustar el tamaño y la posición dentro de la celda

                destructorRoot.setScaleX(0.4);
                destructorRoot.setScaleY(0.25);

                // Añadir al barco
                root.getChildren().add(destructorRoot);

            }

            root.setPrefSize(type * 35, 35);
        }



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
                    if (node instanceof Group) {
                        // Cambiar el color del Halcón cuando se enfoca
                        Group halconRoot = (Group) node;
                        for (Node child : halconRoot.getChildren()) {
                            if (child instanceof Circle) {
                                Circle circle = (Circle) child;
                                circle.setFill(newValue ? Color.LAWNGREEN : Color.web("#e7e7e7"));
                            }
                        }
                    }
                }
            });

            playerGridPane.setOnMouseClicked(event -> {
                for (Pane ship : playerShips) {

                    if ( ship.isFocused()) {
                        int col = (int) (event.getX() / 35);
                        int row = (int) (event.getY() / 35);

                        if (col < playerGridPane.getColumnCount() && row < playerGridPane.getRowCount()) {
                            if(playerBoard.spawnBoat(row,col,getShipDirection(ship),getPlayerShipType(ship))){
                                placeBoatInCell(ship, row, col, playerGridPane);
                                ship.setDisable(true);
                                usedPlayerPanes.add(ship);
                                organizePlayerShipsInVBox(getPlayerShipType(ship));
                            }else{
                                hoverPlayerBoard(row,col,getPlayerShipType(ship),getShipDirection(ship),Color.RED);
                            }

                            System.out.println("-------------AQUIIIII---------------------------------------------------------");
                            System.out.println(playerBoard.showBoard(playerBoard.getBoard()));
                            System.out.println("direction: " + getShipDirection(ship));
                            System.out.println("type: " + getPlayerShipType(ship));
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
        int actualAngle = (int) ((boat.getRotate() % 360 + 360) % 360);
        int shipType = getPlayerShipType(boat);
        System.out.println("Estoy a tanto de ángulo "+actualAngle);
        if(shipType == 1 ){
            if (actualAngle == 0){
            boat.setTranslateX(0);
            boat.setTranslateY(0);
        }
        else if (actualAngle == 90){
            boat.setTranslateX(0);
            boat.setTranslateY(0);
        } else if (actualAngle == 180) {
            boat.setTranslateX(0);
            boat.setTranslateY(0);
        }
        else if (actualAngle == 270) {
            boat.setTranslateX(0);
            boat.setTranslateY(0);
        }}

        if(shipType == 2 ){
                if (actualAngle == 0) {
                    boat.setTranslateX(-17);
                    boat.setTranslateY(17);
                } else if (actualAngle == 90) {
                    boat.setTranslateX(-35);
                    boat.setTranslateY(0);
                } else if (actualAngle == 180) {
                    boat.setTranslateX(-17);
                    boat.setTranslateY(-17);
                } else if (actualAngle == 270) {
                    boat.setTranslateX(0);
                    boat.setTranslateY(0);
                }


                if (actualAngle == 0) {
                    boat.setTranslateX(-17);
                    boat.setTranslateY(17);
                }
        }
        if(shipType == 3 ){
            if (actualAngle == 0){
                boat.setTranslateX(-35);
                boat.setTranslateY(35);
            }
            else if (actualAngle == 90){
                boat.setTranslateX(0);
                boat.setTranslateY(0);
            } else if (actualAngle == 180) {
                boat.setTranslateX(0);
                boat.setTranslateY(0);
            }
            else if (actualAngle == 270) {
                boat.setTranslateX(0);
                boat.setTranslateY(0);
            }}

        if(shipType == 4 ){
            if (actualAngle == 0){
                boat.setTranslateX(-17);
                boat.setTranslateY(17);
            }
            else if (actualAngle == 90){
                boat.setTranslateX(-35);
                boat.setTranslateY(0);
            } else if (actualAngle == 180) {
                boat.setTranslateX(-17);
                boat.setTranslateY(-17);
            }
            else if (actualAngle == 270) {
                boat.setTranslateX(0);
                boat.setTranslateY(0);
            }}

        boat.setDisable(true);
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(300), boat);
        rotateTransition.setByAngle(90);
        rotateTransition.setOnFinished(event -> {
            boat.setDisable(false);
            boat.requestFocus();
            if(playerOrBot == 1){
                clearHoverEffect();
            }
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
                int shipType = botBoard.getNumberByIndex(botBoard.getBoard(), i, j);
                if(shipType == 1 || shipType == 2 || shipType == 3 || shipType == 4){
                    if(!(usedBotBoats.contains(botBoard.getObjectByIndex(botBoard.getBoardWithBoats(),i,j)))){
                        positionBotShipWithDirection(shipType,i,j);
                        usedBotBoats.add(botBoard.getObjectByIndex(botBoard.getBoardWithBoats(),i,j));
                    }
                }
            }
        }
    }
     public void positionBotShipWithDirection(int type, int row, int col){

             // Obtener el barco del bot de manera similar a como se obtienen los barcos del jugador
             Pane ship = getPaneOfShip(type, 2);
             if (ship == null) return;

             // Obtener la dirección del barco desde el tablero del bot
             int boardDirection = botBoard.getBoardWithBoats().get(row).get(col).getBoatDirection();

             // Rotar el barco si es necesario antes de colocarlo
             if (boardDirection == 1 || boardDirection == 3) {
                 rotateBoat(ship, 2);
             }

             // Colocar el barco de manera consistente en el grid
             placeBoatInCell(ship, row, col, botGridPane);
             usedBotPaneShip.add(ship);

             // Marcar este barco como usado en la lista de barcos del bot
             botShips.set(positionBotShipsToSetNull, null);

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
                int row = i;
                int col = j;
                rect.setOnMouseEntered(event -> handleMouseEnter(rect, row, col));
                rect.setOnMouseExited(event -> handleMouseExit(rect, row, col));

                playerGridPane.add(rect, j, i);
            }
        }
    }

    public void clearHoverEffect() {
        for (Node node : playerGridPane.getChildren()) {
            if (node instanceof Rectangle rect) {
                rect.setFill(Color.LIGHTBLUE); // Restaurar color inicial
            }
        }
    }

    public void handleMouseEnter(Rectangle rect, int row, int col) {
        for (Pane ship : playerShips) {
            if (ship != null && ship.isFocused()) {
                int shipType = getPlayerShipType(ship);
                int shipDirection = getShipDirection(ship);
                hoverPlayerBoard(row, col, shipType, shipDirection, Color.DARKBLUE);
            }
        }
        rect.setFill(Color.DARKBLUE);
    }

    public void hoverPlayerBoard(int row, int col, int type, int direction, Color highlightColor) {
        int num = switch (type) {
            case 2 -> 1;
            case 3 -> 2;
            case 4 -> 3;
            default -> 0;
        };

        for (int i = 0; i <= num; i++) {
            int directionCol = col, DirectionRow = row;
            switch (direction) {
                case 0 -> directionCol = col + i; // Derecha
                case 1 -> DirectionRow = row - i; // Arriba
                case 2 -> directionCol = col - i; // Izquierda
                case 3 -> DirectionRow = row + i; // Abajo
            }

            // Verifica si el nodo está dentro de los límites
            if (directionCol >= 0 && directionCol < 10 && DirectionRow >= 0 && DirectionRow < 10) {
                Node node = getNodeFromGridPane(playerGridPane, directionCol, DirectionRow);
                if (node instanceof Rectangle) {
                    ((Rectangle) node).setFill(highlightColor);
                }
            }
        }
    }

    public Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            Integer nodeCol = GridPane.getColumnIndex(node);
            Integer nodeRow = GridPane.getRowIndex(node);

            if (nodeCol != null && nodeRow != null && nodeCol == col && nodeRow == row) {
                return node;
            }
        }
        return null;
    }

    public void handleMouseExit(Rectangle rect, int row, int col) {
        for (Pane ship : playerShips) {
            if (ship != null && ship.isFocused()) {
                int shipType = getPlayerShipType(ship);
                int shipDirection = getShipDirection(ship);
                hoverPlayerBoard(row, col, shipType, shipDirection, Color.LIGHTBLUE);
            }
        }
        rect.setFill(Color.LIGHTBLUE);
    }

    public void placeBoatInCell(Pane ship, int row, int col, GridPane grid) {
        grid.add(ship, col, row);
    }

    @FXML
    void handleClickStart(ActionEvent event) {
        if(usedPlayerPanes.size()==10) {
            startButton.setDisable(true);
            prepareBotBoardForShot();
        }

    }
    public void changeTurnToBot(){
        disablePlayerBoard(false);
        for (Pane ship : usedPlayerPanes) {
            ship.setMouseTransparent(true);
        }
        int[] destroyedPart;
        destroyedPart = botBoard.randomShootInOtherBoard(playerBoard);
        int row = destroyedPart[0];
        int col = destroyedPart[1];
        System.out.println("row: "+row+" col: "+col);
        System.out.println("despues de shot: ");
        System.out.println(playerBoard.showBoard(playerBoard.getBoard()));
        makeShot(row,col, 2);

        disableBotBoard(false);
    }

    public void prepareBotBoardForShot(){
        for (Pane ship : usedBotPaneShip) {
            ship.setMouseTransparent(true);
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Node node = getNodeFromGridPane(botGridPane, i, j);
                if ((node instanceof Rectangle)) {
                    int row = i, col = j;
                    node.setOnMouseEntered(event -> handleMouseEnter((Rectangle) node, row, col));
                    node.setOnMouseExited(event -> handleMouseExit((Rectangle) node, row, col));
                    node.setOnMouseClicked(event -> {
                        makeShot(col,row, 1);
                        disableBotBoard(true);
                        changeTurnToBot();
                    });
                }

            }
        }
    }

    public void disableBotBoard(boolean disable){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Node node = getNodeFromGridPane(botGridPane, i, j);
                node.setDisable(disable);
            }
        }
    }
    public void disablePlayerBoard(boolean disable){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Node node = getNodeFromGridPane(playerGridPane, i, j);
                node.setDisable(disable);
            }
        }
    }

    public void makeShot(int row, int col, int playerOrBot){
        int type = 0;
        if(playerOrBot == 1){
            playerBoard.shootInOtherBoard(botBoard,row,col);
            System.out.println(botBoard.showBoard(botBoard.getBoard()));
            type = botBoard.getNumberByIndex(botBoard.getBoard(),row,col);
        }else{
            type = playerBoard.getNumberByIndex(playerBoard.getBoard(),row,col);
            System.out.println("row: "+row+" col: "+col);
            System.out.println("type: " + type);
        }

        Circle shot = new Circle();
        shot.setRadius(10);
        shot.setTranslateX(7.5);
        switch (type) {
            case -6:
                shot.setFill(Color.CHOCOLATE);
                break;
            case -1:
                shot.setFill(Color.GREEN);
                break;
            case -2:
                shot.setFill(Color.PURPLE);
                break;
            case -3:
                shot.setFill(Color.DARKRED);
                break;
            case -4:
                shot.setFill(Color.ROYALBLUE);
                break;
        }
        if (playerOrBot==1){
            botGridPane.add(shot, col, row);
            System.out.println("bot board: "+ botBoard.showBoard(botBoard.getBoard()));
        }else{
            playerGridPane.add(shot, col, row);
            System.out.println("player board: "+ playerBoard.showBoard(botBoard.getBoard()));
        }
    }

    @FXML
    public void handleWatchBotBoard(ActionEvent event) {
        isWatchBotBoardOn = !isWatchBotBoardOn;

        for (Pane ship : usedBotPaneShip) {
            if(isWatchBotBoardOn){
                ship.setVisible(false);
            }else{
                ship.setVisible(true);
            }
        }
    }
    public void startPlay() {
        createBotTable();
        createPlayerTable();
    }
}
