/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class Respuesta {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String texto;
    
    @Constraints.Required
    @ManyToOne()
    Pregunta pregunta;
    
    public static Model.Finder<Long, Respuesta> find = new 
                                 Model.Finder<Long, Respuesta>(Respuesta.class);
    
}
