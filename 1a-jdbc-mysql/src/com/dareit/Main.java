package com.dareit;

import com.dareit.common.CustomerService;
import com.dareit.common.ICustomerDBApi;
import com.dareit.mysql.CommandLineReader;
import com.dareit.mysql.MySQLApi;


public class Main {

    public static void main(String[] args) {
        System.out.println("DareIT challenge 1a - JDBC with MySQL");

        CustomerService customerService = new CustomerService(getDBApi());
        customerService.process();
    }

    private static ICustomerDBApi getDBApi() {
        return MySQLApi.getInstance(CommandLineReader.readyMySQLConnectionFromCmdLine());
    }

}
