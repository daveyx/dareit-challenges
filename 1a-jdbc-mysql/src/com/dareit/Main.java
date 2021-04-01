package com.dareit;

import java.util.List;
import java.util.Scanner;


public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("DareIT challenge 1 - JDBC with MySQL");

        MySQLConnection mySQLConnection = readyMySQLConnectionFromCmdLine();
        MySQLApi mySQLApi = MySQLApi.getInstance(mySQLConnection);

        Customer customerToCreate = readCustomerDataFromCmdLine();
        mySQLApi.createCustomer(customerToCreate);

        List<Customer> customerRead = mySQLApi.readCustomers();
        System.out.println("Data from table " + mySQLConnection.table + ":");
        customerRead.forEach(customer -> System.out.println(customer.firstName + " " + customer.lastName));
    }

    private static Customer readCustomerDataFromCmdLine() {
        System.out.println("Please provide mysql connection");

        System.out.println("firstName");
        final String firstName = SCANNER.next();
        System.out.println("lastName");
        final String lastName = SCANNER.next();

        return new Customer(firstName, lastName);
    }

    private static MySQLConnection readyMySQLConnectionFromCmdLine() {
        System.out.println("Please provide mysql connection");

        System.out.println("server");
        final String server = SCANNER.next();
        System.out.println("user");
        final String user = SCANNER.next();
        System.out.println("password");
        final String password = SCANNER.next();
        System.out.println("database");
        final String database = SCANNER.next();
        System.out.println("table");
        final String table = SCANNER.next();

        return new MySQLConnection(server, user, password, database, table);
    }

}
