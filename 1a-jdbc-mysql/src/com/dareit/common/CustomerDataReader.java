package com.dareit.common;

import java.util.Scanner;


public class CustomerDataReader {

    private static final Scanner SCANNER = new Scanner(System.in);


    public static Customer readCustomerDataFromCmdLine() {
        System.out.println("Please provide data to insert into the database");

        System.out.println("firstName");
        final String firstName = SCANNER.next();
        System.out.println("lastName");
        final String lastName = SCANNER.next();

        return new Customer(firstName, lastName);
    }

}
