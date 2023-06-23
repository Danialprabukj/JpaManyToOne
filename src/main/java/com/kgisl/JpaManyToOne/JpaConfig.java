package com.kgisl.JpaManyToOne;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaConfig {
    private static final EntityManagerFactory emFactory;
    
    static {
        try {
            emFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static EntityManagerFactory getEntityManagerFactory() {
        return emFactory;
    }
}
