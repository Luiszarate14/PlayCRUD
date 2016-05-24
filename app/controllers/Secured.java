package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Clase encargada de la sesión y autenticación del usuario en la aplicación, extiende de la clase
 * Security de Play y sobreescribe los métodos necesarios
 **/
public class Secured extends Security.Authenticator {

    /**
     * Método encargado de obtener el nombre de usuario que tiene sesión activa
     * en la aplicación
     *
     * @param ctx corresponde al contexto http de la aplicación en el momento
     * @return nombre de usuario encontrado en la información de la sesión
     **/
    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("username");
    }

    /**
     * Método encargado de redireccionar a la plantilla de bienvenida cuando el usuario
     * se ha logueado correctamente y por lo tanto tiene autorización para navegar
     * en las vistas restringidas de la aplicación
     *
     * @param ctx corresponde al contexto http de la aplicación en el momento
     * @return redirección a la pantalla de bienvenida del dashboard
     **/
    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return redirect(routes.Application.home());
    }
}
