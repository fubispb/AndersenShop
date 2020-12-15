package service;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class ConnectBaseService {

    private Connection connection;
    private Statement statement;
    private DataSource dataSource;

    public ConnectBaseService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void connect() throws SQLException {
        connection = dataSource.getConnection();
        statement = connection.createStatement();
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Start log. " + e);
        }
    }
}
