package com.dareit;

import com.dareit.common.CustomerService;
import com.dareit.common.ICustomerDBApi;
import com.dareit.hsqldb.HSQLDBApi;
import org.hsqldb.server.Server;

import static com.dareit.hsqldb.HSQLDBApi.startServer;


public class Main {

    public static void main(String[] args) {
        System.out.println("DareIT challenge 1 - JDBC with HSQLDB");

        Server server = startServer();
        HSQLDBApi.startDatabaseManager();

        CustomerService customerService = new CustomerService(getDBApi());
        customerService.process();

        server.stop();
    }


    private static ICustomerDBApi getDBApi() {
        return HSQLDBApi.getInstance();
    }

}
