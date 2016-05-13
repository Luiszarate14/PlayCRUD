/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class Pregunta extends Model{

     @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String texto;
    
    @Constraints.Required
    public String tipo;

    @Constraints.Required
    public boolean requerida;

    @Constraints.Required
    public String textoAyuda;
    
    public static Finder<Long, Pregunta> find = new Finder<Long, Pregunta>(Pregunta.class);
}
