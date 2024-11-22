package com.example.navalbattleminiproject3.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * La clase `StarView` crea una representación gráfica de una estrella utilizando elementos gráficos de JavaFX como círculos,
 * rectángulos y líneas. Estos elementos se organizan en un solo grupo (`Group`) que puede ser agregado a una escena en una aplicación JavaFX.
 *
 * @version 1.0
 * @since 2024
 */
public class StarView {
    private Group root;

    /**
     * Constructor de la clase StarView que crea todos los elementos gráficos necesarios
     * para formar una figura de una estrella. Estos elementos incluyen círculos, rectángulos,
     * y otras formas geométricas.
     */
    public StarView() {
        root = new Group();

        // Círculos principales
        Circle circle1 = new Circle(138.0, 83.0, 82.0, Color.web("#eae9e9"));
        circle1.setStroke(Color.BLACK);

        Circle circle2 = new Circle(140.0, 47.0, 27.0, Color.web("#000000c7"));
        circle2.setStroke(Color.BLACK);

        Circle circle3 = new Circle(140.0, 42.0, 10.0, Color.RED);
        circle3.setStroke(Color.BLACK);

        // Rectángulos que componen la estrella
        Rectangle rect1 = createRectangle(57.0, 80.0, 162.0, 14.0, "#eae9e9", Color.BLACK);
        Rectangle rect2 = createRectangle(132.0, 96.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect3 = createRectangle(117.0, 96.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect4 = createRectangle(101.0, 96.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect5 = createRectangle(85.0, 96.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect6 = createRectangle(69.0, 96.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect7 = createRectangle(147.0, 96.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect8 = createRectangle(162.0, 96.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect9 = createRectangle(177.0, 96.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect10 = createRectangle(192.0, 96.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect11 = createRectangle(84.0, 126.0, 12.0, 18.0, "#b7b7b7", Color.WHITE);
        Rectangle rect12 = createRectangle(100.0, 126.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect13 = createRectangle(117.0, 126.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect14 = createRectangle(132.0, 126.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect15 = createRectangle(148.0, 126.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect16 = createRectangle(163.0, 126.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect17 = createRectangle(177.0, 127.0, 12.0, 18.0, "#b7b7b7", Color.WHITE);
        Rectangle rect18 = createRectangle(64.0, 50.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect19 = createRectangle(78.0, 50.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect20 = createRectangle(92.0, 50.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect21 = createRectangle(92.0, 20.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect22 = createRectangle(78.0, 29.0, 12.0, 18.0, "#b7b7b7", Color.WHITE);
        Rectangle rect23 = createRectangle(171.0, 49.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect24 = createRectangle(185.0, 49.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect25 = createRectangle(199.0, 49.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect26 = createRectangle(171.0, 19.0, 12.0, 28.0, "#b7b7b7", Color.WHITE);
        Rectangle rect27 = createRectangle(185.0, 28.0, 12.0, 18.0, "#b7b7b7", Color.WHITE);
        Rectangle rect28 = createRectangle(107.0, 9.0, 12.0, 18.0, "#b7b7b7", Color.WHITE);
        Rectangle rect29 = createRectangle(123.0, 4.0, 12.0, 15.0, "#b7b7b7", Color.WHITE);
        Rectangle rect30 = createRectangle(140.0, 3.0, 12.0, 15.0, "#b7b7b7", Color.WHITE);
        Rectangle rect31 = createRectangle(157.0, 8.0, 12.0, 18.0, "#b7b7b7", Color.WHITE);

        // Círculos adicionales
        Circle circle4 = new Circle(75.0, 87.0, 6.0, Color.web("#767676"));
        circle4.setStroke(Color.BLACK);

        Circle circle5 = new Circle(204.0, 87.0, 6.0, Color.web("#999999"));
        circle5.setStroke(Color.BLACK);

        // Rectángulo en el centro
        Rectangle rect32 = createRectangle(119.0, 82.0, 40.0, 10.0, null, Color.WHITE);

        // Añadir todos los elementos al grupo raíz
        root.getChildren().addAll(
                circle1, circle2, circle3, rect1, rect2, rect3, rect4, rect5, rect6, rect7,
                rect8, rect9, rect10, rect11, rect12, rect13, rect14, rect15, rect16,
                rect17, rect18, rect19, rect20, rect21, rect22, rect23, rect24, rect25,
                rect26, rect27, rect28, rect29, rect30, rect31, circle4, circle5, rect32
        );
    }

    /**
     * Crea un rectángulo con las propiedades especificadas.
     *
     * @param x            La posición en el eje X.
     * @param y            La posición en el eje Y.
     * @param width        El ancho del rectángulo.
     * @param height       La altura del rectángulo.
     * @param fillColor    El color de relleno del rectángulo.
     * @param strokeColor  El color del borde del rectángulo.
     * @return El rectángulo creado.
     */
    private Rectangle createRectangle(double x, double y, double width, double height, String fillColor, Color strokeColor) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        if (fillColor != null) rectangle.setFill(Color.web(fillColor));
        if (strokeColor != null) rectangle.setStroke(strokeColor);
        return rectangle;
    }

    /**
     * Obtiene el grupo raíz que contiene todos los elementos gráficos de la vista.
     *
     * @return El grupo raíz.
     */
    public Group getRoot() {
        return root;
    }
}
