package client;

import database.DBConnection;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginWindow {
    private Stage stage;

    public void show() {
        stage = new Stage();
        stage.setTitle("Вход в систему");

        // Элементы интерфейса
        Label titleLabel = new Label("Авторизация");
        TextField loginField = new TextField();
        loginField.setPromptText("Введите логин");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Введите пароль");

        Button loginButton = new Button("Войти");
        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;"); // Красный текст для ошибок

        // Логика кнопки (связываем с контроллером)
        loginButton.setOnAction(e -> {
            String login = loginField.getText();
            String password = passwordField.getText();

            // Здесь вызываем проверку из базы данных (написанную подругой)
            if (DBConnection.validateUser(login, password)) {
                stage.close(); // Закрываем окно входа
                ChatWindow chatWindow = new ChatWindow();
                Stage chatStage = new Stage();
                chatWindow.start(chatStage); // Открываем основное окно чата
            } else {
                errorLabel.setText("Неверный логин или пароль!");
            }
        });

        // Компоновка
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new javafx.geometry.Insets(20));
        layout.getChildren().addAll(titleLabel, loginField, passwordField, loginButton, errorLabel);

        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.show();
    }
}