package com.dareit;

import com.dareit.common.Customer;
import com.dareit.hsqldb.Api;
import org.hsqldb.server.Server;
import org.hsqldb.util.DatabaseManagerSwing;

import java.util.Scanner;


public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("DareIT challenge 1 - JDBC with HSQLDB");

        Server server = startServer();

        Api api = Api.getInstance();

        Customer newCustomer = new Customer(
                "petr",
                "poter"
        );

        api.createCustomer(newCustomer);

        System.out.println("Data from table " + Customer.class.getSimpleName() + ":");
        api.readCustomers().forEach(customer -> System.out.println(customer.getFirstName() + " " + customer.getLastName()));

        DatabaseManagerSwing.main(new String[]{
                "--url", "jdbc:hsqldb:mem:dareitdb", "--noexit"
        });

        server.stop();
    }

    private static Server startServer() {
        Server server = new Server();
        server.setDatabasePath(0, "mem:dareitdb");
        server.setDatabaseName(0, "dareitdb");
        server.setLogWriter(null); // can use custom writer
        server.setErrWriter(null); // can use custom writer
        server.setSilent(true);
        server.setRestartOnShutdown(false);
        server.start();

        return server;
    }

}
