package com.example.navalbattleminiproject3.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * La clase HalconView representa una vista gráfica en forma de halcón utilizando varios elementos gráficos como círculos,
 * rectángulos, polígonos y líneas para formar una figura visual compleja.
 * Esta clase extiende Group, lo que permite agregar todos los elementos gráficos en un solo contenedor.
 *
 * @version 1.0
 * @since 2024
 */
public class HalconView {
    private Group root;

    /**
     * Constructor de la clase HalconView que crea todos los elementos gráficos necesarios
     * para formar la figura del halcón. Estos elementos incluyen círculos, rectángulos, polígonos y líneas.
     */
    public HalconView() {
        root = new Group();

        // Crear círculos
        Circle circle1 = new Circle(180.0, 85.0, 71.0, Color.web("#e7e7e7"));
        circle1.setStroke(Color.BLACK);

        Circle circle2 = new Circle(180.0, 86.0, 15.0, Color.web("#a5a9ac"));
        circle2.setStroke(Color.BLACK);

        // Crear círculos negros
        Circle[] blackCircles = {
                new Circle(207.0, 64.0, 9.0),
                new Circle(214.0, 83.0, 9.0),
                new Circle(205.0, 102.0, 9.0),
                new Circle(223.0, 48.0, 9.0),
                new Circle(234.0, 75.0, 9.0),
                new Circle(227.0, 104.0, 9.0)
        };
        for (Circle circle : blackCircles) {
            circle.setFill(Color.web("#1e1c1c"));
            circle.setStroke(Color.WHITE);
            circle.setStrokeWidth(2.0);
        }

        // Crear círculos adicionales
        Circle circle9 = new Circle(180.0, 27.0, 5.0, Color.web("#3b3b3b"));
        circle9.setStroke(Color.WHITE);
        circle9.setStrokeWidth(2.0);

        Circle circle10 = new Circle(180.0, 48.0, 5.0, Color.web("#3b3b3b"));
        circle10.setStroke(Color.WHITE);
        circle10.setStrokeWidth(2.0);

        Circle circle11 = new Circle(181.0, 120.0, 5.0, Color.web("#3b3b3b"));
        circle11.setStroke(Color.WHITE);
        circle11.setStrokeWidth(2.0);

        Circle circle12 = new Circle(181.0, 140.0, 5.0, Color.web("#3b3b3b"));
        circle12.setStroke(Color.WHITE);
        circle12.setStrokeWidth(2.0);

        // Crear rectángulos
        Rectangle rect1 = new Rectangle(89.0, 77.0, 77.0, 17.0);
        rect1.setArcHeight(5.0);
        rect1.setArcWidth(5.0);
        rect1.setFill(Color.web("#d1d1d1"));
        rect1.setStroke(Color.BLACK);

        Rectangle[] smallRectangles = {
                new Rectangle(200.0, 27.0, 10.0, 10.0),
                new Rectangle(131.0, 54.0, 10.0, 10.0),
                new Rectangle(126.0, 101.0, 10.0, 10.0),
                new Rectangle(200.0, 120.0, 10.0, 10.0),
                new Rectangle(149.0, 99.0, 10.0, 10.0)
        };
        for (Rectangle rect : smallRectangles) {
            rect.setFill(Color.web("#cfcfcf"));
            rect.setArcHeight(5.0);
            rect.setArcWidth(5.0);
            rect.setStroke(Color.BLACK);
        }

        // Crear polígonos
        Polygon polygon1 = new Polygon(
                -9.8, -4.6, 99.8, -4.6, 111.6, -40.0);
        polygon1.setLayoutX(14.0);
        polygon1.setLayoutY(80.0);
        polygon1.setFill(Color.web("#e7e7e7"));
        polygon1.setStroke(Color.BLACK);

        Polygon polygon2 = new Polygon(
                -9.2, -4.6, 99.8, -4.6, 112.0, 24.4);
        polygon2.setLayoutX(13.0);
        polygon2.setLayoutY(101.0);
        polygon2.setFill(Color.web("#e7e7e7"));
        polygon2.setStroke(Color.BLACK);

        // Crear líneas
        Line line1 = new Line(-9.2, 7.8, 14.8, -26.2);
        line1.setLayoutX(205.0);
        line1.setLayoutY(54.0);

        Line line2 = new Line(-19.2, 44.2, 21.2, 63.8);
        line2.setLayoutX(215.0);
        line2.setLayoutY(64.0);

        // Añadir todos los elementos al grupo raíz
        root.getChildren().addAll(
                circle1, circle2, blackCircles[0], blackCircles[1], blackCircles[2],
                blackCircles[3], blackCircles[4], blackCircles[5], circle9, circle10,
                circle11, circle12, rect1, polygon1, polygon2, line1, line2
        );
        root.getChildren().addAll(smallRectangles);
    }

    /**
     * Obtiene el grupo raíz de la vista, que contiene todos los elementos gráficos.
     *
     * @return El grupo raíz que contiene los elementos gráficos.
     */
    public Group getRoot() {
        return root;
    }
}
