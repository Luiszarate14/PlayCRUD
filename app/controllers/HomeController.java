package controllers;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import play.data.Form;
import play.mvc.*;
import models.Pregunta;
import play.data.FormFactory;
import play.data.validation.Constraints;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    @Inject FormFactory formFactory;
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        Form<Pregunta> pregForm = formFactory.form(Pregunta.class);
        
        return ok(index.render("Formulario de pregunta", pregForm));
    }
    
    public Result post() {
        Form<Pregunta> pregForm = formFactory.form(Pregunta.class).bindFromRequest();
        if (pregForm.hasErrors()) {
            return badRequest(index.render("Encontramos errores", pregForm));
        } else {
        Map<String, String> values = pregForm.data();
            System.err.println("VOY");
         Pregunta preg =  new Pregunta();
        boolean requerida=false;
        if(values.get("requerida").equalsIgnoreCase("true")){
            requerida=true;
        }
        preg.texto=values.get("texto");
        preg.tipo=values.get("tipo");
        preg.requerida=requerida;
        preg.textoAyuda=values.get("textoAyuda");
        preg.save();
        pregForm = formFactory.form(Pregunta.class);
        }
        return ok(index.render("Recepción de formulario correcto.", pregForm));
    }

}
