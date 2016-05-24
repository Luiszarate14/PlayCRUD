package controllers;

import models.User;
import models.utils.AppException;
import play.Logger;
import play.data.Form;
import play.data.validation.Constraints;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.home;
import views.html.userhome;
import models.utils.Hash;


import static play.data.Form.form;

/**
 * Controlador encargado del manejo de la aplicación, se encarga de cosas como:
 * login, logout, creación de usuarios y otros aspectos generales relacionados
 * con la aplicación
 */
public class Application extends Controller {

    /**
     * Variable estatica que contiene el valor de redirección a la página
     * inicial de la aplicación, es decir, la pantalla con el formulario de login
    **/
    public Result GO_HOME = redirect(
            routes.Application.home()
    );


    /**
     * Muestra la pantalla indicada dependiendo de si el usuario se encuentra
     * logueado o no en la aplicación, si ya está logueado redirecciona a la
     * pantalla de bienvenida, si no lo está o si los datos de login están
     * incorrectos retorna el render de la plantilla de login
     *
     * @return render de la pagina de login o redirección a la página inicial
     * del dashboard (pantalla de bienvenida)
     */
    public Result home() {
        String username = ctx().session().get("username");
        if (username != null) {
            User user = User.findByUsername(username);
            if (user != null) {
                return  ok(userhome.render(user));//redirect("/");
            } else {
                session().clear();
            }
        }
        return ok(home.render(form(Login.class)));
    }

    /**
     * Login class, usada por el formulario de login para realizar
     * la autenticación de usuarios
     */
    public static class Login {

        @Constraints.Required()
        public String username;
        @Constraints.Required()
        public String password;

        /**
         * Realiza la validación de los datos de usuario, si los datos son
         * incorrectos o hay algún error en el sistema se crea un mensaje
         * indicando el error encontrado
         *
         * @return nulo si validacion de los datos del usuario es correcta,
         * de lo contrario un string con detalles del error (ya sea que
         * los datos del usuario sean incorrectos o que ocurra algún error
         * durante la autenticación del usuario)
         */
        public String validate() {

            User user = null;
            try {
                user = User.authenticate(username, password);
            } catch (AppException e) {
                return Messages.get("error.technical");
            }
            if (user == null) {
                return Messages.get("invalid.username.or.password");
            }
            return null;
        }

    }

    /**
     * Clase encargada de crear usuarios en el sistema, es utilizada por el
     * formulario de creación de usuarios
     **/
    public static class CreateUser {

    @Constraints.Required
    public String username;

    @Constraints.Required
    public String password;

    /**
     * Comprueba que los datos ingresados en un input dado no estén en blanco
     *
     * @param input el string con el contenido del input que se desea verificar
     * @return un booleano indicando si el input de entrada está vacío o no
     **/
    private boolean isBlank(String input) {
        return input == null || input.isEmpty() || input.trim().isEmpty();
    }
}

    /**
     * Realiza el manejo de la autenticación de usuarios, utiliza los datos
     * ingresados en el form de login para autenticar el usuario indicado
     *
     * @return Dashboard (pantalla de bienvenida) si la autenticacion es OK o el
     * formulario de login si falla para permitirle al usuario volver a
     * intentarlo
     */
    public Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(home.render(loginForm));
        } else {
            session("username", loginForm.get().username);
            return GO_HOME;
        }
    }

    /**
     * Realiza el cierre de sesión, limpia los datos de la sesión del sistema
     * con el fin de desloguear por completo al usuario que solicitó la acción
     *
     * @return pagina inicial del sistema (página con formulario de login)
     */
    public Result logout() {
        session().clear();
        flash("success",  "Usted ha cerrado sesión correctamente");
        return GO_HOME;
    }

}
