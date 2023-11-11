package ru.dubna.simplejavagui;

import javafx.stage.Stage;
import ru.dubna.simplejavagui.controllers.SceneController;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        new SceneController().init(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}