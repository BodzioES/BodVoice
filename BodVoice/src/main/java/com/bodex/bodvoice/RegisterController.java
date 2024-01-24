package com.bodex.bodvoice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class RegisterController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane registerPane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField displayNameField;
    @FXML
    private TextField emailField;
    @FXML
    private DatePicker dateOfBirthPicker;
    @FXML
    private Button registerButton;

    @FXML
    public void switchToSceneLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(MainController.class.getResource("login-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String displayName = displayNameField.getText();
        String email = emailField.getText();
        LocalDate dateOfBirth = dateOfBirthPicker.getValue();

        if(!Validations.isEmailValid(email)){
            System.out.println("Invalid email address");
            return;
        }
        if (!Validations.isPasswordValid(password)){
            System.out.println("Invalid password");
            return;
        }
// obiekt User
        User newUser = new User(displayName,username, password, email, dateOfBirth);
// Zapis użytkownika do bazy danych
        saveUserToDatabase(newUser);
    }

    private void saveUserToDatabase(User user) {
        String sql = "INSERT INTO Users (nickName, username, password, email, dateOfBirth) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DataBaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getDisplayName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setDate(5, Date.valueOf(user.getDateOfBirth()));

            // Wykonaj zapytanie
            preparedStatement.executeUpdate();

            System.out.println("Użytkownik dodany do bazy danych!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
