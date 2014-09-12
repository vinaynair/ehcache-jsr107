package org.terracotta.demo;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.cache.Cache;

/**
 * Created by vinay on 9/12/14.
 */
@Configuration
@EnableCaching
@ComponentScan(basePackages = "org.terracotta.demo")
public class JCacheAppConfig {

    @Bean(name = "cacheManager")
    public org.springframework.cache.jcache.JCacheCacheManager getJCacheManager() throws Exception {
        org.springframework.cache.jcache.JCacheCacheManager bean = new org.springframework.cache.jcache.JCacheCacheManager();
        javax.cache.CacheManager cacheManager=getJCacheManagerFactoryBean().getObject();
        bean.setCacheManager(cacheManager);
        return bean;

    }

    @Bean(name = "jcache")
    public JCacheManagerFactoryBean getJCacheManagerFactoryBean() throws Exception {
        JCacheManagerFactoryBean bean = new JCacheManagerFactoryBean();
        bean.setBeanClassLoader(getClass().getClassLoader());
        bean.setCacheManagerUri(getClass().getResource("/ehcache-jcache-one.xml").toURI());
        return bean;
    }


    @Bean(name = "customersCache")
    public javax.cache.Cache getCustomersJCache() throws Exception {
        String cacheName="customers";
        Cache cache = getJCacheManagerFactoryBean().getObject().getCache(cacheName);
        cache.getCacheManager().enableStatistics(cacheName,true);
        cache.getCacheManager().enableManagement(cacheName,true);
        return cache;
    }


}
