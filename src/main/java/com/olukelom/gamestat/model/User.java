package com.olukelom.gamestat.model;

public class User {
    private Integer id;
    private String login;
    private String password;
    private boolean isAdmin;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdminRights(boolean hasAdminRights) {
        this.isAdmin = hasAdminRights;
    }

    public boolean isNotAnonymous() {
        return id != null && login!=null;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
