/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class Respuesta  extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Constraints.Required
    public String texto;
    
    @Constraints.Required
    @ManyToOne()
    Pregunta pregunta;
    
    public static Model.Finder<Long, Respuesta> find = new 
                                 Model.Finder<Long, Respuesta>(Respuesta.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public static Finder<Long, Respuesta> getFind() {
        return find;
    }

    public static void setFind(Finder<Long, Respuesta> find) {
        Respuesta.find = find;
    }
    
    
    
}
