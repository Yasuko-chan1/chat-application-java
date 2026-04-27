package database;

import model.Message;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/chat_db";
    private static final String USER = "root";
    private static final String PASS = "project";

    // 1. Метод для создания соединения
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Драйвер не найден!", e);
        }
    }

    // --- НОВЫЙ МЕТОД ДЛЯ ПОДРУГИ (ВАЛИДАЦИЯ) ---
    public static boolean validateUser(String login, String password) {
        // Вызываем твой рабочий метод checkLogin
        return checkLogin(login, password);
    }

    // 2. Метод для сохранения сообщения
    public static void saveMessage(Message msg) {
        String sql = "INSERT INTO messages (sender_name, content) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, msg.getSender());
            pstmt.setString(2, msg.getContent());
            pstmt.executeUpdate();

            System.out.println("Сообщение от " + msg.getSender() + " сохранено в базу.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Метод для получения истории сообщений
    public static List<Message> getChatHistory() {
        List<Message> history = new ArrayList<>();
        String sql = "SELECT sender_name, content FROM messages ORDER BY timestamp ASC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Message msg = new Message(
                        rs.getString("sender_name"),
                        rs.getString("content")
                );
                history.add(msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    // 4. Метод для проверки входа (авторизация)
    public static boolean checkLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // true, если пользователь найден

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5. Твой проверочный main
    public static void main(String[] args) {
        try {
            Connection con = getConnection();
            if (con != null) {
                System.out.println("---------------------------------");
                System.out.println("УСПЕХ: База данных подключена!");
                System.out.println("---------------------------------");

                // Проверяем через новый метод validateUser
                if(validateUser("admin", "project")) {
                    System.out.println("Тестовый вход через validateUser: РАБОТАЕТ");
                } else {
                    System.out.println("Тестовый вход: ОШИБКА (проверь данные в MySQL)");
                }

                con.close();
            }
        } catch (SQLException e) {
            System.out.println("ОШИБКА: " + e.getMessage());
        }
    }
}