package com.dareit;


public class MySQLConnection {

    final String server;
    final String user;
    final String password;
    final String database;
    final String table;


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
                "user=" + user +
                "password=" + password +
                "database=" + database +
                "table=" + table +
                "}";
    }

}
