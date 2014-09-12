package org.terracotta.demo;

import net.sf.ehcache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

/**
 * TODO: Not used. Using {@link org.terracotta.demo.JCacheAppConfig}
 * Created by vinay on 9/12/14.
 */
//@Configuration
//@EnableCaching
//@ComponentScan(basePackages = "org.terracotta.demo")
public class EhCacheAppConfig {

    @Bean(name = "cacheManager")
    public org.springframework.cache.ehcache.EhCacheCacheManager getCacheManager() {
        org.springframework.cache.ehcache.EhCacheCacheManager bean = new org.springframework.cache.ehcache.EhCacheCacheManager();
        bean.setCacheManager(getEhCacheManagerFactoryBean().getObject());
        return bean;
    }

    @Bean(name = "ehcache")
    public org.springframework.cache.ehcache.EhCacheManagerFactoryBean getEhCacheManagerFactoryBean() {
        org.springframework.cache.ehcache.EhCacheManagerFactoryBean bean = new org.springframework.cache.ehcache.EhCacheManagerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("/ehcache-jcache-one.xml"));
        return bean;
    }


    @Bean(name = "customersCache")
    public Cache getCustomersCache() {
        return getEhCacheManagerFactoryBean().getObject().getCache("customers");
    }


}
