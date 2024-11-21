package com.example.navalbattleminiproject3.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class represents a failed shot in the game.
 * The visual representation consists of two red rectangles that form an "X" shape to indicate a failed shot.
 * This is used to visually show when a shot misses a target on the game board.
 * @author Maria Juliana Saavedra, Libardo Alejandro Quintero, Juan David Rincon Lopez
 * @version 1.0
 */
public class FailShot {
    private final Group root;

    /**
     * Constructor that initializes the graphical elements representing a failed shot.
     * It creates two red rectangles that are rotated to form an "X" shape, representing the failed shot.
     * These elements are added to the root `Group`.
     * @version 1.0
     */
    public FailShot() {
        root = new Group();

        // Create the rectangles representing the failed shot
        Rectangle rectangle1 = createRectangle(42.0, 0.0, 23.0, 100.0, 45.0, "#ff1f1f");
        Rectangle rectangle2 = createRectangle(40.0, 1.0, 23.0, 100.0, -45.0, "#ff1f1f");

        // Add the rectangles to the root group
        root.getChildren().addAll(rectangle1, rectangle2);
    }

    /**
     * Helper method to create rectangles with specific attributes.
     * @param layoutX the x-coordinate of the rectangle's position.
     * @param layoutY the y-coordinate of the rectangle's position.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param rotation the rotation angle of the rectangle in degrees.
     * @param fillColor the color to fill the rectangle.
     * @return the created rectangle.
     * @version 1.0
     */
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

    /**
     * Gets the root `Group` containing all graphical elements of the failed shot.
     * @return the root `Group` containing all shapes.
     * @version 1.0
     */
    public Group getRoot() {
        return root;
    }
}
