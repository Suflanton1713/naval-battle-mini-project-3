package com.example.navalbattleminiproject3.controller;
import com.example.navalbattleminiproject3.model.board.Board.BotBoard;
import com.example.navalbattleminiproject3.model.board.Board.PlayerBoard;
import com.example.navalbattleminiproject3.model.board.Exception.GameException;
import com.example.navalbattleminiproject3.model.board.GameData.PlayerDataHandler;
import com.example.navalbattleminiproject3.model.board.GamePieces.Boats;
import com.example.navalbattleminiproject3.view.*;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private PlayerDataHandler playerDataHandler;
    private List<Boats> usedBotBoats = new ArrayList<>(10);
    private List<Boats> usedPlayerBoatsForLoad = new ArrayList<>(10);
    private List<Pane> usedPlayerPanes = new ArrayList<>(10);
    private List<StackPane> playerPaneShips = new ArrayList<>(10);
    private List<StackPane> botPaneShips = new ArrayList<>(10);
    private List<Integer> temporaryNumberBoats = new ArrayList<>(10);
    private List<Pane> usedBotPaneShip = new ArrayList<>(10);
    int positionBotShipsToSetNull = 0;
    int positionPlayerShipsToSetNull = 0;
    private int rotationCounter = 0;
    private double mouseAnchorX;
    private double mouseAnchorY;
    private boolean isWatchBotBoardOn = true;
    private int missingPlayerShips = 10;
    private boolean isLoadingMatch = false;
    private boolean matchEnded = false;
    private boolean matchStarted = false;
    private int profilePointsLoaded;


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
    private ImageView imgPlayer;

    @FXML
    private ImageView imgBot;

    @FXML
    private ImageView imgBotCloud;

    @FXML
    private ImageView imgPlayerCloud;

    @FXML
    private Label labelBotThink;

    @FXML
    private Label labelPlayerThink;

    @FXML
    public void initialize(boolean isLoadedMatch, String loadedProfile) {
        startPlay(isLoadedMatch, loadedProfile);
    }


    // Método para establecer la imagen del bot
    public void setBotCharacter(Image character) {
        if (imgBot != null) {
            imgBot.setImage(character);
        }
    }


    public void setPlayerCharacter(Image character) {
        if (imgPlayer != null) {
            imgPlayer.setImage(character);
        }
    }

    @FXML
    void handleClickExit() {
        if(!matchEnded){
            if(matchStarted){
                playerDataHandler.saveMatch(playerBoard.getNickname(), playerBoard, botBoard, profilePointsLoaded + playerBoard.getActualGameBoatsSunk());
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No se guardará tu partida.");
                alert.setHeaderText("No se guardará tu partida.");
                alert.setContentText("Si quieres guardar tu partida, deberás desplegar a toda tu flota de naves. Sólo se guardará tu usuario.");

                // Personalizar el estilo del Alert
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/com/example/navalbattleminiproject3/styles/styleWelcome.css").toExternalForm());
                dialogPane.getStyleClass().add("custom-alert");

                // Mostrar el Alert
                alert.showAndWait();

                playerDataHandler.updateProfile(playerBoard.getNickname(), profilePointsLoaded + playerBoard.getBoatsSunkEver() );
            }
        }

        GameView.deleteInstance();
        WelcomeView.getInstance();
        System.out.println(playerBoard.showBoard(playerBoard.getBoard()));
        System.out.println(botBoard.showBoard(botBoard.getBoard()));
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
            StackPane playerShip = (StackPane) createShip(temporaryNumberBoats.get(i),1);
            StackPane botShip = (StackPane) createShip(temporaryNumberBoats.get(i),2);

            //botShip.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-style: solid;");
            playerPaneShips.add(playerShip);
            botPaneShips.add(botShip);

        }
    }

    public StackPane createShip(int type, int playerOrBot) {

        StackPane root = new StackPane();
        root.setMinSize(type * 35, 35);
        root.setStyle("-fx-border-color: blue; -fx-border-width: 2; -fx-border-style: solid;");
        for (int i = 0; i < type; i++) {


            if(type == 1){
                StarView star = new StarView();
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
                for (Pane ship : playerPaneShips) {

                    if ( ship.isFocused()) {
                        int col = (int) (event.getX() / 35);
                        int row = (int) (event.getY() / 35);

                        if (col < playerGridPane.getColumnCount() && row < playerGridPane.getRowCount()) {
                            if(playerBoard.spawnBoat(row,col,getShipDirection(ship),getPlayerShipType(ship))){
                                placeBoatInCell(ship, row, col, playerGridPane);
                                missingPlayerShips--;
                                imgPlayerCloud.setVisible(true);
                                labelPlayerThink.setText("Quedan "+missingPlayerShips+" naves para la batalla");
                                if(missingPlayerShips==0){
                                    labelPlayerThink.setText("¡La estrategia está lista! preciona iniciar");
                                }
                                ship.setDisable(true);
                                usedPlayerPanes.add(ship);
                                organizePlayerShipsInVBox(getPlayerShipType(ship));
                            }else{
                                hoverPlayerBoard(row,col,getPlayerShipType(ship),getShipDirection(ship),Color.RED);
                            }
                        }

                        break;
                    }
                }
            });

        }


        return root;
    }


    public int getPlayerShipType(Pane ship) {
        for (int i = 0; i < playerPaneShips.size(); i++) {
            if (playerPaneShips.get(i) == ship) {
                return temporaryNumberBoats.get(i);
            }
            if (botPaneShips.get(i) == ship) {
                return temporaryNumberBoats.get(i);
            }
        }
        return -1;
    }
    public void rotateBoat(StackPane boat, int  playerOrBot) {
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
                boat.setTranslateX(-35);
                boat.setTranslateY(-35);
            }
            else if (actualAngle == 270) {
                boat.setTranslateX(0);
                boat.setTranslateY(0);
            }}

        if(shipType == 4 ) {
            if (actualAngle == 0) {
                boat.setTranslateX(-52);
                boat.setTranslateY(52);
            } else if (actualAngle == 90) {
                boat.setTranslateX(-105);
                boat.setTranslateY(0);
            } else if (actualAngle == 180) {
                boat.setTranslateX(-52);
                boat.setTranslateY(-52);
            }
        }


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
             StackPane ship = getPaneOfShip(type, 2);
             ship.setVisible(false);
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
             botPaneShips.set(positionBotShipsToSetNull, null);

     }

    public StackPane getPaneOfShip(int type, int playerOrBot) {

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

        List<StackPane> shipsList = (playerOrBot == 1) ? playerPaneShips : botPaneShips;

        for (int i = startIndex; i < endIndex; i++) {
            if (shipsList.get(i) != null) {
                StackPane ship = shipsList.get(i);
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
        clearHoverEffect();
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

    public void setVisibleBotPlayerCloud(boolean visible) {
        imgBotCloud.setVisible(visible);
        imgPlayerCloud.setVisible(visible);
    }

    public void resetLabelCloudBotPlayer(){
        labelBotThink.setText("");
        labelPlayerThink.setText("");
    }

    @FXML
    void handleClickStart() {
        if(usedPlayerPanes.size()==10) {
            startButton.setDisable(true);
            setVisibleBotPlayerCloud(true);
            labelBotThink.setText("Que la Fuerza me guíe");
            labelPlayerThink.setText("¡Al ataque!");
            prepareBotBoardForShot();
            matchStarted = true;
        }
    }
    public void changeTurnToBot(){

        disablePlayerBoard(false);
        for (Pane ship : usedPlayerPanes) {
            ship.setMouseTransparent(true);
        }

        System.out.println("Botes destruídos por jugador " + playerBoard.getActualGameBoatsSunk() + " y siempre " +playerBoard.getBoatsSunkEver());
        System.out.println("El jugador gano" + playerBoard.isWinnner());

        System.out.println("Botes destruídos por bot" + botBoard.getActualGameBoatsSunk());
        System.out.println("El jugador gano" + botBoard.isWinnner());

        if(!playerBoard.isWinnner()){
            disablePlayerBoard(false);
            disableBotBoard(true);

            imgBotCloud.setVisible(true);
            labelBotThink.setText("Preparando disparo");
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5)); // 2 segundos de pausa
            pause.setOnFinished(event -> {
                int[] destroyedPart;
                destroyedPart = botBoard.randomShootInOtherBoard(playerBoard);
                int row = destroyedPart[0];
                int col = destroyedPart[1];
                makeShot(row, col, 2);

                // Verificar si el juego terminó
                if (!botBoard.isWinnner()) {
                    disableBotBoard(false);
                    setDisableShotCells();
                } else {
                    System.out.println("El bot ganó");
                    disableBotBoard(true);
                    disablePlayerBoard(true);
                    win(2);
                    System.out.println("Juego terminado");
                }
                imgBotCloud.setVisible(false);
                labelBotThink.setText("");


            });

            pause.play();

        }else {
            System.out.println("El jugador gano");
            disableBotBoard(true);
            disablePlayerBoard(true);
            win(1);
            System.out.println("yepissss");
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

                        setVisibleBotPlayerCloud(false);
                        resetLabelCloudBotPlayer();

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





            System.out.println("Shoot on boat type, "+type );

            StackPane shotPane = new StackPane();
            if (type == -6) {
                FailShot failShot = new FailShot();
                Group ShotGroup = failShot.getRoot();
                ShotGroup.setScaleX(0.25);
                ShotGroup.setScaleY(0.25);
                shotPane.setTranslateX(-35);
                shotPane.setTranslateY(0);
                shotPane.getChildren().add(ShotGroup);
                shotPane.setPrefSize(35, 35); // Tamaño preferido
                shotPane.setMinSize(35, 35);  // Tamaño mínimo
                shotPane.setMaxSize(35, 35);
                shotPane.setTranslateX(0);
            }
            else{
                Bomb Shot = new Bomb();
                Group ShotGroup = Shot.getRoot();
                ShotGroup.setScaleX(0.25);
                ShotGroup.setScaleY(0.25);
                shotPane.setTranslateX(-35);
                shotPane.setTranslateY(0);
                shotPane.getChildren().add(ShotGroup);
                shotPane.setPrefSize(35, 35); // Tamaño preferido
                shotPane.setMinSize(35, 35);  // Tamaño mínimo
                shotPane.setMaxSize(35, 35);
                shotPane.setTranslateX(0);
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
                        if(!isLoadingMatch){
                        playerBoard.boatSunk();
                        }

                        playerBoard.isWinnner();
                        System.out.println("DESTRUIDOOOOOOOO");
                        makeBoatDestroyed(row, col,2);
                        System.out.println("DESTRUIDOOOOOOOO");
                    }else{
                        botGridPane.add(shotPane, col, row);
                    }

                }else{
                    botGridPane.add(shotPane, col, row);
                }

            }else{
                if(type!=(-6)){
                    System.out.println("Hasta aqui llega");

                    isDestroyed = playerBoard.getObjectByIndex(playerBoard.getBoardWithBoats(),row,col).isBoatDestroyed();

                    System.out.println("paso is destroted" + isDestroyed);
                    System.out.println("player boats board\n"+playerBoard.showBoard(playerBoard.getBoard()));

                    if(isDestroyed){
                        if(!isLoadingMatch) {
                            botBoard.boatSunk();
                        }
                        botBoard.isWinnner();
                        makeBoatDestroyed(row, col,1);

                    }else{
                        playerGridPane.add(shotPane, col, row);
                    }

                }else{
                    playerGridPane.add(shotPane, col, row);
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
                    StackPane shotroot = new StackPane();
                    Fire shot = new Fire();
                    Group shotRoot = shot.getRoot();
                    shotRoot.setScaleX(0.25);
                    shotRoot.setScaleY(0.25);
                    shotroot.setTranslateX(-35);
                    shotroot.setTranslateY(0);
                    shotroot.getChildren().add(shotRoot);
                    shotroot.setPrefSize(35, 35); // Tamaño preferido
                    shotroot.setMinSize(35, 35);  // Tamaño mínimo
                    shotroot.setMaxSize(35, 35);
                    shotroot.setTranslateX(0);


                    if(playerOrBot==1){
                        playerGridPane.add(shotroot, column, fila);
                        System.out.println("yellow shot in: fila "+fila+" columna "+column);
                    }else{
                        System.out.println("yellow shot in: fila "+fila+" columna "+column);
                        botGridPane.add(shotroot, column, fila);
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
public void win(int playerOrBot) {
    playerBoard.restartGame();
    botBoard.restartGame();
    playerDataHandler.endMatch(playerBoard.getNickname(), profilePointsLoaded + playerBoard.getActualGameBoatsSunk());
    matchEnded = true;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("YOU WON! CONGRATULATIONS!");
    alert.setHeaderText(null);
    alert.setGraphic(null);
    alert.setContentText(null);
    DialogPane dialogPane = alert.getDialogPane();
    ButtonType okButtonType = ButtonType.OK;
    Button okButton = (Button) alert.getDialogPane().lookupButton(okButtonType);
    Label resultsLabel = new Label();
    Label resultsLabel2 = new Label();
    if (playerOrBot == 1) {
        resultsLabel.setText("¡Felicidades " + playerBoard.getNickname() + "! la fuerza está contigo");
        resultsLabel2.setText("Acumulas un puntaje de: "+ (profilePointsLoaded + playerBoard.getActualGameBoatsSunk())+"\n ¡impresionante!");
    } else {
        resultsLabel.setText("¡El poder de la oscuridad triunfa, los rebeldes cayeron ante su fuerza!" );
        resultsLabel2.setText("Fuiste derrotado, el puntaje de tu oponente fue: " + botBoard.getActualGameBoatsSunk());
    }
    okButton.setStyle(
            "-fx-background-color: #000065; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-size: 25px;"
    );
    resultsLabel.setStyle("-fx-font-family: 'JetBrains Mono'; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: bold; " +
            "-fx-font-size: 32px;");

    resultsLabel2.setStyle("-fx-font-family: 'JetBrains Mono'; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: bold; " +
            "-fx-font-size: 32px;");

    VBox content = new VBox();
    content.getChildren().add(resultsLabel);
    content.getChildren().add(resultsLabel2);
    content.setStyle("-fx-alignment: center;");
    VBox.setMargin(resultsLabel, new Insets(500, 0, 0, 0));

    content.setAlignment(Pos.BOTTOM_CENTER);
    alert.getDialogPane().setContent(content);
    dialogPane.getStylesheets().add(getClass().getResource("/com/example/navalbattleminiproject3/styles/styleGame.css").toExternalForm());
    dialogPane.getStyleClass().add("mi-alerta");
    alert.show();

//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
//            AddPetal(dialogPane);
//        }));
//
//        timeline.setCycleCount(10);
//        timeline.play();

}


    public void loadPlayerTable(){
        positionBotShipsToSetNull = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle rect = new Rectangle(35, 35);
                rect.setFill(Color.LIGHTBLUE);
                rect.setStroke(Color.BLACK);
                playerGridPane.add(rect, j, i);
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int shipType = playerBoard.getNumberByIndex(playerBoard.getBoard(), i, j);
                if(shipType == 1 || shipType == 2 || shipType == 3 || shipType == 4){
                    if(!(usedPlayerBoatsForLoad.contains(playerBoard.getObjectByIndex(playerBoard.getBoardWithBoats(),i,j)))){
                        positionPlayerShipsWithDirection(shipType,i,j);
                        usedPlayerBoatsForLoad.add(playerBoard.getObjectByIndex(playerBoard.getBoardWithBoats(),i,j));
                    }
                }
            }
        }

    }

    public void positionPlayerShipsWithDirection(int type, int row, int col){
        StackPane ship = getPaneOfShip(type,1);
        ship.setDisable(true);
        ship.setFocusTraversable(false);
        int boardDirection = playerBoard.getBoardWithBoats().get(row).get(col).getBoatDirection();

        if (boardDirection==0|| boardDirection==2){


            placeBoatInCell(ship,row,col,playerGridPane);
            usedPlayerPanes.add(ship);

            playerPaneShips.set(positionBotShipsToSetNull, null);

        }else{

            rotateBoat(ship, 2);
            placeBoatInCell(ship,row,col,playerGridPane);
            usedPlayerPanes.add(ship);
            playerPaneShips.set(positionBotShipsToSetNull, null);

        }
    }

    public List<List<Integer>> cleanLoadedBoards(List<List<Integer>> board){
        for (List<Integer> row : board) {
            System.out.println(row);
            for(int i=0; i<row.size(); i++){
                if(row.get(i)==(-6)) {
                    row.set(i, 0);
                }
                if(row.get(i)<0){
                    row.set(i, row.get(i)*(-1));
                }
            }
        }
        System.out.println("Se limpió tablero");
        System.out.println(board);
        return board;

    }

    public void loadShootsOnBoard(int playerOrBot, List<List<Integer>> shootedReferenceboard) {
        for (int i = 0; i < shootedReferenceboard.size(); i++) {
            for (int j = 0; j < shootedReferenceboard.get(i).size(); j++) {
                System.out.println("Número en " +i+j + " :" +shootedReferenceboard.get(i).get(j));

                if (shootedReferenceboard.get(i).get(j) < 0) {
                    System.out.println("DISPARO");
                    if (playerOrBot == 2){
                        playerBoard.shootInOtherBoard(playerBoard, i, j);
                        System.out.println(playerBoard.showBoard(playerBoard.getBoard()));
                    }
                    makeShot(i,j,playerOrBot);
                    //Shoot of boat from player
                    System.out.println("Botes hundidos por player en esta partida "+ playerBoard.getActualGameBoatsSunk());
                    System.out.println("Botes hundidos por bot en esta partida "+  botBoard.getActualGameBoatsSunk());
                }

            }
        }
    }

    public void loadingMatch(String loadedProfile){


        isLoadingMatch = true;
        Object[] returnBoard = playerDataHandler.loadMatch(loadedProfile);

        playerBoard = (PlayerBoard) returnBoard[0];
        botBoard =  (BotBoard) returnBoard[1];



        List<List<Integer>> temporalPlayerBoard = new ArrayList<>();
        List<List<Integer>> temporalBotBoard = new ArrayList<>();

        for (List<Integer> innerList : playerBoard.getBoard()) {
            List<Integer> newInnerList = new ArrayList<>(innerList); // Crear nueva lista para cada lista interna
            temporalPlayerBoard.add(newInnerList);
        }

        for (List<Integer> innerList : botBoard.getBoard()) {
            List<Integer> newInnerList = new ArrayList<>(innerList); // Crear nueva lista para cada lista interna
            temporalBotBoard.add(newInnerList);
        }

        playerBoard.setBoard(cleanLoadedBoards(playerBoard.getBoard()));
        botBoard.setBoard(cleanLoadedBoards(botBoard.getBoard()));

        System.out.println("Tableros recogidos de los temporales");
        System.out.println(playerBoard.showBoard(playerBoard.getBoard()));
        System.out.println(botBoard.showBoard(botBoard.getBoard()));

        createBoardShips();
        for (int i = 1; i < 5; i++) {
            organizePlayerShipsInVBox(i);
        }
        createBotTable();
        loadPlayerTable();


        handleClickStart();

        System.out.println("Botes hundidos por player en esta partida "+ playerBoard.getActualGameBoatsSunk());
        System.out.println("Botes hundidos por bot en esta partida "+  botBoard.getActualGameBoatsSunk());


        loadShootsOnBoard(1, temporalBotBoard);
        loadShootsOnBoard(2, temporalPlayerBoard);

        System.out.println("Tableros ahora");
        System.out.println(playerBoard.showBoard(playerBoard.getBoard()));
        System.out.println(botBoard.showBoard(botBoard.getBoard()));

        System.out.println("Botes hundidos por player en esta partida "+ playerBoard.getActualGameBoatsSunk());
        System.out.println("Botes hundidos por bot en esta partida "+  botBoard.getActualGameBoatsSunk());

        isLoadingMatch = false;
    }




    public void startPlay(boolean isLoadedMatch, String loadedProfile){
        playerDataHandler = new PlayerDataHandler();
        System.out.println("perfiles creados " + playerDataHandler.getNicknamesData());
        imgBotCloud.setVisible(false);
        imgPlayerCloud.setVisible(false);
        Collections.addAll(temporaryNumberBoats, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4);
        if(isLoadedMatch){
            try{

                loadingMatch(loadedProfile);

                matchStarted=true;
                profilePointsLoaded = playerBoard.getBoatsSunkEver();

                System.out.println("Botes hundidos por player en esta partida "+ playerBoard.getActualGameBoatsSunk());
                System.out.println("Botes hundidos por bot en esta partida "+  botBoard.getActualGameBoatsSunk());

            }catch( Exception e){
                System.out.println("Creating new match. "+e.getMessage());
                isLoadedMatch = false;
                isLoadingMatch = false;
            }



        }

        if(!isLoadedMatch){

            playerBoard = new PlayerBoard();
            playerBoard.setNickname(loadedProfile);
            botBoard = new BotBoard();
            createBoardShips();
            for (int i = 1; i < 5; i++) {
                organizePlayerShipsInVBox(i);
            }
            System.out.println("botShips: "+ showArrayBotShips());
            createBotTable();
            createPlayerTable();


        }


    }

}
