package com.dareit.mysql;


public class MySQLConnection {

    private final String server;
    private final String user;
    private final String password;
    private final String database;
    private final String table;


    public String getServer() {
        return server;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

    public String getTable() {
        return table;
    }


    public MySQLConnection(String server,
                           String user,
                           String password,
                           String database,
                           String table) {
        this.server = server;
        this.user = user;
        this.password = password;
        this.database = database;
        this.table = table;
    }

    @Override
    public String toString() {
        return "MySQLConnection {" +
                "server=" + server +
                ", user=" + user +
                ", password=" + password +
                ", database=" + database +
                ", table=" + table +
                "}";
    }

}
