package com.dareit.common;

import java.util.List;

public class CustomerService {

    private final ICustomerDBApi ICustomerDBApi;


    public CustomerService(ICustomerDBApi ICustomerDBApi) {
        this.ICustomerDBApi = ICustomerDBApi;
    }

    public void process() {
        Customer customerToCreate = CustomerDataReader.readCustomerDataFromCmdLine();
        ICustomerDBApi.createCustomer(customerToCreate);

        List<Customer> customerRead = ICustomerDBApi.readCustomers();
        System.out.println("Data from table:");
        customerRead.forEach(customer -> System.out.println(customer.getFirstName() + " " + customer.getLastName()));
    }

}
