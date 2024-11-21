package com.example.navalbattleminiproject3.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Bomb {
    private final Group root;

    public Bomb() {
        root = new Group();

        // Crear los elementos gráficos
        Circle outerCircle = createCircle(64.0, 81.0, 49.0, "#06335e", 0.0);
        Rectangle whiteRectangle = createRectangle(12.0, 23.0, 31.0, 9.0, 51.0, "WHITE", 0.0);
        Rectangle blueRectangle = createRectangle(17.0, 35.0, 38.0, 9.0, -33.1, "#0d6cc4", 0.0);
        Circle redCircle = createCircle(15.0, 12.0, 9.0, "#ff4d4d", 0.0);
        Circle orangeCircle = createCircle(15.0, 11.0, 6.0, "#ff7f1f", 0.0);
        Circle yellowCircle = createCircle(15.0, 11.0, 3.0, "#ffd221", 0.0);

        // Añadir los elementos al grupo raíz
        root.getChildren().addAll(
                outerCircle, whiteRectangle, blueRectangle,
                redCircle, orangeCircle, yellowCircle
        );
    }

    // Método para crear círculos
    private Circle createCircle(double layoutX, double layoutY, double radius, String fillColor, double strokeWidth) {
        Circle circle = new Circle(layoutX, layoutY, radius, Color.web(fillColor));
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(strokeWidth);
        return circle;
    }

    // Método para crear rectángulos
    private Rectangle createRectangle(double layoutX, double layoutY, double width, double height, double rotation,
                                      String fillColor, double strokeWidth) {
        Rectangle rectangle = new Rectangle(width, height, Color.web(fillColor));
        rectangle.setLayoutX(layoutX);
        rectangle.setLayoutY(layoutY);
        rectangle.setArcWidth(5.0);
        rectangle.setArcHeight(5.0);
        rectangle.setRotate(rotation);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(strokeWidth);
        return rectangle;
    }

    public Group getRoot() {
        return root;
    }
}
