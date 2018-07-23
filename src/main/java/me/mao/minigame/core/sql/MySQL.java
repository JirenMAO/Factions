package me.mao.minigame.core.sql;

import me.mao.minigame.core.utils.ChatUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {


    private String user = "";
    private String database = "";
    private String password = "";
    private int port = 0;
    private String hostname = "";
    private Connection connection = null;

    public MySQL(String user, String database, String password, int port, String hostname) {
        this.user = user;
        this.database = database;
        this.password = password;
        this.port = port;
        this.hostname = hostname;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
            return connection;
        } catch (SQLException e) {
            ChatUtils.debug("Could not connect to MySQL server! Cause: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            ChatUtils.debug("JDBC Driver not found!");
        }
        return this.connection;
    }

    public void closeConnection() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;
    }

    public boolean checkConnection() {
        if (connection != null) return true;
        return false;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

}
