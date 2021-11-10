/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestorpracticas;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author LuisRobbeToichoaSaut
 */
public class ObjectDBUtil {
    
    private static EntityManagerFactory emf;
    
    static{
        try{
        
        emf = Persistence.createEntityManagerFactory("db.odb");
        } catch(Exception e){
            System.out.println("Error al crear el Factory");
            System.out.println(e);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    
}
