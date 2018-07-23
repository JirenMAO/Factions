package me.mao.minigame.core.factions;

import me.mao.minigame.core.Core;
import me.mao.minigame.core.factions.util.FactionPlayer;
import me.mao.minigame.core.factions.util.FactionRank;
import me.mao.minigame.core.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.UUID;

public class FactionLoader extends BukkitRunnable {

    private Core core;

    private Faction faction;

    private String INSERT;
    private String SELECT;
    private String SELECT2;

    public FactionLoader(Core core, Faction faction) {
        this.core = core;
        this.faction = faction;
        this.INSERT = "INSERT INTO " + core.getInitializer().getFactions_data() + " VALUES(?, ?, ?, ?) ON DUPLICATE KEY UPDATE name=?";
        this.SELECT = "SELECT * FROM " + core.getInitializer().getFactions_data() + " WHERE name=?";
        this.SELECT2 = "SELECT * FROM " + core.getInitializer().getFactions_data() + " WHERE faction=?";
    }

    public void run() {

        Connection connection = core.getInitializer().getConnection();

        try {
            if(connection.isClosed()) {
                core.getInitializer().getMySQL().openConnection();
            }

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setString(1, faction.getName());
            ResultSet results = preparedStatement.executeQuery();

            if(results.next()) {
                faction.setName(results.getString("name"));
                faction.setDescription(results.getString("description"));
                faction.setPower(results.getInt("power"));
                faction.setBalance(results.getInt("balance"));
                preparedStatement.close();
                results.close();
                return;
            }

            preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setString(1, faction.getName());
            results = preparedStatement.executeQuery();

            if(results.next()) {

                faction.getMembers().add(
                        new FactionPlayer(UUID.fromString(
                                results.getString("uuid")),
                                results.getInt("balance"),
                                faction, FactionRank.valueOf(results.getString("position").toUpperCase())));

            }

            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, faction.getName().toString());
            preparedStatement.setString(2, faction.getName());
            preparedStatement.setString(3, faction.getDescription());

            int power = 0;
            int balance = 0;

            for(FactionPlayer player : faction.getMembers()) {
                power += 20;
                balance += player.getBalance();
            }

            preparedStatement.setInt(4, power);
            preparedStatement.setInt(5, balance);
            preparedStatement.execute();

            ChatUtils.dataInfo("NEW FACTION ADDED WITH THE NAME: " + faction.getName());

            preparedStatement.close();
            results.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
