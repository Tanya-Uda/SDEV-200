package application;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a pane to hold the images views
		GridPane pane = new GridPane();
		pane.setHgap(10);
        pane.setVgap(10);

                // Image file paths (Ensure these files exist in the project directory)
                String[] imageFiles = {
                    "flag1.gif",
                    "flag2.gif",
                    "flag6.gif",
                    "flag7.gif"
                };

                    // Set fixed width and height for all images
                    final double IMAGE_WIDTH = 150;
                    final double IMAGE_HEIGHT = 100;

                    int index = 0;
                    for (int row = 0; row < 2; row++) {
                        for (int col = 0; col < 2; col++) {
                            Image image = new Image(imageFiles[index]); // Load the image
                            ImageView imageView = new ImageView(image);

                            // Set the same size for all images
                            imageView.setFitWidth(IMAGE_WIDTH);
                            imageView.setFitHeight(IMAGE_HEIGHT);
                            imageView.setPreserveRatio(true);
                            imageView.setSmooth(true);

                            pane.add(imageView, col, row);
                            index++;
                        }
                    }

                    // Create and show the scene
                    Scene scene = new Scene(pane, 350, 250);
                    primaryStage.setTitle("Display Images");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }

                public static void main(String[] args) {
                    launch(args);
                }
            }
