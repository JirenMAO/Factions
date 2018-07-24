package me.mao.minigame.core.factions.util.collections;

import me.mao.minigame.core.Core;
import me.mao.minigame.core.factions.Faction;
import me.mao.minigame.core.factions.FactionLoader;
import me.mao.minigame.core.factions.FactionSaver;
import me.mao.minigame.core.utils.ChatUtils;
import org.bukkit.scheduler.BukkitTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Factions {

    private List<Faction> factions = new LinkedList<Faction>();
    private Core core;

    private String DELETE, SELECT, SELECT_ALL, COUNT;

    public Factions(Core core) {
        this.core = core;
        this.SELECT = "SELECT * FROM " + core.getInitializer().getFactions_data() + " WHERE name=?";
        this.DELETE = "DELETE FROM " + core.getInitializer().getFactions_data() + " WHERE name=?";
        this.SELECT_ALL = "SELECT 'name' FROM " + core.getInitializer().getFactions_data();
        this.COUNT = "SELECT COUNT('name') AS total FROM " + core.getInitializer().getFactions_data();
    }


    public void addFaction(Faction faction) {
        if (this.factions.contains(faction)) return;
        this.factions.add(faction);
    }

    public void removeFaction(Faction faction) {
        if (!this.factions.contains(faction)) return;
        this.factions.remove(faction);
    }

    public void deleteFaction(Faction faction) {
        if (faction == null) return;
        Connection con = core.getInitializer().getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(SELECT);
            preparedStatement.setString(1, faction.getName());
            preparedStatement.execute();

            ResultSet set = preparedStatement.executeQuery();

            if (!set.next()) {
                preparedStatement.close();
                set.close();
                ChatUtils.dataInfo("COULD NOT DELETE A FACTION WITH THE NAME: " + faction.getName() + " CAUSE: faction dosent exist in the table.");
                return;
            }

            preparedStatement = con.prepareStatement(DELETE);
            preparedStatement.setString(1, faction.getName());
            preparedStatement.execute();

            set = preparedStatement.executeQuery();

            if (set.next()) {
                preparedStatement.close();
                set.close();
                ChatUtils.dataInfo("DELETED A FACTION WITH THE NAME: " + faction.getName());
                return;
            }

            preparedStatement.close();

        } catch (SQLException e) {
            ChatUtils.dataInfo("COULD NOT EXECUTE THE QUERY: " + SELECT + " CAUSE: " + e.getMessage());
        }
    }

    public void loadFaction(Faction faction) {
        if (this.factions.contains(faction)) return;
        new FactionLoader(core, faction).runTaskAsynchronously(core);
        factions.add(faction);
    }

    public void loadFactions() {
        if(factions.isEmpty()) return;
        Connection con = core.getInitializer().getConnection();
        int count = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(COUNT);
            preparedStatement.execute();

            ResultSet set = preparedStatement.executeQuery();

            if (set.next()) {
                count = set.getInt("total");
            }

            preparedStatement = con.prepareStatement(SELECT_ALL);
            preparedStatement.execute();

            set = preparedStatement.executeQuery();
            if (set.next()) {
                loadFaction(new Faction(
                        set.getString("name"),
                        set.getString("description"),
                        set.getInt("power"),
                        set.getInt("balance")));
            }

            preparedStatement.close();
            set.close();

            ChatUtils.dataInfo("LOADED ALL FACTIONS! COUNT: " + count);

        } catch (SQLException e) {
            ChatUtils.dataInfo("COULD NOT EXECUTE THE QUERY: " + SELECT_ALL + " CAUSE: " + e.getMessage());
        }
    }

    public void saveFaction(Faction faction) {
        new FactionSaver(core, faction).runTaskAsynchronously(core);
    }

    public void saveFactions() {
        if(factions.isEmpty()) return;

        for(Faction f : factions) {
            new FactionSaver(core, f).runTaskAsynchronously(core);
        }

        ChatUtils.dataInfo("SAVED ALL FACTIONS! COUNT: " + factions.size());
    }

    public Faction getAndSaveFaction(String faction) {
        Faction f = factions.stream().filter(fac -> fac.getName().equals(faction)).findAny().orElse(null);
        if (f == null) return null;
        saveFaction(f);
        return f;
    }

    public Faction getAndRemoveFaction(Faction faction) {
        if (faction == null) return null;
        removeFaction(faction);
        return faction;
    }

    public void clearLoadedFactions() {
        factions.clear();
    }

    public List<Faction> getFactions() {
        return factions;
    }
}
