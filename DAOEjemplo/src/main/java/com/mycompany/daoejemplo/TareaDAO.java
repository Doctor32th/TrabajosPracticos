/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.daoejemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tarea;

/**
 *
 * @author FranciscoRomeroGuill
 */
public class TareaDAO {
    
    private static Connection con;
    
    static{
        String url = "jdbc:mysql://localhost:3306/acceso?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String password = "";
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con la base de datos");
        } catch (SQLException ex) {
            Logger.getLogger(TareaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static final String LIST_QUERY ="SELECT * FROM tarea";
    static final String INSERT_QUERY = "INSERT INTO tarea(nombre,responsable,prioridad) VALUES (?,?,?)";
    static final String GET_QUERY = "SELECT * FROM tarea WHERE id=?";
    static final String REMOVE_QUERY = "DELETE * FROM tarea WHERE id=?";
    static final String COUNT_QUERY = "SELECT COUNT (id) as total FROM tarea";
    
    
    
    public ArrayList<Tarea> list(){
        
        var salida = new ArrayList<Tarea>();
        
        try( Statement st = con.createStatement() ){
            
            ResultSet resultado = st.executeQuery(LIST_QUERY);
            
            while(resultado.next()){
                Tarea t = new Tarea();
                t.setId( resultado.getInt("id") );
                t.setNombre( resultado.getString("nombre") );
                t.setResponsable( resultado.getString("responsable") );
                t.setPrioridad( resultado.getString("prioridad") );
                
                salida.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TareaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return salida;
        
    }
    
    public Integer save( Tarea t){
        
        try( PreparedStatement ps = con.prepareStatement(INSERT_QUERY,RETURN_GENERATED_KEYS)){
            ps.setString(1, t.getNombre() );
            ps.setString(2, t.getResponsable() );
            ps.setString(3, t.getPrioridad() );
            ps.executeUpdate();
            
            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next()){
                return keys.getInt(1);
            }else{
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TareaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
    }
    
    public Tarea get(Integer id){
        
        //Ponemos preparedstatemen porque necesitamos parametros
        try(PreparedStatement ps = con.prepareStatement(GET_QUERY)){
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Tarea t = new Tarea();
                
                t.setId( rs.getInt("id") );
                t.setNombre( rs.getString("nombre") );
                t.setResponsable( rs.getString("responsable") );
                t.setPrioridad( rs.getString("prioridad") );
                
                return t;
            }else{
                return null;
            }
            
        }catch(SQLException e){
            Logger.getLogger(TareaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
        
        
    }
    
    public boolean remove(Integer id){
        try(PreparedStatement ps = con.prepareStatement(REMOVE_QUERY)){
            ps.setInt(1, id);
            return (ps.executeUpdate() == 1);
        }catch(SQLException e){
            Logger.getLogger(TareaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return false;
    }
    
    public Integer count(){
        //Filas que tenga la tabla
        //El select siempre va con un executeQuery
        
        try(Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(COUNT_QUERY);
            
            rs.next();
            return(rs.getInt("total"));
            
        }catch(SQLException e){
            Logger.getLogger(TareaDAO.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        } 
        
    }
    

}
