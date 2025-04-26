package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {
    TextField tfId = new TextField();
    TextField tfLastName = new TextField();
    TextField tfFirstName = new TextField();
    TextField tfMI = new TextField();
    TextField tfAddress = new TextField();
    TextField tfCity = new TextField();
    TextField tfState = new TextField();
    TextField tfTelephone = new TextField();
    TextField tfEmail = new TextField();
    Label lblStatus = new Label();

    Connection conn;

    @Override
    public void start(Stage primaryStage) {
        connectToDB();
        
        tfId.setPrefWidth(120); 
        tfLastName.setPrefWidth(100);
        tfFirstName.setPrefWidth(100);
        tfMI.setPrefWidth(30); 
        tfAddress.setPrefWidth(100); 
        tfCity.setPrefWidth(100);
        tfState.setPrefWidth(40); 
        tfTelephone.setPrefWidth(100); 
        tfEmail.setPrefWidth(100); 

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(5);
        pane.setVgap(5);

        // ID Field
        pane.add(new Label("ID:"), 0, 0);
        pane.add(tfId, 1, 0);

        // Last Name, First Name, MI on the same line
        HBox nameBox = new HBox(5);
        nameBox.getChildren().addAll(new Label("Last Name:"), tfLastName, new Label("First Name:"), tfFirstName, new Label("MI:"), tfMI);
        pane.add(nameBox, 0, 1, 2, 1);

        // Address Field
        pane.add(new Label("Address:"), 0, 2);
        pane.add(tfAddress, 1, 2);

        // City and State on the same line
        HBox cityStateBox = new HBox(5);
        cityStateBox.getChildren().addAll(new Label("City:"), tfCity, new Label("State:"), tfState);
        pane.add(cityStateBox, 0, 3, 2, 1);

        // Telephone and Email Fields
        pane.add(new Label("Telephone:"), 0, 4);
        pane.add(tfTelephone, 1, 4);
        pane.add(new Label("Email:"), 0, 5);
        pane.add(tfEmail, 1, 5);

        // Optionally, constrain MI to only one character input
        tfMI.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() > 1) {
                tfMI.setText(newText.substring(0, 1));
            }
        });

        HBox buttonBox = new HBox(10);
        Button btView = new Button("View");
        Button btInsert = new Button("Insert");
        Button btUpdate = new Button("Update");
        Button btClear = new Button("Clear");
        buttonBox.getChildren().addAll(btView, btInsert, btUpdate, btClear);

        VBox mainLayout = new VBox(10, lblStatus, pane, buttonBox);
        mainLayout.setPadding(new Insets(10));

        btView.setOnAction(e -> viewRecord());
        btInsert.setOnAction(e -> insertRecord());
        btUpdate.setOnAction(e -> updateRecord());
        btClear.setOnAction(e -> clearFields());

        Scene scene = new Scene(mainLayout, 400, 450);
        primaryStage.setTitle("Staff Database App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void connectToDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            lblStatus.setText("Failed to connect to database");
        }
    }

    private void viewRecord() {
        String sql = "SELECT * FROM staff WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tfId.getText());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tfLastName.setText(rs.getString("Last Name"));
                tfFirstName.setText(rs.getString("First Name"));
                tfMI.setText(rs.getString("MI"));
                tfAddress.setText(rs.getString("Address"));
                tfCity.setText(rs.getString("City"));
                tfState.setText(rs.getString("State"));
                tfTelephone.setText(rs.getString("Telephone"));
                tfEmail.setText(rs.getString("Email"));
                lblStatus.setText("Record found");
            } else {
                lblStatus.setText("Record not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertRecord() {
        String sql = "INSERT INTO staff (`ID`, `Last Name`, `First Name`, `MI`, `Address`, `City`, `State`, `Telephone`, `Email`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tfId.getText());
            stmt.setString(2, tfLastName.getText());
            stmt.setString(3, tfFirstName.getText());
            stmt.setString(4, tfMI.getText());
            stmt.setString(5, tfAddress.getText());
            stmt.setString(6, tfCity.getText());
            stmt.setString(7, tfState.getText());
            stmt.setString(8, tfTelephone.getText());
            stmt.setString(9, tfEmail.getText());
            int rows = stmt.executeUpdate();
            lblStatus.setText(rows > 0 ? "Record inserted" : "Insert failed");
        } catch (SQLException e) {
            e.printStackTrace();
            lblStatus.setText("Insert failed: " + e.getMessage());
        }
    }

    private void updateRecord() {
        String sql = "UPDATE staff SET `Last Name`=?, `First Name`=?, `MI`=?, `Address`=?, `City`=?, `State`=?, `Telephone`=?, `Email`=? WHERE ID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tfLastName.getText());
            stmt.setString(2, tfFirstName.getText());
            stmt.setString(3, tfMI.getText());
            stmt.setString(4, tfAddress.getText());
            stmt.setString(5, tfCity.getText());
            stmt.setString(6, tfState.getText());
            stmt.setString(7, tfTelephone.getText());
            stmt.setString(8, tfEmail.getText());
            stmt.setString(9, tfId.getText());
            int rows = stmt.executeUpdate();
            lblStatus.setText(rows > 0 ? "Record updated" : "Update failed: Record not found");
        } catch (SQLException e) {
            e.printStackTrace();
            lblStatus.setText("Update failed: " + e.getMessage());
        }
    }

    private void clearFields() {
        tfId.clear();
        tfLastName.clear();
        tfFirstName.clear();
        tfMI.clear();
        tfAddress.clear();
        tfCity.clear();
        tfState.clear();
        tfTelephone.clear();
        tfEmail.clear();
        lblStatus.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
