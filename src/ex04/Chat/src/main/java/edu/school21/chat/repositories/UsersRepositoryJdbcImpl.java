package edu.school21.chat.repositories;

import edu.school21.chat.models.Chat;
import edu.school21.chat.models.User;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.sql.DataSource;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> findAll(int page, int size) throws SQLException {
        dataSource = DataBaseHikariConfig.createDataSource();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String sqlQuery = "WITH visited_chats AS (\n" +
                "    SELECT u.*, ARRAY_AGG(DISTINCT m.chat_id) AS visited_chat_ids\n" +
                "    FROM users u\n" +
                "    JOIN messages m ON m.author_id = u.id\n" +
                "    GROUP BY u.id\n" +
                "),\n" +
                "created_chats AS (\n" +
                "    SELECT u.*, ARRAY_AGG(DISTINCT ch.id) AS created_chat_ids\n" +
                "    FROM users u\n" +
                "    JOIN chat ch ON ch.owner_id = u.id\n" +
                "    GROUP BY u.id\n" +
                "),\n" +
                "created_chats_name AS (\n" +
                "    SELECT cc.id, unnest(cc.created_chat_ids) AS created_chat_id\n" +
                "    FROM created_chats cc\n" +
                "),\n" +
                "visited_chats_name AS (\n" +
                "    SELECT vc.id, unnest(vc.visited_chat_ids) AS visited_chat_id\n" +
                "    FROM visited_chats vc\n" +
                ")\n" +
                "SELECT DISTINCT\n" +
                "vc.id, \n" +
                "vc.login, \n" +
                "vc.password, \n" +
                "vcn.visited_chat_id,\n" +
                "ccn.created_chat_id,\n" +
                "c.name AS created_chats_name,\n" +
                "cz.name AS visited_chats_name\n" +
                "FROM visited_chats vc\n" +
                "LEFT JOIN created_chats_name ccn ON vc.id = ccn.id\n" +
                "LEFT JOIN visited_chats_name vcn ON vc.id = vcn.id\n" +
                "JOIN chat c ON ccn.created_chat_id = c.id\n" +
                "JOIN chat cz ON vcn.visited_chat_id = cz.id\n" +
                "ORDER BY vc.id\n" +
                "LIMIT +" + size + " OFFSET " + page + ";";

        ResultSet resultSet = statement.executeQuery(sqlQuery);
        List<Chat> createdRooms = new ArrayList<>();
        List<Chat> visitRooms = new ArrayList<>();
        List<User> users = new ArrayList<>();
        ArrayList<User> allUsers = new ArrayList<>();
        HashMap<Integer, String> createdRoomsName = new HashMap<>();
        HashMap<Integer, String> visitedRoomsName = new HashMap<>();

        while (resultSet.next()) {
            User user = new User(resultSet.getLong("id"), resultSet.getString("login"),
                    resultSet.getString("password"), new ArrayList<>(),
                    new ArrayList<>());
            allUsers.add(user);
            createdRoomsName.put(resultSet.getInt("created_chat_id"),
                    resultSet.getInt("id") + " " + resultSet.getString(
                            "created_chats_name"));
            visitedRoomsName.put(resultSet.getInt("id"),
                    resultSet.getInt("visited_chat_id") + " " + resultSet.getString(
                            "visited_chats_name"));
        }
        List<User> finalUser = allUsers.stream().distinct().collect(Collectors.toList());
        String c = createdRoomsName.toString();
        String v = visitedRoomsName.toString();
        String tempC = c.substring(1).substring(0, c.length() - 2);
        String tempV = v.substring(1).substring(0, v.length() - 2);
        String[] arrayC = tempC.split(", ");
        String[] arrayV = tempV.split(", ");
        for (int i = 0; i < finalUser.size(); i++) {
            for (int j = 0; j < arrayC.length; j++) {
                int index = arrayC[j].indexOf('=');
                int index2 = arrayC[j].indexOf(' ');
                if (finalUser.get(i).getId() == Long.parseLong(
                        arrayC[j].substring(index + 1, index2))) {
                    finalUser.get(i).getCreatedRooms()
                            .add(new Chat(Long.parseLong(arrayC[j].substring(0, index)),
                                    arrayC[j].substring(index2 + 1, arrayC[j].length()), null,
                                    null));
                }

            }
        }

        for (int i = 0; i < finalUser.size(); i++) {
            for (int j = 0; j < arrayV.length; j++) {
                int index = arrayV[j].indexOf('=');
                int index2 = arrayV[j].indexOf(' ');
                if (finalUser.get(i).getId() == Long.parseLong(arrayV[j].substring(0, index))) {
                    finalUser.get(i).getVisitRooms()
                            .add(new Chat(Long.parseLong(
                                    arrayV[j].substring(index + 1, index2)),
                                    arrayV[j].substring(index2 + 1, arrayV[j].length()), null,
                                    null));
                }
            }
        }

        return finalUser;

    }
}
