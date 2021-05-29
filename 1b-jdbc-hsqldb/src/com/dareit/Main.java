package com.dareit;

import com.dareit.common.Customer;
import com.dareit.common.CustomerDataReader;
import com.dareit.hsqldb.HSQLDBApi;
import org.hsqldb.server.Server;

import static com.dareit.hsqldb.HSQLDBApi.startServer;


public class Main {

    public static void main(String[] args) {
        System.out.println("DareIT challenge 1 - JDBC with HSQLDB");

        Server server = startServer();

        HSQLDBApi hsqldbApi = HSQLDBApi.getInstance();

        Customer newCustomer = CustomerDataReader.readCustomerDataFromCmdLine();

        hsqldbApi.createCustomer(newCustomer);

        System.out.println("Data from table " + Customer.class.getSimpleName() + ":");
        hsqldbApi.readCustomers().forEach(customer -> System.out.println(customer.getFirstName() + " " + customer.getLastName()));

        HSQLDBApi.startDatabaseManager();

        server.stop();
    }

}
