package client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatWindow extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextArea chatArea = new TextArea();
        chatArea.setEditable(false);
        TextField inputField = new TextField();
        Button sendButton = new Button("Send");

        VBox root = new VBox(10, chatArea, inputField, sendButton);
        Scene scene = new Scene(root, 400, 300);
        
        primaryStage.setTitle("Java Chat - Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
