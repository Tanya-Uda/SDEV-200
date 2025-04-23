package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Scrollabar extends Application {

    private Text textDisplay;

    @Override
    public void start(Stage primaryStage) {
        // Create text display
        textDisplay = new Text("Show colors");
        textDisplay.setStyle("-fx-font-size: 30;");

        // Create scrollbars for Red, Green, Blue, and Opacity
        ScrollBar redScrollBar = createColorScrollBar();
        ScrollBar greenScrollBar = createColorScrollBar();
        ScrollBar blueScrollBar = createColorScrollBar();
        ScrollBar opacityScrollBar = createOpacityScrollBar();

        // Labels for scrollbars
        Label redLabel = new Label("Red");
        Label greenLabel = new Label("Green");
        Label blueLabel = new Label("Blue");
        Label opacityLabel = new Label("Opacity");

        // Update color on scrollbar change
        redScrollBar.valueProperty().addListener((obs, oldVal, newVal) -> 
            updateColor(
                (int) redScrollBar.getValue(),
                (int) greenScrollBar.getValue(),
                (int) blueScrollBar.getValue(),
                opacityScrollBar.getValue()
            )
        );

        greenScrollBar.valueProperty().addListener((obs, oldVal, newVal) -> 
            updateColor(
                (int) redScrollBar.getValue(),
                (int) greenScrollBar.getValue(),
                (int) blueScrollBar.getValue(),
                opacityScrollBar.getValue()
            )
        );

        blueScrollBar.valueProperty().addListener((obs, oldVal, newVal) -> 
            updateColor(
                (int) redScrollBar.getValue(),
                (int) greenScrollBar.getValue(),
                (int) blueScrollBar.getValue(),
                opacityScrollBar.getValue()
            )
        );

        opacityScrollBar.valueProperty().addListener((obs, oldVal, newVal) -> 
            updateColor(
                (int) redScrollBar.getValue(),
                (int) greenScrollBar.getValue(),
                (int) blueScrollBar.getValue(),
                opacityScrollBar.getValue()
            )
        );

        // Layout using GridPane for cleaner arrangement
        GridPane scrollbarGrid = new GridPane();
        scrollbarGrid.setHgap(10);
        scrollbarGrid.setVgap(10);
        scrollbarGrid.addRow(0, redLabel, redScrollBar);
        scrollbarGrid.addRow(1, greenLabel, greenScrollBar);
        scrollbarGrid.addRow(2, blueLabel, blueScrollBar);
        scrollbarGrid.addRow(3, opacityLabel, opacityScrollBar);

        VBox root = new VBox(20, textDisplay, scrollbarGrid);
        root.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: white;");

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Color Selector (ScrollBar)");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initialize the color display
        updateColor(0, 0, 0, 1.0);
    }

    private ScrollBar createColorScrollBar() {
        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setMin(0);
        scrollBar.setMax(255);
        scrollBar.setValue(0);
        return scrollBar;
    }

    private ScrollBar createOpacityScrollBar() {
        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setMin(0);
        scrollBar.setMax(1);
        scrollBar.setValue(1);
        scrollBar.setBlockIncrement(0.01); // Smaller increments for opacity
        return scrollBar;
    }

    private void updateColor(int r, int g, int b, double opacity) {
        Color color = Color.rgb(r, g, b, opacity);
        textDisplay.setFill(color);
    }

    public static void main(String[] args) {
        launch(args);
    }
}