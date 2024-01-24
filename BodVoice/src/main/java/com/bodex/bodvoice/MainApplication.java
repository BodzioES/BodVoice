package com.bodex.bodvoice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Image icon = new Image("D:\\Programowanie\\JAVA\\BodVoice\\BodVoice\\src\\main\\resources\\image\\BodCHAT.png");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        Scene scene = new Scene(root);

        stage.getIcons().add(icon);
        stage.setTitle("BodVoice");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();//wofwifbwuui
    }
}
