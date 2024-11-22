package com.example.navalbattleminiproject3.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

/**
 * This class creates a graphical representation of a destroyer ship using JavaFX shapes.
 * The ship is represented by various rectangles, polygons, and circles to form a complex visual representation.
 * This class is part of the game view and is used to represent a destroyer in the game visually.
 * @author Maria Juliana Saavedra, Libardo Alejandro Quintero, Juan David Rincon Lopez
 * @version 1.0
 */
public class DestructorView extends Group {
    private Group root;

    /**
     * Constructor that initializes the graphical elements of the destroyer ship.
     * It creates several rectangles, polygons, and other shapes to form the destroyer's visual representation.
     * All these elements are added to the root `Group`.
     * @version 1.0
     */
    public DestructorView() {
        root = new Group();

        // Main rectangles
        Rectangle rectangle1 = createRectangle(129.0, 25.0, 111.0, 27.0, Color.web("#565656"));
        Rectangle rectangle2 = createRectangle(124.0, 124.0, 111.0, 27.0, Color.web("#565656"));

        // Polygon
        Polygon polygon = new Polygon(
                -116.66667175292969, 56.59999084472656,
                167.0, -0.33333587646484375,
                -116.66667175292969, -88.46665954589844
        );
        polygon.setLayoutX(173.0);
        polygon.setLayoutY(95.0);
        polygon.setFill(Color.web("#6e6e6e"));
        polygon.setStroke(Color.BLACK);

        // Additional rectangles
        Rectangle rectangle3 = createRectangle(24.0, 44.0, 41.0, 74.0, Color.web("#6e6e6e"));
        Rectangle rectangle4 = createRectangle(6.0, 60.0, 115.0, 46.0, Color.web("#6e6e6e"));

        // Small detail rectangles
        Rectangle[] detailRectangles = {
                createRectangle(69.0, 13.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(101.0, 38.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(138.0, 63.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(148.0, 43.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(66.0, 116.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(224.0, 65.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(60.0, 20.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(85.0, 39.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(126.0, 84.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(211.0, 60.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(260.0, 73.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(160.0, 52.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(278.0, 85.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(71.0, 37.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(82.0, 20.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(113.0, 34.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(195.0, 54.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(85.0, 109.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(128.0, 38.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(95.0, 119.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(109.0, 110.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(76.0, 126.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(156.0, 94.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(229.0, 84.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(183.0, 97.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(142.0, 99.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(240.0, 71.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(151.0, 74.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(166.0, 73.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(128.0, 112.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(174.0, 51.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(179.0, 73.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(194.0, 76.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(199.0, 100.0, 7.0, 18.0, Color.web("#3b3b3b")),
                createRectangle(170.0, 95.0, 7.0, 18.0, Color.web("#3b3b3b"))
        };

        // Add all elements to the root Group
        root.getChildren().addAll(rectangle1, rectangle2, polygon, rectangle3, rectangle4);
        root.getChildren().addAll(detailRectangles);
    }

    /**
     * Helper method to create rectangles with specific attributes.
     * @param x the x-coordinate of the rectangle's position.
     * @param y the y-coordinate of the rectangle's position.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param fill the color to fill the rectangle.
     * @return the created rectangle.
     * @version 1.0
     */
    private Rectangle createRectangle(double x, double y, double width, double height, Color fill) {
        Rectangle rect = new Rectangle(width, height, fill);
        rect.setLayoutX(x);
        rect.setLayoutY(y);
        rect.setArcWidth(5.0);
        rect.setArcHeight(5.0);
        rect.setStroke(Color.BLACK);
        return rect;
    }

    /**
     * Gets the root `Group` containing all graphical elements of the destroyer ship.
     * @return the root `Group` containing all shapes.
     * @version 1.0
     */
    public Group getRoot() {
        return root;
    }
}
