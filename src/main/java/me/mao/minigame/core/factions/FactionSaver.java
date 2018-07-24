package me.mao.minigame.core.factions;

import me.mao.minigame.core.Core;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FactionSaver extends BukkitRunnable {

    private Core core;

    private Faction faction;

    private String UPDATE;

    public FactionSaver(Core core, Faction faction) {
        this.core = core;
        this.faction = faction;
        this.UPDATE  = "UPDATE " + core.getInitializer().getFactions_data() + " SET name=?, description=?, power=?, balance=? WHERE name=?";
    }

    public void run() {

        Connection connection = core.getInitializer().getConnection();

        try {
            if(connection.isClosed()) {
                core.getInitializer().getMySQL().openConnection();
            }
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, faction.getName());
            preparedStatement.setString(2, faction.getName());
            preparedStatement.setString(3, faction.getDescription());
            preparedStatement.setInt(4, faction.getPower());
            preparedStatement.setInt(5, faction.getBalance());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Faction getFaction() {
        return faction;
    }
}


