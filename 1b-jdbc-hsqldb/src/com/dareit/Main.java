package com.dareit;

import com.dareit.common.Customer;
import com.dareit.hsqldb.HSQLDBApi;
import org.hsqldb.server.Server;
import org.hsqldb.util.DatabaseManagerSwing;


public class Main {

    public static void main(String[] args) {
        System.out.println("DareIT challenge 1 - JDBC with HSQLDB");

        Server server = startServer();

        HSQLDBApi HSQLDBApi = HSQLDBApi.getInstance();

        Customer newCustomer = new Customer(
                "petr",
                "poter"
        );

        HSQLDBApi.createCustomer(newCustomer);

        System.out.println("Data from table " + Customer.class.getSimpleName() + ":");
        HSQLDBApi.readCustomers().forEach(customer -> System.out.println(customer.getFirstName() + " " + customer.getLastName()));

        DatabaseManagerSwing.main(new String[]{
                "--url", "jdbc:hsqldb:mem:dareitdb", "--noexit"
        });

        server.stop();
    }

    private static Server startServer() {
        Server server = new Server();
        server.setDatabasePath(0, "mem:dareitdb");
        server.setDatabaseName(0, "dareitdb");
        server.setLogWriter(null);
        server.setErrWriter(null);
        server.setSilent(true);
        server.setRestartOnShutdown(false);
        server.start();

        return server;
    }

}
