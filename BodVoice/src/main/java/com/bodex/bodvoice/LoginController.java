package com.bodex.bodvoice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private UserLogin userLogin;
    private DatabaseInformation databaseInformation;
    private MainController mainController;
    private TokenUser tokenUser;

    String username;
    String password;
    String nickname;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public LoginController() throws SQLException {
        Connection connection = DataBaseConnector.getConnection();
        userLogin = new UserLogin(DataBaseConnector.getConnection());
        databaseInformation = new DatabaseInformation(connection);
        tokenUser = new TokenUser();
    }

    @FXML
    public void switchToSceneMain(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        root = loader.load();
        mainController = loader.getController();
        username = usernameField.getText();
        password = passwordField.getText();
        nickname = databaseInformation.getNickNameByUsername(username);

        if (userLogin.verificationUser(username, password)) {
            Session.setLoggedInUsername(username);
            mainController.getNickLabel().setText(nickname);
            String token = tokenUser.generateNewToken();
            mainController.setFiendsList(databaseInformation.getFriendsList(username));
            mainController.displayFriendsList();
        } else {
            JOptionPane.showMessageDialog(null, "Niepoprawny login lub hasło");
            return;
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToSceneRegister(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(MainController.class.getResource("register-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleLogin(ActionEvent event) throws SQLException, IOException {
        switchToSceneMain(event);
    }
}
