package models;

import models.utils.AppException;
import models.utils.Hash;
import play.data.format.Formats;
import play.data.validation.Constraints;
import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

import play.Logger;


/**
 * Clase encargada de los usuarios del sistema que se encuentran en la base de datos
 **/
@Entity
public class User extends Model {

    @Id
    public Long id;

    @Constraints.Required
    @Formats.NonEmpty
    @Column(unique = true)
    public String username;

    @Constraints.Required
    @Formats.NonEmpty
    public String passwordHash;

    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date creationDate;


    // -- querys (long id, user.class)
    public static Model.Finder<Long, User> find = new Model.Finder<Long, User>(Long.class, User.class);

    /**
     * Busca un usuario a a partir de un nombre de usuario
     *
     * @param username nombre del usuario a buscar
     * @return Usuario encontrado o null
     */
    public static User findByUsername(String username) {
        return find.where().eq("username", username).findUnique();
    }


    /**
     * Autentica usuarios utilizando el nombre de usuario y la contraseña sin encriptar
     *
     * @param username nombre de usuario
     * @param password contraseña sin encriptar
     * @return un usuario si se autentica correctamente, null en el caso contrario
     * @throws AppException en caso de error
     */
    public static User authenticate(String username, String password) throws AppException {
        User user = find.where().eq("username", username).findUnique();
        if (user != null) {
            if (Hash.checkPassword(password, user.passwordHash)) {
                return user;
            }
        }
        return null;
    }
}
