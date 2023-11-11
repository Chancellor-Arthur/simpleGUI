package ru.dubna.simplejavagui.controllers;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public final class SceneController {

    public void switchToCreateUserScene(Event event) throws IOException {
        defaultSwitch(event, "/ru/dubna/simplejavagui/scenes/UserCreate.fxml");
    }

    public void switchToUpdateUserScene(Event event) throws IOException {
        defaultSwitch(event, "/ru/dubna/simplejavagui/scenes/UserUpdate.fxml");
    }

    public void switchToShowUsersScene(Event event) throws IOException {
        defaultSwitch(event, "/ru/dubna/simplejavagui/scenes/UsersTable.fxml");
    }

    public void defaultSwitch(Event event, String path) throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource(path)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void init(Stage stage) throws IOException {
        FXMLLoader usersLoader = new FXMLLoader(
                getClass().getResource("/ru/dubna/simplejavagui/scenes/UsersTable.fxml"));
        Scene scene = new Scene(usersLoader.load(), 800, 600);
        stage.setTitle("Пользователи");
        stage.setScene(scene);
        stage.show();
    }
}
