package me.mao.minigame.core.sql;

import me.mao.minigame.core.Core;
import me.mao.minigame.core.utils.ChatUtils;
import me.mao.minigame.core.utils.FileBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Initializer {

    private long time = System.currentTimeMillis();

    private Core core;
    private MySQL mySQL;

    private FileBuilder data;

    private String player_data = "";
    private String factions_data = "";

    private boolean usesSql = false;

    public Initializer(Core core) {
        this.core = core;
        this.data = new FileBuilder(core.getDataFolder().getPath(), "Mysql");

        if (!data.exists()) {
            this.usesSql = false;
            data.set("mysql.username", "");
            data.set("mysql.password", "");
            data.set("mysql.database", "");
            data.set("mysql.hostname", "");
            data.set("mysql.player_data_table", "");
            data.set("mysql.factions_data_table", "");
            data.set("mysql.port", 0);
            data.save();
            return;
        }

        if (data.getKeys(false).isEmpty()) {
            this.usesSql = false;
            data.set("mysql.username", "");
            data.set("mysql.password", "");
            data.set("mysql.database", "");
            data.set("mysql.hostname", "");
            data.set("mysql.player_data_table", "");
            data.set("mysql.factions_data_table", "");
            data.set("mysql.port", 0);

            return;
        }


        init();

    }

    public void init() {
        mySQL = new MySQL(
                data.getString("mysql.username"),
                data.getString("mysql.database"),
                data.getString("mysql.password"),
                data.getInt("mysql.port"),
                data.getString("mysql.hostname"));
        mySQL.openConnection();

        this.player_data = data.getString("mysql.player_data_table");
        this.factions_data = data.getString("mysql.factions_data_table");

        if(mySQL.getConnection() != null) {
            try {
                Connection connection = mySQL.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("" + "CREATE TABLE IF NOT EXISTS `" + getPlayer_data() +
                        "` (`uuid` varchar(36) NOT NULL," +
                        " `name` varchar(16) NOT NULL," +
                        " `faction` varchar(16) NOT NULL," +
                        " `balance` int(11) NOT NULL," +
                        " `points` int(11) NOT NULL, PRIMARY KEY (`uuid`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
                preparedStatement.execute();
                preparedStatement.close();

                PreparedStatement preparedStatement2 = connection.prepareStatement("" + "CREATE TABLE IF NOT EXISTS `" + getFactions_data() +
                        "` (`name` varchar(36) NOT NULL," +
                        " `description` varchar(16) NOT NULL," +
                        " `balance` int(11) NOT NULL," +
                        " `power` int(11) NOT NULL, PRIMARY KEY (`name`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
                preparedStatement2.execute();
                preparedStatement2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (!mySQL.checkConnection()) return;
        ChatUtils.debug("MySQL Connection has been opend succesufly! {time estimated: " + (System.currentTimeMillis() - time) + " ms}");

    }

    public void close() {
        if (!usesSql) {
            return;
        }
        if (mySQL != null) {
            mySQL.closeConnection();
            ChatUtils.debug("MySQL Connection has been closed succesufly!");
        }

    }

    public Connection getConnection() {
        if (!usesSql) {
            return null;
        }
        if (mySQL == null) {
            return null;
        }

        if (!mySQL.checkConnection()) {
            return null;
        }

        return mySQL.getConnection();
    }


    public FileBuilder getData() {
        return data;
    }

    public boolean usesSql() {
        return usesSql;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public String getPlayer_data() {
        return player_data;
    }

    public void setPlayer_data(String player_data) {
        this.player_data = player_data;
    }

    public String getFactions_data() {
        return factions_data;
    }

    public void setFactions_data(String factions_data) {
        this.factions_data = factions_data;
    }
}
