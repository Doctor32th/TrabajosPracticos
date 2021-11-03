package com.mycompany.daoejemplo;

import model.Tarea;

/**
 *
 * @author FranciscoRomeroGuill
 */
public class Principal {

    public static void main(String[] args) {

        var DAO = new TareaDAO();
        
        Tarea t = new Tarea(3434,"Preparar Examen","Francisco","Importante");
        
        t.setId( DAO.save(t));       
        System.out.println("Total de tareas: " + DAO.count());
               
        /*if(DAO.remove(3)){
            System.out.println("Fila eliminada correctamente");
        }*/
        
        var listado = DAO.list();
        
        
        listado.forEach(System.out::println);
        
    }
    
}
