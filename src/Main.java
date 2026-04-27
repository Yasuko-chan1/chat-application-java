import client.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Мы говорим программе: "При старте создай и покажи окно входа"
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.show();
    }

    public static void main(String[] args) {
        // Эта команда запускает весь механизм JavaFX
        launch(args);
    }
}