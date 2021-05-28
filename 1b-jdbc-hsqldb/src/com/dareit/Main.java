package com.dareit;

import com.dareit.common.Customer;
import com.dareit.common.CustomerDataReader;
import com.dareit.hsqldb.HSQLDBApi;
import org.hsqldb.server.Server;
import org.hsqldb.util.DatabaseManagerSwing;

import java.awt.*;

import static com.dareit.hsqldb.HSQLDBApi.startServer;


public class Main {

    static {
        try {
            GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
            DatabaseManagerSwing.main(new String[]{
                    "--url", "jdbc:hsqldb:mem:dareitdb", "--noexit"
            });
        } catch (UnsatisfiedLinkError e) {
            System.err.println("can't open databasemanager, no graphic device available");
        }
    }

    public static void main(String[] args) {
        System.out.println("DareIT challenge 1 - JDBC with HSQLDB");

        Server server = startServer();

        HSQLDBApi hsqldbApi = HSQLDBApi.getInstance();

        Customer newCustomer = CustomerDataReader.readCustomerDataFromCmdLine();

        hsqldbApi.createCustomer(newCustomer);

        System.out.println("Data from table " + Customer.class.getSimpleName() + ":");
        hsqldbApi.readCustomers().forEach(customer -> System.out.println(customer.getFirstName() + " " + customer.getLastName()));

        server.stop();
    }

}
