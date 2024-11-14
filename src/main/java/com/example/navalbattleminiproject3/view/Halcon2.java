package com.example.navalbattleminiproject3.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Halcon2 {
    private Group root;
    public void Halcon2() {
        Group root = new Group();

        // Círculos
        Circle circle1 = new Circle(333.0, 208.0, 71.0, Color.web("#e7e7e7"));
        circle1.setStroke(Color.BLACK);

        Circle circle2 = new Circle(333.0, 209.0, 15.0, Color.web("#a5a9ac"));
        circle2.setStroke(Color.BLACK);

        Circle circle3 = new Circle(360.0, 187.0, 9.0, Color.BLACK);
        circle3.setStroke(Color.WHITE);
        circle3.setStrokeWidth(2.0);

        Circle circle4 = new Circle(367.0, 206.0, 9.0, Color.BLACK);
        circle4.setStroke(Color.WHITE);
        circle4.setStrokeWidth(2.0);

        Circle circle5 = new Circle(358.0, 225.0, 9.0, Color.BLACK);
        circle5.setStroke(Color.WHITE);
        circle5.setStrokeWidth(2.0);

        Circle circle6 = new Circle(376.0, 171.0, 9.0, Color.BLACK);
        circle6.setStroke(Color.WHITE);
        circle6.setStrokeWidth(2.0);

        Circle circle7 = new Circle(387.0, 198.0, 9.0, Color.BLACK);
        circle7.setStroke(Color.WHITE);
        circle7.setStrokeWidth(2.0);

        Circle circle8 = new Circle(380.0, 227.0, 9.0, Color.BLACK);
        circle8.setStroke(Color.WHITE);
        circle8.setStrokeWidth(2.0);

        // Rectángulos
        Rectangle rect1 = new Rectangle(242.0, 200.0, 77.0, 17.0);
        rect1.setArcHeight(5.0);
        rect1.setArcWidth(5.0);
        rect1.setFill(Color.web("#d1d1d1"));
        rect1.setStroke(Color.BLACK);

        Rectangle rect2 = new Rectangle(318.0, 137.0, 30.0, 51.0);
        rect2.setArcHeight(5.0);
        rect2.setArcWidth(5.0);
        rect2.setFill(Color.web("#e6e6e7"));
        rect2.setStroke(Color.BLACK);

        Rectangle rect3 = new Rectangle(319.0, 229.0, 30.0, 51.0);
        rect3.setArcHeight(5.0);
        rect3.setArcWidth(5.0);
        rect3.setFill(Color.web("#e6e6e7"));
        rect3.setStroke(Color.BLACK);

        Rectangle rect4 = new Rectangle(292.0, 142.0, 18.0, 44.0);
        rect4.setArcHeight(5.0);
        rect4.setArcWidth(5.0);
        rect4.setFill(Color.web("#b7b7b7"));
        rect4.setStroke(Color.BLACK);
        rect4.setRotate(-25.0);

        // Líneas
        Line line1 = new Line(358.0, 177.0, 14.8, -26.2);
        line1.setStroke(Color.BLACK);

        Line line2 = new Line(368.0, 187.0, 21.2, 63.8);
        line2.setStroke(Color.BLACK);

        // Elipses
        Ellipse ellipse1 = new Ellipse(274.0, 146.0, 27.0, 9.0);
        ellipse1.setFill(Color.web("#b7b7b7"));
        ellipse1.setStroke(Color.BLACK);

        // Añadiendo todos los elementos al grupo root
        root.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6, circle7, circle8,
                rect1, rect2, rect3, rect4, line1, line2, ellipse1);
    }

    public Group getRoot() {
        return root;
    }
}