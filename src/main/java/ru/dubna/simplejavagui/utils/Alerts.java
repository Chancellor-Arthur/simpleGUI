package ru.dubna.simplejavagui.utils;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public final class Alerts {

    public static void showSuccessAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успешно");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.initStyle(StageStyle.UTILITY);

        alert.showAndWait();
    }

    public static void showErrorAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.initStyle(StageStyle.UTILITY);

        alert.showAndWait();
    }
}
