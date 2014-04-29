package com.olukelom.gamestat.model;

public class GameMode {
    int id;
    String name;
    String description;

    public GameMode() {
    }

    public GameMode(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
