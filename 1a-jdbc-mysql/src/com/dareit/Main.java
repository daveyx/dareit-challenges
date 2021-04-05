package com.dareit;

import com.dareit.common.Customer;
import com.dareit.common.CustomerDataReader;
import com.dareit.mysql.MySQLApi;
import com.dareit.mysql.MySQLConnection;

import java.util.List;
import java.util.Scanner;


public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("DareIT challenge 1 - JDBC with MySQL");

        MySQLConnection mySQLConnection = readyMySQLConnectionFromCmdLine();
        MySQLApi mySQLApi = MySQLApi.getInstance(mySQLConnection);

        Customer customerToCreate = CustomerDataReader.readCustomerDataFromCmdLine();
        mySQLApi.createCustomer(customerToCreate);

        List<Customer> customerRead = mySQLApi.readCustomers();
        System.out.println("Data from table " + mySQLConnection.getTable() + ":");
        customerRead.forEach(customer -> System.out.println(customer.getFirstName() + " " + customer.getLastName()));
    }

    private static MySQLConnection readyMySQLConnectionFromCmdLine() {
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
