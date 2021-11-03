/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloobjectdb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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
        ready.setPosition("Profesor");
        ready.setTeam("2DAM");
        ready.setMoney(9999.9);
        
        em.getTransaction().begin();
        em.persist(ready);
        em.getTransaction().commit();
        
        TypedQuery<models.Player> q = em.createQuery("SELECT p FROM Player p", models.Player.class);
        var resultado = q.getResultList();
        
        System.out.println("Todo el contenido");
        resultado.forEach((p) -> System.out.println(p));
        
        
        q = em.createQuery("SELECT p FROM Player p WHERE p.id > 6", models.Player.class);
        q.setParameter("limite", 5);
        resultado = q.getResultList();
        
        
        System.out.println("\n\nLos mayores de 6");
        resultado.forEach((p) ->System.out.println(p));
        
        
        
        
        
    }
    
}
