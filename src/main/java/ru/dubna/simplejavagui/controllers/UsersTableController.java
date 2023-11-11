package ru.dubna.simplejavagui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ru.dubna.simplejavagui.db.DBManager;
import ru.dubna.simplejavagui.entities.User;
import ru.dubna.simplejavagui.store.Store;

import java.io.IOException;
import java.sql.SQLException;

public class UsersTableController {
    @FXML
    private Button ButtonGoToUserCreate;

    @FXML
    private TableColumn<User, String> TableColumnLogin;

    @FXML
    private TableColumn<User, String> TableColumnPassword;

    @FXML
    private TableColumn<User, Boolean> TableColumnIsAdmin;

    @FXML
    private TableView<User> TableViewUsers;

    @FXML
    void GoToUserCreate(ActionEvent event) throws IOException {
        new SceneController().switchToCreateUserScene(event);
    }

    @FXML
    public void initialize() throws SQLException {
        showUsers();
    }

    @FXML
    void ShowUser(MouseEvent event) throws IOException {
        Store.user = TableViewUsers.getSelectionModel().getSelectedItem();
        new SceneController().switchToUpdateUserScene(event);
    }

    private void showUsers() throws SQLException {
        ObservableList<User> list = getUserList();

        TableColumnLogin.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        TableColumnPassword.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        TableColumnIsAdmin.setCellValueFactory(new PropertyValueFactory<User, Boolean>("isAdmin"));

        TableViewUsers.setItems(list);
    }

    public ObservableList<User> getUserList() throws SQLException {
        ObservableList<User> userList = FXCollections.observableArrayList();

        var allUsers = DBManager.getAllUsers();
        while (allUsers.next()) {
            var user = new User
                    (
                            allUsers.getInt("id"),
                            allUsers.getString("login"),
                            allUsers.getString("password"),
                            allUsers.getBoolean("is_admin")
                    );
            userList.add(user);
        }
        return userList;
    }
}