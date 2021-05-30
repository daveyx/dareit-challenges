package com.dareit;

import com.dareit.common.CustomerService;
import com.dareit.common.ICustomerDBApi;
import com.dareit.hsqldb.HSQLDBApi;
import com.dareit.mysql.CommandLineReader;
import com.dareit.mysql.MySQLApi;
import org.hsqldb.server.Server;

import java.util.Scanner;

import static com.dareit.hsqldb.HSQLDBApi.startServer;


public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static Server HSQLDB_SERVER;


    public static void main(String[] args) {
        System.out.println("DareIT challenge 1c - JDBC with HSQLDB");
        DatabaseSelection databaseSelection = getDatabaseSelection();

        CustomerService customerService = new CustomerService(getDBApi(databaseSelection));
        customerService.process();

        if (HSQLDB_SERVER != null) {
            HSQLDB_SERVER.stop();
        }
    }

    private static ICustomerDBApi getDBApi(DatabaseSelection databaseSelection) {
        switch (databaseSelection) {
            case MySQL:
                return MySQLApi.getInstance(CommandLineReader.readyMySQLConnectionFromCmdLine());
            case HSQLDB:
                HSQLDB_SERVER = startServer();
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
