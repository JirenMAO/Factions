package me.mao.minigame.core.factions.util;

import me.mao.minigame.core.factions.Faction;

import java.util.UUID;

public class FactionPlayer {

    private UUID uuid;
    private int balance;
    private Faction faction;
    private FactionRank factionRank;


    public FactionPlayer(UUID uuid, int balance, Faction faction, FactionRank factionRank) {
        this.uuid = uuid;
        this.balance = balance;
        this.faction = faction;
        this.factionRank = factionRank;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public FactionRank getFactionRank() {
        return factionRank;
    }

    public void setFactionRank(FactionRank factionRank) {
        this.factionRank = factionRank;
    }
}
