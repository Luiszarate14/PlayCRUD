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


@Entity
public class Pregunta extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Constraints.Required
    public String texto;
    
    @Constraints.Required
    public String tipo;

    public boolean requerida;
    public String textoAyuda;
    
    public static Finder<Long, Pregunta> find = new Finder<Long, Pregunta>(Pregunta.class);

    public static Map<String,String> preguntas() {
        List<Pregunta> pregs = Pregunta.find.all();
        LinkedHashMap<String,String> options = new LinkedHashMap<>();
        for(Pregunta set: pregs) {
            options.put(set.id.toString(), set.texto.toString());
        }
        return options;
    }


}
