package edu.school21.chat.app;

import edu.school21.chat.models.Chat;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import javax.sql.DataSource;

public class Program {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = null;
        MessagesRepositoryJdbcImpl messagesRepositoryJdbc = new MessagesRepositoryJdbcImpl(
                dataSource);
        User creator = new User(1L, "user0", "1", new ArrayList<>(), new ArrayList<>());
        Chat room = new Chat(1L, "Java", creator, new ArrayList());
        Message message = new Message(null, creator, room, "Hello!", Date.valueOf(LocalDate.now()));
        messagesRepositoryJdbc.save(message);
        System.out.println(message.getId());
    }

}
