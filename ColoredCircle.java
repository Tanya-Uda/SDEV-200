package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class ColoredCircle extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a circle
        Circle circle = new Circle(50);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        // Change color when mouse is pressed
        circle.setOnMousePressed((MouseEvent e) -> circle.setFill(Color.BLACK));

        // Change color back when mouse is released
        circle.setOnMouseReleased((MouseEvent e) -> circle.setFill(Color.WHITE));

        // Set up the layout
        StackPane root = new StackPane(circle);
        Scene scene = new Scene(root, 200, 200);

        // Set up the stage
        primaryStage.setTitle("Change Color Using Mouse");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}