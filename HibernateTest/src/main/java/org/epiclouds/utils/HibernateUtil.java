/**
 * @author Administrator
 * @created 2015 2015年3月15日 上午11:38:20
 * @version 1.0
 */
package org.epiclouds.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Administrator
 *
 */
public class HibernateUtil {
	private static final  SessionFactory sessionFactory  =  buildSessionFactory(); 

    private static  SessionFactory  buildSessionFactory()  { 
       try  { 
           // Create the  Ses s ionFactory from  hibernate.cfg.xml 
    	   Configuration configuration = new Configuration();
    	   configuration.configure("hibernate.cfg.xml");
    	   ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
    	           .applySettings(configuration.getProperties()).build();
    	   return configuration
    	           .buildSessionFactory(serviceRegistry);
        } 
       catch  (Throwable ex)  { 
           //  Make s ure you  log the exception,  as  it  might  be s wallowed 
            System.err.println("Initial  SessionFactory creation failed."  + ex); 
           throw  new  ExceptionInInitializerError(ex); 
        } 
    } 

    public static  SessionFactory getSessionFactory()  { 
        return sessionFactory; 
    } 
}
