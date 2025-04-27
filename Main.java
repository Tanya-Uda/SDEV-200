package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.sql.*;

public class Main extends Application {

    private Connection conn;
    private Label lblStatus = new Label("Not connected");

    @Override
    public void start(Stage primaryStage) {
        // Top: Status + Connect Button
        Button btnConnect = new Button("Connect to Database");
        HBox topBox = new HBox(10, lblStatus, btnConnect);
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setPadding(new Insets(10));

        // Bottom: Batch and Non-Batch Buttons
        Button btnBatchUpdate = new Button("Batch Update");
        Button btnNonBatchUpdate = new Button("Non-Batch Update");
        HBox bottomBox = new HBox(10, btnBatchUpdate, btnNonBatchUpdate);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(bottomBox);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Exercise35_01");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Event handlers
        btnConnect.setOnAction(e -> connectToDatabase());
        btnBatchUpdate.setOnAction(e -> {
            if (conn != null) {
                long time = insertWithBatch();
                lblStatus.setText("Batch update completed\nThe elapsed time is " + time + " ms");
            } else {
                lblStatus.setText("Not connected");
            }
        });
        btnNonBatchUpdate.setOnAction(e -> {
            if (conn != null) {
                long time = insertWithoutBatch();
                lblStatus.setText("Non-Batch update completed\nThe elapsed time is " + time + " ms");
            } else {
                lblStatus.setText("Not connected");
            }
        });
    }

    private void connectToDatabase() {
        batch connectionPanel = new batch();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Connect to DB");
        dialog.getDialogPane().setContent(connectionPanel);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CLOSE);

        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    Class.forName(connectionPanel.getDriver());
                    conn = DriverManager.getConnection(
                            connectionPanel.getURL(),
                            connectionPanel.getUsername(),
                            connectionPanel.getPassword()
                    );
                    lblStatus.setText("Connected successfully");
                } catch (Exception ex) {
                    lblStatus.setText("Connection failed: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    private long insertWithoutBatch() {
        String sql = "INSERT INTO temp (`Num 1`, `Num 2`, `Num 3`) VALUES (?, ?, ?)";
        long startTime = System.currentTimeMillis();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < 1000; i++) {
                stmt.setDouble(1, Math.random());
                stmt.setDouble(2, Math.random());
                stmt.setDouble(3, Math.random());
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private long insertWithBatch() {
        // Corrected column names using backticks (`) instead of single quotes (')
        String sql = "INSERT INTO Temp (`Num 1`, `Num 2`, `Num 3`) VALUES (?, ?, ?)";
        long startTime = System.currentTimeMillis();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < 1000; i++) {
                stmt.setDouble(1, Math.random());
                stmt.setDouble(2, Math.random());
                stmt.setDouble(3, Math.random());
                stmt.addBatch();

                if (i % 100 == 0) { // Optional: execute every 100 iterations
                    stmt.executeBatch();
                }
            }
            stmt.executeBatch(); // Execute any remaining batches
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
