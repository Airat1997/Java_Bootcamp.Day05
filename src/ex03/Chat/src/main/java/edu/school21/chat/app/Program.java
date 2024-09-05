package edu.school21.chat.app;


import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;

public class Program {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = null;
        MessagesRepositoryJdbcImpl messagesRepositoryJdbc = new MessagesRepositoryJdbcImpl(
                dataSource);
        Optional<Message> messageOptional = messagesRepositoryJdbc.findById(1L);
        System.out.println(messageOptional);
        if(messageOptional.isPresent()){
            Message message = messageOptional.get();
            message.setText("newText");
            message.setDate(null);
            messagesRepositoryJdbc.update(message);
        }
        System.out.println(messageOptional);
    }

}
