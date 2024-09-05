package edu.school21.chat.models.app;

import edu.school21.chat.models.repositories.MessagesRepositoryJdbcImpl;
import java.sql.SQLException;
import java.util.Scanner;
import javax.sql.DataSource;

public class Program {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = null;
        MessagesRepositoryJdbcImpl messagesRepositoryJdbc = new MessagesRepositoryJdbcImpl(dataSource);
        Scanner console = new Scanner(System.in);
        System.out.println("Enter a message ID");
        long id = console.nextLong();
        System.out.println(messagesRepositoryJdbc.findById(id));
    }

}
