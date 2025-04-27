package application;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class batch extends GridPane {
    private TextField tfDriver = new TextField("com.mysql.cj.jdbc.Driver");
    private TextField tfURL = new TextField("jdbc:mysql://localhost:3306/batch");
    private TextField tfUsername = new TextField("root");
    private PasswordField pfPassword = new PasswordField();

    public batch() {
        setHgap(5);
        setVgap(5);

        add(new Label("JDBC Driver:"), 0, 0);
        add(tfDriver, 1, 0);
        add(new Label("Database URL:"), 0, 1);
        add(tfURL, 1, 1);
        add(new Label("Username:"), 0, 2);
        add(tfUsername, 1, 2);
        add(new Label("Password:"), 0, 3);
        add(pfPassword, 1, 3);
    }

    public String getDriver() {
        return tfDriver.getText();
    }

    public String getURL() {
        return tfURL.getText();
    }

    public String getUsername() {
        return tfUsername.getText();
    }

    public String getPassword() {
        return pfPassword.getText();
    }
}
