package edu.school21.chat.models.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chat;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        dataSource = DataBaseHikariConfig.createDataSource();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        long idMessage = 0L;
        User userMessage = getUserFromResultSet(dataSource, connection, statement, id);
        Chat chatMessage = getChatFromResultSet(dataSource, connection, statement, id);
        String textMessage = "";
        Date dateMessage = null;
        String sqlQuery = "SELECT * FROM messages where id = " + id + ";";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        if (resultSet.next()) {
            idMessage = resultSet.getLong("id");
            textMessage = resultSet.getString("text");
            dateMessage = resultSet.getDate("date");
        }
        return Optional.of(new Message(idMessage, userMessage, chatMessage, textMessage, dateMessage));
    }

    private User getUserFromResultSet(DataSource dataSource, Connection connection,
            Statement statement, Long id) throws SQLException {
        String sqlQuery = "SELECT * FROM messages m \n" + "JOIN users ON m.author_id = users.id\n"
                + "WHERE m.id = " + id + ";";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        long userId = 0L;
        String login = "";
        String password = "";
        if (resultSet.next()) {
            userId = resultSet.getLong("id");
            login = resultSet.getString("login");
            password = resultSet.getString("password");
        }
        return new User(userId, login, password, null, null);
    }

    private Chat getChatFromResultSet(DataSource dataSource, Connection connection,
            Statement statement, Long id) throws SQLException {
        String sqlQuery =
                "SELECT * FROM messages m JOIN chat ON m.author_id = chat.id WHERE m.id = " + id
                        + ";";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        long chatId = 0L;
        String name = "";
        if(resultSet.next()){
            chatId = resultSet.getLong("id");
            name = resultSet.getString("name");
        }
        return new Chat(chatId, name, null, null);
    }
}
