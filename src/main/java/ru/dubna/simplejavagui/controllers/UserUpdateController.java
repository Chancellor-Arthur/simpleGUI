package ru.dubna.simplejavagui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.dubna.simplejavagui.db.DBManager;
import ru.dubna.simplejavagui.entities.User;
import ru.dubna.simplejavagui.store.Store;
import ru.dubna.simplejavagui.utils.Alerts;

import java.io.IOException;
import java.sql.SQLException;

public class UserUpdateController {

    @FXML
    private Button ButtonGoToUsersTable;

    @FXML
    private Button ButtonUserUpdate;

    @FXML
    private Button ButtonUserDelete;

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
    public void initialize() {
        var user = Store.user;
        if (user != null) fillFields(user);
    }

    void fillFields(User user) {
        InputFieldLogin.setText(user.getLogin());
        InputFieldPassword.setText(user.getPassword());
        CheckboxIsAdmin.setSelected(user.getIsAdmin());
    }

    @FXML
    void UserUpdate(ActionEvent event) throws SQLException, IOException {
        var login = InputFieldLogin.getText().trim();
        var password = InputFieldPassword.getText().trim();
        var isAdmin = CheckboxIsAdmin.isSelected();

        if (login.isEmpty() || password.isEmpty()) {
            Alerts.showErrorAlert("Введите логин/пароль!");
            return;
        }

        var oldUser = DBManager.getUser(login, Store.user.getId());

        if (oldUser.next()) {
            Alerts.showErrorAlert("Логин занят!");
            return;
        }

        DBManager.updateUser(InputFieldLogin.getText().trim(), InputFieldPassword.getText().trim(), isAdmin, Store.user.getId());
        new SceneController().switchToShowUsersScene(event);
        Alerts.showSuccessAlert("Пользователь обновлён!");
    }

    @FXML
    void UserDelete(ActionEvent event) throws IOException, SQLException {
        DBManager.removeUser(Store.user.getId());

        new SceneController().switchToShowUsersScene(event);
        Alerts.showSuccessAlert("Пользователь успешно удален!");
    }
}
