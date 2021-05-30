package com.dareit.mysql;

import java.util.Scanner;

public abstract class CommandLineReader {

    private static final Scanner SCANNER = new Scanner(System.in);

    private CommandLineReader() { }

    public static MySQLConnection readyMySQLConnectionFromCmdLine() {
        System.out.println("Please provide mysql connection");

//        System.out.println("server");
//        final String server = SCANNER.next();
//        System.out.println("user");
//        final String user = SCANNER.next();
//        System.out.println("password");
//        final String password = SCANNER.next();
//        System.out.println("database");
//        final String database = SCANNER.next();
//        System.out.println("table");
//        final String table = SCANNER.next();
//
//        return new MySQLConnection(server, user, password, database, table);
        return new MySQLConnection("localhost", "kevin", "kevin", "test", "Customer");
    }

}
