package com.example.navalbattleminiproject3.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Fire {
    private Group root;

    public Fire() {
        root = new Group();

        // Círculos
        Circle circle1 = createCircle(64.0, 102.0, 50.0, "#ff4d4d");
        Circle circle2 = createCircle(64.0, 103.0, 41.0, "#ff7f1f");
        Circle circle3 = createCircle(65.0, 105.0, 29.0, "#ffd221");

        // Polígonos
        Polygon polygon1 = createPolygon(39.0, 62.0, "#ff7f1f",
                -16.133, 36.8,
                13.733, 9.066,
                -3.866, -29.866);
        Polygon polygon2 = createPolygon(57.0, 50.0, "#ff7f1f",
                -25.0, 37.866,
                13.733, 9.066,
                8.0, -30.666);
        Polygon polygon3 = createPolygon(75.0, 50.0, "#ff7f1f",
                -25.0, 37.866,
                13.733, 9.066,
                6.0, -47.2);
        Polygon polygon4 = createPolygon(49.0, 72.0, "#ff7f1f",
                -25.0, 37.866,
                13.733, 9.066,
                1.0, -34.533);
        Polygon polygon5 = createPolygon(93.0, 69.0, "#ff7f1f",
                -23.733, 9.066,
                13.733, 9.066,
                5.066, -40.066);
        Polygon polygon6 = createPolygon(62.0, 42.0, "#ff3921",
                -20.133, 25.999,
                2.366, 25.999,
                2.366, -14.666);
        Polygon polygon7 = createPolygon(41.0, 45.0, "#ff3921",
                -13.866, 31.999,
                6.8, 31.999,
                -2.8, -3.733);
        Polygon polygon8 = createPolygon(84.0, 41.0, "#ff3921",
                -20.133, 25.999,
                2.366, 25.999,
                -5.133, -24.333);
        Polygon polygon9 = createPolygon(97.0, 50.0, "#ff3921",
                -20.133, 25.999,
                2.366, 25.999,
                2.366, -14.666);
        Polygon polygon10 = createPolygon(97.0, 55.0, "#ff7f1f",
                -20.133, 25.999,
                2.366, 25.999,
                -2.666, -9.266);
        Polygon polygon11 = createPolygon(83.0, 50.0, "#ff7f1f",
                -20.133, 25.999,
                2.366, 25.999,
                -2.666, -9.266);
        Polygon polygon12 = createPolygon(58.0, 49.0, "#ff7f1f",
                -16.999, 25.999,
                2.366, 25.999,
                2.366, -7.4);
        Polygon polygon13 = createPolygon(43.0, 60.0, "#ff7f1f",
                -14.266, 25.999,
                8.133, 20.133,
                -5.199, -9.733);

        // Agregar elementos al grupo raíz
        root.getChildren().addAll(
                circle1, circle2, circle3,
                polygon1, polygon2, polygon3, polygon4, polygon5,
                polygon6, polygon7, polygon8, polygon9, polygon10,
                polygon11, polygon12, polygon13
        );
    }

    private Circle createCircle(double layoutX, double layoutY, double radius, String fillColor) {
        Circle circle = new Circle(layoutX, layoutY, radius, Color.web(fillColor));
        circle.setStroke(Color.BLACK);
        return circle;
    }

    private Polygon createPolygon(double layoutX, double layoutY, String fillColor, double... points) {
        Polygon polygon = new Polygon(points);
        polygon.setLayoutX(layoutX);
        polygon.setLayoutY(layoutY);
        polygon.setFill(Color.web(fillColor));
        polygon.setStroke(Color.BLACK);
        return polygon;
    }

    public Group getRoot() {
        return root;
    }
}
