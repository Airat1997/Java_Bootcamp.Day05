package edu.school21.chat.app;


import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;

public class Program {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = null;
        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(dataSource);
        System.out.println(usersRepositoryJdbc.findAll(0, 10).toString());
    }

}
