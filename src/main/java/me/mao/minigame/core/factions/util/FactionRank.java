package me.mao.minigame.core.factions.util;


public enum FactionRank {


    LEADER("Leader", "&7Leader", 100),
    ADMIN("Admin", "&7Admin", 80),
    RECRUITER("Recruiter", "&7Recruiter", 40),
    MEMEBER("Member", "&7Member", 20);

    private final int MAX_POWER = 100;

    private String name, prefix;

    private int power;

     FactionRank(String name, String prefix, int power) {
        this.name = name;
        this.prefix = prefix;
        this.power = power;
    }

    public int getMAX_POWER() {
        return MAX_POWER;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPowerPercentage() {
        return power*100/getMAX_POWER();
    }
}
