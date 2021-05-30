package com.dareit.common;

import java.util.List;


public interface ICustomerDBApi {

    void createCustomer(Customer customer);
    List<Customer> readCustomers();

}
