package me.mao.minigame.core.factions;

import me.mao.minigame.core.factions.util.FactionPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Faction {

    private String name, description;
    private int power, balance;

    private List<FactionPlayer> members;

    public Faction(String name, String description, int power, int balance) {
        this.name = name;
        this.description = description;
        this.power = power;
        this.balance = balance;
    }

    public Faction(ResultSet set) throws SQLException {
        this.name = set.getString("name");
        this.description = set.getString("description");
        this.power = set.getInt("power");
        this.balance = set.getInt("balance");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FactionPlayer> getMembers() {
        return members;
    }

    public void setMembers(List<FactionPlayer> members) {
        this.members = members;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
