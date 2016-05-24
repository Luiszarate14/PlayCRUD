package models.utils;

import org.mindrot.jbcrypt.BCrypt;
import java.util.UUID;
import play.i18n.Messages;
import play.Logger;


/**
 * Clase utilitaria para el manejo de contraseñas.  Maneja la encripción y
 * validación de contraseñas
 */
public class Hash {

    /**
     * Crea una contraseña encriptada a partir de una contraseña en texto plano
     *
     * @param clearString el string sin encriptar (en texto plano)
     * @return el password encriptado
     * @throws AppException AppException en caso de error
     */
    public static String createPassword(String password) throws AppException {
        if (password == null) {
            throw new AppException(Messages.get("password.not.defined"));
        }
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Verifica que un password encriptado sea igual a un candidato en texto plano
     *
     * @param candidate contraseña en texto plano que se quiere comprobar
     * @param encryptedPassword el password encriptado para validar
     * @return true si el candidato coincide, false en el caso contrario
     */
    public static boolean checkPassword(String candidate, String encryptedPassword) {
        if (candidate == null) {
            return false;
        }
        if (encryptedPassword == null && encryptedPassword != " ") {
            return false;
        }
        return BCrypt.checkpw(candidate, encryptedPassword);
    }
}
