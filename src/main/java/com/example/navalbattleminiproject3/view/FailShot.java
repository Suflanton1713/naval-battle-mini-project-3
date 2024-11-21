package com.example.navalbattleminiproject3.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FailShot {
    private final Group root;

    public FailShot() {
        root = new Group();

        // Crear los rectángulos
        Rectangle rectangle1 = createRectangle(42.0, 0.0, 23.0, 100.0, 45.0, "#ff1f1f");
        Rectangle rectangle2 = createRectangle(40.0, 1.0, 23.0, 100.0, -45.0, "#ff1f1f");

        // Añadir los rectángulos al grupo raíz
        root.getChildren().addAll(rectangle1, rectangle2);
    }

    // Método para crear rectángulos
    private Rectangle createRectangle(double layoutX, double layoutY, double width, double height, double rotation,
                                      String fillColor) {
        Rectangle rectangle = new Rectangle(width, height, Color.web(fillColor));
        rectangle.setLayoutX(layoutX);
        rectangle.setLayoutY(layoutY);
        rectangle.setArcWidth(5.0);
        rectangle.setArcHeight(5.0);
        rectangle.setRotate(rotation);
        rectangle.setStroke(Color.BLACK);
        return rectangle;
    }

    // Método para obtener el grupo raíz
    public Group getRoot() {
        return root;
    }
}
