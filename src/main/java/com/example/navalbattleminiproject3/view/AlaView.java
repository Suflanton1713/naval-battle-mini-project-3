package com.example.navalbattleminiproject3.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class AlaView extends Group {
    private Group root;
    public AlaView() {
        root = new Group();
        // Ellipse
        Ellipse ellipse = new Ellipse(186.0, 89.0, 136.0, 22.0);
        ellipse.setFill(Color.valueOf("#dfdfdf"));
        ellipse.setStroke(Color.BLACK);

        // Rectangles
        Rectangle rect1 = createRectangle(71.0, 62.0, 28.0, 22.0, "#dfdfdf");
        Rectangle rect2 = createRectangle(71.0, 91.0, 28.0, 22.0, "#dfdfdf");
        Rectangle rect3 = createRectangle(39.0, 66.0, 33.0, 15.0, "#ffae51");
        Rectangle rect4 = createRectangle(39.0, 95.0, 33.0, 15.0, "#ffae51");
        Rectangle rect5 = createRectangle(68.0, 15.0, 32.0, 50.0, "#dfdfdf");
        Rectangle rect6 = createRectangle(69.0, 111.0, 32.0, 50.0, "#dfdfdf");
        Rectangle rect7 = createRectangle(24.0, 15.0, 200.0, 4.0, "#dfdfdf");
        Rectangle rect8 = createRectangle(33.0, 158.0, 200.0, 4.0, "#dfdfdf");
        Rectangle rect9 = createRectangle(151.0, 79.0, 42.0, 15.0, "#4fc2ff");
        Rectangle rect10 = createRectangle(198.0, 84.0, 42.0, 6.0, "#ffca68");
        Rectangle rect11 = createRectangle(61.0, 84.0, 56.0, 8.0, "#696969");

        // Polygons
        Polygon polygon1 = createPolygon(
                -51.66667556762695, -11.000007629394531,
                -30.00000762939453, -11.000007629394531,
                -30.00000762939453, -62.000003814697266
        );
        polygon1.setLayoutX(100.0);
        polygon1.setLayoutY(77.0);

        Polygon polygon2 = createPolygon(
                -62.26667022705078, 22.99999237060547,
                -39.866668701171875, 22.99999237060547,
                -39.866668701171875, 70.06666564941406
        );
        polygon2.setLayoutX(110.0);
        polygon2.setLayoutY(87.0);

        // Ellipse
        Ellipse ellipse2 = new Ellipse(297.0, 89.0, 22.0, 8.0);
        ellipse2.setFill(Color.valueOf("#ffca68"));
        ellipse2.setStroke(Color.BLACK);

        // Add all elements to the Group
        root.getChildren().addAll(
                ellipse, rect1, rect2, rect3, rect4, rect5, rect6, rect7, rect8, rect9, rect10, rect11,
                polygon1, polygon2, ellipse2
        );
    }

    // Helper method to create rectangles
    private Rectangle createRectangle(double x, double y, double width, double height, String fillColor) {
        Rectangle rect = new Rectangle(x, y, width, height);
        rect.setFill(Color.valueOf(fillColor));
        rect.setStroke(Color.BLACK);
        rect.setArcWidth(5.0);
        rect.setArcHeight(5.0);
        return rect;
    }

    // Helper method to create polygons
    private Polygon createPolygon(double... points) {
        Polygon polygon = new Polygon(points);
        polygon.setFill(Color.valueOf("#dfdfdf"));
        polygon.setStroke(Color.BLACK);
        return polygon;
    }
    public Group getRoot() {
        return root;
    }
}
