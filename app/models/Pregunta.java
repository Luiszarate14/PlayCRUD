/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */

package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;
import play.mvc.PathBindable;


@Entity
public class Pregunta extends Model implements PathBindable<Pregunta> {

     @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String texto;
    
    @Constraints.Required
    public String tipo;

    public boolean requerida;
    public String textoAyuda;
    
    public static Finder<Long, Pregunta> find = new Finder<Long, Pregunta>(Pregunta.class);

    @Override
    public Pregunta bind(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String unbind(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String javascriptUnbind() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
