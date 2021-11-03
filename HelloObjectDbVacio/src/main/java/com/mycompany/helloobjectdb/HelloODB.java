/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloobjectdb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Player;

/**
 *
 * @author LuisRobbeToichoaSaut
 */
public class HelloODB {
    
    private static EntityManagerFactory emf;
    
    public static void main(String[] args) {
        
        try{
            //El archivo datos.odb es la base de datos
            emf = Persistence.createEntityManagerFactory("datos.odb");
        }catch(Exception e){
            System.out.println("Error al iniciar EntityManagerFactory");
        }
        
        /**
         * Creación de la sesion (en hibernate es open session)
         */
        EntityManager em = emf.createEntityManager();
        
        Player ready = new Player();
        ready.setName("Francisco");
        ready.setPosition("Delantero");
        ready.setTeam("2DAM");
        ready.setMoney(9999.9);
        
        em.getTransaction().begin();
        em.persist(ready);
        em.getTransaction().commit();
        
        
    }
    
}
