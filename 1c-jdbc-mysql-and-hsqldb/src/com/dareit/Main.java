package com.dareit;

import com.dareit.common.IDBApi;
import com.dareit.hsqldb.HSQLDBApi;
import com.dareit.mysql.CommandLineReader;
import com.dareit.mysql.MySQLApi;

import java.util.Scanner;

import static com.dareit.hsqldb.HSQLDBApi.startServer;


public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        DatabaseSelection databaseSelection = getDatabaseSelection();

        IDBApi dbApi = getDBApi(databaseSelection);
    }

    private static IDBApi getDBApi(DatabaseSelection databaseSelection) {
        switch (databaseSelection) {
            case MySQL:
                return MySQLApi.getInstance(CommandLineReader.readyMySQLConnectionFromCmdLine());
            case HSQLDB:
                startServer();
                return HSQLDBApi.getInstance();
            default: throw new IllegalArgumentException();
        }
    }

    private static DatabaseSelection getDatabaseSelection() {
        System.out.println(
                "Type '"
                + DatabaseSelection.MySQL.label
                + "' for "
                + DatabaseSelection.MySQL.name()
                + " or '"
                + DatabaseSelection.HSQLDB.label
                + "' for "
                + DatabaseSelection.HSQLDB.name()
        );

        return DatabaseSelection.valueOfLabel(SCANNER.next());
    }

}
