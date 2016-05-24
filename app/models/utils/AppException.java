package models.utils;

/**
 * Se encarga del manejo de excepciones en la aplicación
 **/
public class AppException extends Exception {

    public AppException() {
    }

    /**
     * Crea una excepción con el mensaje indicado
     *
     * @param message mensaje de error de la excepción
     **/
    public AppException(String message) {
        super(message);
    }

    /**
     * Crea una exepción con el mensaje y la causa indicados
     *
     * @param message el mensaje que contiene la excepción
     * @param cause la causa por la cual la excepción fue lanzada
     **/
    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Crea una excepción con la causa indicada
     *
     * @param cause causa por la cual la exepción fue lanzada
     **/
    public AppException(Throwable cause) {
        super(cause);
    }
}
