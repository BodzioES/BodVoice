package com.bodex.bodvoice;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainController {

    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    private UserLogin userLogin;
    private TokenUser tokenUser;
    private DatabaseInformation databaseInformation;

    private List<String> fiendsList;

    public MainController() throws SQLException {
        tokenUser = new TokenUser();
        Connection connection = DataBaseConnector.getConnection();
        databaseInformation = new DatabaseInformation(connection);
        userLogin = new UserLogin(DataBaseConnector.getConnection());

        fiendsList = new ArrayList<>();
    }

    @FXML
    private Label nickLabel;
    @FXML
    private VBox friendsVBox;
    @FXML
    private void initialize() {
        String loggedInUsername = Session.getLoggedInUsername();
        nickLabel.setText(loggedInUsername);

        friendsVBox = new VBox();

        fiendsList = databaseInformation.getFriendsList(loggedInUsername);
        displayFriendsList();
    }

    public void displayFriendsList(){
        //friendsVBox.getChildren().clear();

        if(fiendsList.isEmpty()){
            TextFlow textFlow = new TextFlow();
            Text messageText = new Text("Nie posiadasz znajomych.");
            messageText.setStyle("-fx-fill: white; -fx-font-size: 18px;");
            textFlow.getChildren().add(messageText);
            friendsVBox.getChildren().add(textFlow);
        }
        else {
            for (String friend : fiendsList) {
                Label friendLabel = new Label(friend);
                friendLabel.setStyle("-fx-text-fill: white;");
                friendsVBox.getChildren().add(friendLabel);
            }
        }
        VBox mainVBox = (VBox) root.lookup("#friendVBox");
        mainVBox.getChildren().add(friendsVBox);
    }


    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        MainController.stage = stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        MainController.scene = scene;
    }

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        MainController.root = root;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public TokenUser getTokenUser() {
        return tokenUser;
    }

    public void setTokenUser(TokenUser tokenUser) {
        this.tokenUser = tokenUser;
    }

    public DatabaseInformation getDatabaseInformation() {
        return databaseInformation;
    }

    public void setDatabaseInformation(DatabaseInformation databaseInformation) {
        this.databaseInformation = databaseInformation;
    }

    public Label getNickLabel() {
        return nickLabel;
    }

    public void setNickLabel(String nickLabel) {
        this.nickLabel.setText(nickLabel);
    }

    public VBox getFriendsVBox() {
        return friendsVBox;
    }

    public void setFriendsVBox(VBox friendsVBox) {
        this.friendsVBox = friendsVBox;
    }

    public List<String> getFiendsList() {
        return fiendsList;
    }

    public void setFiendsList(List<String> fiendsList){
        this.fiendsList = fiendsList;
    }


}
