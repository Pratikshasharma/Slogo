package gui;

import javafx.scene.layout.BorderPane;

/*
 * Used to implement a lambda expression for changing the background color of the canvas
 * @author Teddy
 */
public interface BackgroundChangeable {
	
	public void changeBackground(BorderPane root, String color);
}
