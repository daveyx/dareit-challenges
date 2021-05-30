package com.dareit;

import com.dareit.common.Customer;
import com.dareit.common.CustomerDataReader;
import com.dareit.mysql.CommandLineReader;
import com.dareit.mysql.MySQLApi;
import com.dareit.mysql.MySQLConnection;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        System.out.println("DareIT challenge 1 - JDBC with MySQL");

        MySQLConnection mySQLConnection = CommandLineReader.readyMySQLConnectionFromCmdLine();
        MySQLApi mySQLApi = MySQLApi.getInstance(mySQLConnection);

        Customer customerToCreate = CustomerDataReader.readCustomerDataFromCmdLine();
        mySQLApi.createCustomer(customerToCreate);

        List<Customer> customerRead = mySQLApi.readCustomers();
        System.out.println("Data from table " + mySQLConnection.getTable() + ":");
        customerRead.forEach(customer -> System.out.println(customer.getFirstName() + " " + customer.getLastName()));
    }

}
