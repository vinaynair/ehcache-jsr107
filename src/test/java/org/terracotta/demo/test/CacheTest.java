package org.terracotta.demo.test;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.terracotta.demo.Customer;
import org.terracotta.demo.CustomerService;
import org.terracotta.demo.JCacheAppConfig;

import javax.annotation.Resource;
import javax.cache.Cache;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Created by vinay on 9/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:app-context.xml"})
@ContextConfiguration(classes = {JCacheAppConfig.class})
public class CacheTest {
    @Autowired
    CustomerService customerService;
    @Resource(name = "customersCache")
    Cache cache;


    @Test
    public void testCache() throws Exception {
        //invoke method that triggers a cache put
        Customer customer = new Customer("abc");
        customerService.addCustomer("1", customer);

        //assert cache basic get() call
        Assert.assertNotNull(cache.get("1"));
        Assert.assertEquals(customer, cache.get("1"));

        //test cache statistics after the get calls
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        boolean foundCacheStatisticsMbean = false;
        for (ObjectName bean : mBeanServer.queryNames(null, null)) {
            if (bean.getKeyProperty("type").equals("CacheStatistics")) {
                foundCacheStatisticsMbean = true;
                Assert.assertEquals(new Float(100), mBeanServer.getAttribute(bean,
                        "CacheHitPercentage"));
                break;
            }

        }
        Assert.assertTrue(foundCacheStatisticsMbean);

        //assert that the provider is indeed from EhCache
        Assert.assertEquals(org.ehcache.jcache.JCacheCachingProvider.class, cache.getCacheManager().getCachingProvider().getClass());

    }

}
