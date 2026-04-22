package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextArea chatArea = new TextArea();
        chatArea.setEditable(false); // Чтобы нельзя было стирать чужие сообщения

        TextField inputField = new TextField();
        inputField.setPromptText("Введите сообщение...");

        Button sendButton = new Button("Send");

        // ЛОГИКА КНОПКИ:
        sendButton.setOnAction(e -> {
            String message = inputField.getText();
            if (!message.isEmpty()) {
                chatArea.appendText("Вы: " + message + "\n");
                inputField.clear(); // Очищаем поле после отправки
            }
        });

        VBox root = new VBox(10, chatArea, inputField, sendButton);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Java Chat - Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}