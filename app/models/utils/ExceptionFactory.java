package models.utils;

import play.Logger;

/**
 * Se encarga de la creación de excepciones
 **/
public class ExceptionFactory {

  /**
   * Crea una nueva AppException a partir de una excepción,
   * indicandole la causa por la cual fue lanzada
   *
   * @param exception excepción a partir de la cual se va a crear la nueva AppException
   * @return la nueva AppException creada
   **/
    public static AppException getNewAppException(Exception exception) {
        Logger.error(exception.getMessage());
        AppException app = new AppException();
        app.initCause(exception);
        return app;
    }
}
