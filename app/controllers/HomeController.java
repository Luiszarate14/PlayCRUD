package controllers;

import java.util.HashMap;
import java.util.List;
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
    @Inject  FormFactory formFactory;
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public  Result index() {
        Form<Pregunta> pregForm = formFactory.form(Pregunta.class);
        
        return ok(index.render("Formulario de pregunta", 
                pregForm,
                controllers.routes.HomeController.listaPregunta()));
    }
    
    public  Result post() {
        Form<Pregunta> pregForm = formFactory.form(Pregunta.class).bindFromRequest();
        if (pregForm.hasErrors()) {
            return badRequest(index.render("Encontramos errores", 
                    pregForm, controllers.routes.HomeController.index()));
        } else {
        Map<String, String> values = pregForm.data();
            System.out.println(values);
        Pregunta preg =  new Pregunta();
        boolean requerida=false;
        if(values.containsKey("requerida") && values.get("requerida").equalsIgnoreCase("true")){
            requerida=true;
        }
        preg.texto=values.get("texto");
        preg.tipo=values.get("tipo");
        preg.requerida=requerida;
        preg.textoAyuda=values.get("textoAyuda");
        preg.save();
        pregForm = formFactory.form(Pregunta.class);
        }
        return ok(index.render("Recepción de formulario correcto.", pregForm,
                controllers.routes.HomeController.listaPregunta()));
    }

     public  Result listaPregunta() {
        List<Pregunta> pregs = Pregunta.find.all();
        
        return ok(listaPregunta.render("Listado de preguntas", pregs));
    }   
     
    public  Result editar(Long id) {
        Pregunta instancia=Pregunta.find.byId(id);
        Form<Pregunta> pregForm = formFactory.form(Pregunta.class).fill(instancia);
        return ok(index.render("Formulario de pregunta", 
                pregForm, controllers.routes.HomeController.editarPost(id)));
    }

    public  Result editarPost(Long id) {
        Pregunta instancia=Pregunta.find.byId(id);
        Form<Pregunta> pregForm = formFactory.form(Pregunta.class
        ).fill(instancia).bindFromRequest();
        
        if (pregForm.hasErrors()) {
            return badRequest(index.render(
                    "Encontramos errores", pregForm,
                    controllers.routes.HomeController.editarPost(id)
                    ));
        }
        
        
        Pregunta preg = pregForm.get();
        instancia.texto=preg.texto;
        instancia.tipo=preg.tipo;
        instancia.requerida=preg.requerida;
        instancia.textoAyuda=preg.textoAyuda;        
        instancia.save();
        return redirect(controllers.routes.HomeController.listaPregunta());
    }
    
}
