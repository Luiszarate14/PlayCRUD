package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import models.Pregunta;
import play.data.Form;
import play.mvc.*;
import models.Respuesta;
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
        Form<Respuesta> respForm = formFactory.form(Respuesta.class);
        return ok(crearRespuesta.render("Formulario de respunta",
                respForm,
                routes.RespuestaController.crearRespuestaPost()));
    }
    
    public Result crearRespuestaPost() {
        Form<Respuesta> respForm = formFactory.form(Respuesta.class).bindFromRequest();
        if (respForm.hasErrors()) {
            return badRequest(crearRespuesta.render("Encontramos errores",
                    respForm, routes.RespuestaController.crearRespuestaPost()));
        } else {
            Respuesta resp = respForm.get();
            resp.save();
            respForm = formFactory.form(Respuesta.class);
        }
        return ok(crearRespuesta.render("Recepción de formulario correcto.", 
                respForm, routes.RespuestaController.crearRespuestaPost()));
    }

}
