package com.olukelom.gamestat.model;

public class GameServer {
    private int id;
    private String name;
    private GameLevel level;
    private GameMode mode;
    private String description;
    private boolean isOnline;
    private boolean isProtected;

    public GameServer() {
        this(false);
    }

    public GameServer(boolean isProtected) {
        this.isProtected = isProtected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameLevel getLevel() {
        return level;
    }

    public void setLevel(GameLevel level) {
        this.level = level;
    }

    public GameMode getMode() {
        return mode;
    }

    public void setMode(GameMode mode) {
        this.mode = mode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public boolean isSecured() {
        return isProtected;
    }

    public void setStatus(boolean isOnline) {
        this.isOnline = isOnline;
    }
}
