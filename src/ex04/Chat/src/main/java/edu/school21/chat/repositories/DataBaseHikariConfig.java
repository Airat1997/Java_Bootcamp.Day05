package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;

class DataBaseHikariConfig {

    public static HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/java_05");
        config.setUsername("wilmerbl");
        config.setPassword("1");
        return new HikariDataSource(config);
    }
}
