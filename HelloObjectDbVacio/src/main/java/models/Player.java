/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LuisRobbeToichoaSaut
 */

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Player implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String position;
    private String team;
    private Double money;
    
}
