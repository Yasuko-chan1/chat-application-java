CREATE DATABASE IF NOT EXISTS chat_db;
USE chat_db;


CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
    );


CREATE TABLE IF NOT EXISTS messages (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        sender_name VARCHAR(50),
    content TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );


INSERT IGNORE INTO users (username, password) VALUES ('admin', 'project');