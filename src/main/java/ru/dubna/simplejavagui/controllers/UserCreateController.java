package ru.dubna.simplejavagui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.dubna.simplejavagui.db.DBManager;
import ru.dubna.simplejavagui.utils.Alerts;

import java.io.IOException;
import java.sql.SQLException;

public class UserCreateController {

    @FXML
    private Button ButtonGoToUsersTable;

    @FXML
    private Button ButtonUserCreate;

    @FXML
    private CheckBox CheckboxIsAdmin;

    @FXML
    private TextField InputFieldLogin;

    @FXML
    private PasswordField InputFieldPassword;

    @FXML
    void GoToUsersTable(ActionEvent event) throws IOException {
        new SceneController().switchToShowUsersScene(event);
    }

    @FXML
    void UserCreate(ActionEvent event) throws SQLException, IOException {
        var login = InputFieldLogin.getText().trim();
        var password = InputFieldPassword.getText().trim();
        var isAdmin = CheckboxIsAdmin.isSelected();

        if (login.equals("") || password.equals("")) {
            Alerts.showErrorAlert("Введите логин/пароль!");
            return;
        }

        var oldUser = DBManager.getUser(login);

        if (oldUser.next()) {
            Alerts.showErrorAlert("Логин занят!");
            return;
        }

        DBManager.createUser(InputFieldLogin.getText().trim(), InputFieldPassword.getText().trim(), isAdmin);
        new SceneController().switchToShowUsersScene(event);
        Alerts.showSuccessAlert("Пользователь создан!");
    }

}
