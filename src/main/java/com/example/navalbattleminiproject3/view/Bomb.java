package com.example.navalbattleminiproject3.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * This class creates a graphical representation of a bomb using JavaFX shapes.
 * The bomb consists of circles and rectangles, arranged to form a stylized bomb shape.
 * This class is part of the game view and is used to represent a bomb in the game visually.
 * @author Maria Juliana Saavedra, Libardo Alejandro Quintero, Juan David Rincon Lopez
 * @version 1.0
 */
public class Bomb {
    private final Group root;

    /**
     * Constructor that initializes the graphical elements of the bomb.
     * It creates several circles and rectangles to form the bomb's visual representation.
     * All these elements are added to the root `Group`.
     * @version 1.0
     */
    public Bomb() {
        root = new Group();

        // Create the graphical elements
        Circle outerCircle = createCircle(64.0, 81.0, 49.0, "#06335e", 0.0);
        Rectangle whiteRectangle = createRectangle(12.0, 23.0, 31.0, 9.0, 51.0, "WHITE", 0.0);
        Rectangle blueRectangle = createRectangle(17.0, 35.0, 38.0, 9.0, -33.1, "#0d6cc4", 0.0);
        Circle redCircle = createCircle(15.0, 12.0, 9.0, "#ff4d4d", 0.0);
        Circle orangeCircle = createCircle(15.0, 11.0, 6.0, "#ff7f1f", 0.0);
        Circle yellowCircle = createCircle(15.0, 11.0, 3.0, "#ffd221", 0.0);

        // Add all elements to the root Group
        root.getChildren().addAll(
                outerCircle, whiteRectangle, blueRectangle,
                redCircle, orangeCircle, yellowCircle
        );
    }

    /**
     * Helper method to create circles with specific attributes.
     * @param layoutX the x-coordinate of the circle's position.
     * @param layoutY the y-coordinate of the circle's position.
     * @param radius the radius of the circle.
     * @param fillColor the color to fill the circle.
     * @param strokeWidth the width of the circle's stroke.
     * @return the created circle.
     * @version 1.0
     */
    private Circle createCircle(double layoutX, double layoutY, double radius, String fillColor, double strokeWidth) {
        Circle circle = new Circle(layoutX, layoutY, radius, Color.web(fillColor));
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(strokeWidth);
        return circle;
    }

    /**
     * Helper method to create rectangles with specific attributes.
     * @param layoutX the x-coordinate of the rectangle's position.
     * @param layoutY the y-coordinate of the rectangle's position.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param rotation the rotation of the rectangle in degrees.
     * @param fillColor the color to fill the rectangle.
     * @param strokeWidth the width of the rectangle's stroke.
     * @return the created rectangle.
     * @version 1.0
     */
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

    /**
     * Gets the root `Group` containing all graphical elements of the bomb.
     * @return the root `Group` containing all shapes.
     * @version 1.0
     */
    public Group getRoot() {
        return root;
    }
}
