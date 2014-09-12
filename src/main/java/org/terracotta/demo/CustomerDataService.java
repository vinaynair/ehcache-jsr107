package org.terracotta.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by vinay on 9/11/14.
 */
@Component("customerDataService")
public class CustomerDataService {

    private HashMap<String, Customer> internalMap = new HashMap<String, Customer>();
    static final Logger LOG = LoggerFactory.getLogger(CustomerDataService.class);

    public void save(String customerID, Customer customer) {
        LOG.info("save(" + customerID + "," + customer + ")");
        internalMap.put(customerID, customer);
    }

    public Customer load(String customerID) {
        LOG.info("load(" + customerID + ")");
        Customer customer = internalMap.get(customerID);
        LOG.info("load.return =" + customer + "");
        return customer;
    }
}
