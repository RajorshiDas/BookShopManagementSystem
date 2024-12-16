package com.example.bookshopmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HelloController {

    @FXML
    private Button close;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    // This method is used to close the application
    public void close() {
        System.exit(0);
    }

    // Method to verify user login and open the dashboard if successful
    public void loginAdmin() {
        // Get the username and password entered by the user
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();

        // Check if username and password are not empty
        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");
            return;
        }

        // Database connection and query preparation
        Connection connect = database.connectDb(); // Assumes the `Database` class is defined as before
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?"; // Query to check the credentials

        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, enteredUsername);
            prepare.setString(2, enteredPassword);

            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                // If the username and password match a record in the database
                getData.username = username.getText();

                showAlert(AlertType.INFORMATION, "Information Message", "Successfully Logged In");

                // Close the current login window
                loginBtn.getScene().getWindow().hide();

                // Load and display the dashboard.fxml
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
            } else {
                // If no matching credentials are found
                showAlert(AlertType.ERROR, "Error Message", "Wrong Username/Password");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "An error occurred while connecting to the database.");
        }
    }

    // Utility method to show an alert box with a specific type, title, and content
    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
