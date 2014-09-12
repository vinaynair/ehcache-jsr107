package org.terracotta.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;

/**
 * Created by vinay on 9/11/14.
 */
@Component("customerService")
@CacheDefaults(cacheName="customers")
public class CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);
    @Resource
    private CustomerDataService customerDataServce;


    @CacheResult
    public Customer addCustomer(@CacheKey String customerID, @CacheValue Customer customer) {
        LOG.info("addCustomer(" + customerID + "," + customer + ")");
        customerDataServce.save(customerID, customer);
        return customer;
    }

    public Customer getCustomer(@CacheKey String customerID) {
        LOG.info("getCustomer(" + customerID + ")");
        Customer customer = customerDataServce.load(customerID);
        LOG.info("getCustomer.return=" + customer);
        return customer;
    }
}
