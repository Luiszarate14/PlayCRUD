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
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class RespuestaController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result crearRespuestaGet() {
        Form<Pregunta> pregForm = formFactory.form(Pregunta.class);
        return ok(crear.render("Formulario de pregunta",
                pregForm,
                routes.HomeController.listarPregunta()));
    }
       
   
   


}
