package com.example.navalbattleminiproject3.controller;

import com.example.navalbattleminiproject3.model.board.Board.BotBoard;
import com.example.navalbattleminiproject3.model.board.Board.PlayerBoard;
import com.example.navalbattleminiproject3.model.board.Exception.GameException;
import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;
import com.example.navalbattleminiproject3.view.GameView;
import com.example.navalbattleminiproject3.view.WelcomeView;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameController {
    private PlayerBoard playerBoard;
    private BotBoard botBoard;
    private List<Boats> usedBotBoats = new ArrayList<>(10);
    private List<Pane> usedPlayerPanes = new ArrayList<>(10);
    private List<Pane> playerPaneShips = new ArrayList<>(10);
    private List<Pane> botPaneShips = new ArrayList<>(10);
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
                    if (!(usedPlayerPanes.contains(playerPaneShips.get(i)))) {
                        stackPane1.getChildren().add(playerPaneShips.get(i));
                        break;
                    }
                }
                break;
            case 2:
                for (int i = 4; i < 7; i++) {
                    if (!(usedPlayerPanes.contains(playerPaneShips.get(i)))) {
                        stackPane2.getChildren().add(playerPaneShips.get(i));
                        break;
                    }
                }
                break;
            case 3:
                for (int i = 7; i < 9; i++) {
                    if (!(usedPlayerPanes.contains(playerPaneShips.get(i)))) {
                        stackPane3.getChildren().add(playerPaneShips.get(i));
                        break;
                    }
                }
                break;
            case 4:
                for (int i = 9; i < 10; i++) {
                    if (!(usedPlayerPanes.contains(playerPaneShips.get(i)))) {
                        stackPane4.getChildren().add(playerPaneShips.get(i));
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
        for (int i = 0; i < botPaneShips.size(); i++) {
            Pane pane = botPaneShips.get(i);
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
            playerPaneShips.add(playerShip);
            botPaneShips.add(botShip);
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
                for (Pane ship : playerPaneShips) {

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
        for (int i = 0; i < playerPaneShips.size(); i++) {
            if (playerPaneShips.get(i) == ship) {
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
        Pane ship = getPaneOfShip(type,2);
        int boardDirection = botBoard.getBoardWithBoats().get(row).get(col).getBoatDirection();
         System.out.println("tipo de barco que se agarró: "+type+" direccion: "+boardDirection
         + " fila: "+row+" column: "+col);
        if (boardDirection==0|| boardDirection==2){
            System.out.println("antes de posicionar: "+ "pane: "+ ship + "fil: "+ row+ "col: "+col);
            placeBoatInCell(ship,row,col,botGridPane);
            usedBotPaneShip.add(ship);
            botPaneShips.set(positionBotShipsToSetNull, null);
            System.out.println("despues de posicionar: "+ showArrayBotShips());

        }else{
            rotateBoat(ship, 2);
            System.out.println("antes de posicionar: "+ "pane: "+ ship + "fil: "+ row+ "col: "+col);
            placeBoatInCell(ship,row,col,botGridPane);
            usedBotPaneShip.add(ship);
            botPaneShips.set(positionBotShipsToSetNull, null);
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

        List<Pane> shipsList = (playerOrBot == 1) ? playerPaneShips : botPaneShips;

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
        for (Pane ship : playerPaneShips) {
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
        for (Pane ship : playerPaneShips) {
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

        System.out.println("Botes destruídos por jugador" + playerBoard.getActualGameBoatsSunk());
        System.out.println("El jugador gano" + playerBoard.isWinnner());

        System.out.println("Botes destruídos por bot" + botBoard.getActualGameBoatsSunk());
        System.out.println("El jugador gano" + botBoard.isWinnner());

        if(!playerBoard.isWinnner()){
            disablePlayerBoard(false);

            PauseTransition pause = new PauseTransition(Duration.seconds(2)); // 2 segundos de pausa
            pause.setOnFinished(event -> {
                int[] destroyedPart;
                destroyedPart = botBoard.randomShootInOtherBoard(playerBoard);
                int row = destroyedPart[0];
                int col = destroyedPart[1];
                System.out.println("row: " + row + " col: " + col);
                System.out.println("Después del disparo: ");
                System.out.println(playerBoard.showBoard(playerBoard.getBoard()));
                makeShot(row, col, 2);
                System.out.println("Tablero del jugador\n" + playerBoard.showBoard(playerBoard.getBoard()));

                System.out.println("Botes destruidos por jugador: " + botBoard.getActualGameBoatsSunk());
                System.out.println("El bot ganó: " + botBoard.isWinnner());

                // Verificar si el juego terminó
                if (!botBoard.isWinnner()) {
                    disableBotBoard(false);
                    setDisableShotCells();
                } else {
                    System.out.println("El bot ganó");
                    disableBotBoard(true);
                    disablePlayerBoard(true);
                    System.out.println("Juego terminado");
                }
            });

            pause.play();

        }else{
            System.out.println("El jugador gano");
            disableBotBoard(true);
            disablePlayerBoard(true);
            System.out.println("yepissss");
            return;
        }

        if(!botBoard.isWinnner()){
            disableBotBoard(false);
            setDisableShotCells();
        }else{
            System.out.println("El bot gano");
            disableBotBoard(true);
            disablePlayerBoard(true);
            System.out.println("yepis");
        }


    }

    public void setDisableShotCells(){
        int num;
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                num=botBoard.getNumberByIndex(botBoard.getBoard(),i,j);
                if(num==-6||num==-1||num==-2||num==-3||num==-4){
                    Node node = getNodeFromGridPane(botGridPane, j, i);
                    node.setDisable(true);
                }
            }
        }
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
                Node node = getNodeFromGridPane(botGridPane, j, i);
                node.setDisable(disable);
            }
        }
    }
    public void disablePlayerBoard(boolean disable){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Node node = getNodeFromGridPane(playerGridPane, j, i);
                node.setDisable(disable);
            }
        }
    }

    public void makeShot(int row, int col, int playerOrBot){
        boolean isDestroyed;
        int type;
        try{
            if(playerOrBot == 1){
                System.out.println("Realiza el tiro player---------------------------------------------------------------");
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
            System.out.println("Shoot on boat type, "+type );
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
//                System.out.println("bot board: "+ botBoard.showBoard(botBoard.getBoard()));
//                System.out.println(row +col);
//
//                System.out.println("el que necesitamos bot boats board\n"+botBoard.showBoard(botBoard.getBoardWithBoats()));
//                System.out.println("el que necesitamos player boats board\n"+playerBoard.showBoard(playerBoard.getBoardWithBoats()));
                if(type !=(-6)){

//                    System.out.println("Hasta aqui llega");
                    isDestroyed = botBoard.getObjectByIndex(botBoard.getBoardWithBoats(),row,col).isBoatDestroyed();
//                    System.out.println("paso is destroted" + isDestroyed);
//                    System.out.println("shot in row "+row+" col "+col);
//                    System.out.println(botBoard.showBoard(botBoard.getBoard()));
//                    System.out.println(botBoard.showBoard(botBoard.getBoardWithBoats()));
                    if(isDestroyed){
                        playerBoard.boatSunk();
                        playerBoard.isWinnner();
                        System.out.println("DESTRUIDOOOOOOOO");
                        makeBoatDestroyed(row, col,2);
                        System.out.println("DESTRUIDOOOOOOOO");
                    }else{
                        botGridPane.add(shot, col, row);
                    }

                }else{
                    botGridPane.add(shot, col, row);
                }

            }else{
                if(type!=(-6)){
                    System.out.println("Hasta aqui llega");

                    isDestroyed = playerBoard.getObjectByIndex(playerBoard.getBoardWithBoats(),row,col).isBoatDestroyed();

                    System.out.println("paso is destroted" + isDestroyed);
                    System.out.println("player boats board\n"+playerBoard.showBoard(playerBoard.getBoard()));

                    if(isDestroyed){
                        botBoard.boatSunk();
                        botBoard.isWinnner();
                        makeBoatDestroyed(row, col,1);

                    }else{
                        playerGridPane.add(shot, col, row);
                    }

                }else{
                    playerGridPane.add(shot, col, row);
                }

            }

        }catch(Exception e){
            System.out.println("Some error has ocurred.");
        }

    }

    public void makeBoatDestroyed(int row, int col, int playerOrBot){

        try{
            int boatType;
            List<List<Integer>> boatUbication;
            if (playerOrBot == 1){
                boatType = playerBoard.getNumberByIndex(playerBoard.getBoard(),row,col);
                boatUbication = playerBoard.getObjectByIndex(playerBoard.getBoardWithBoats(),row,col).getBoatUbication();

            }else{
                boatType = botBoard.getNumberByIndex(botBoard.getBoard(),row,col);
                System.out.println("si coge type: "+boatType);
                System.out.println("fila: "+row+" columna: "+col);
                boatUbication = botBoard.getObjectByIndex(botBoard.getBoardWithBoats(),row,col).getBoatUbication();
            }
            int endIndex = switch (boatType) {
                case -1 -> 1;
                case -2 -> 2;
                case -3 -> 3;
                case -4 -> 4;
                default -> 0;
            };

            for(int i=0; i<endIndex; i++){
                for (List<Integer> row2 : boatUbication) {
                    int fila = row2.get(0);
                    int column = row2.get(1);
                    Circle shot = new Circle();
                    shot.setRadius(10);
                    shot.setTranslateX(7.5);
                    shot.setFill(Color.YELLOW);
                    if(playerOrBot==1){
                        playerGridPane.add(shot, column, fila);
                        System.out.println("yellow shot in: fila "+fila+" columna "+column);
                    }else{
                        System.out.println("yellow shot in: fila "+fila+" columna "+column);
                        botGridPane.add(shot, column, fila);
                    }
                }
            }


        }catch(GameException.OutOfBoardAction e){
            System.out.println(e.getMessage());
            System.out.println("yep");
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
