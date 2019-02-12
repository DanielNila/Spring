/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelado;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Peligro
 */
public class LibrosValidar implements Validator{
     private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
   
     private Pattern pattern;
     private Matcher matcher;
    
    @Override
    public boolean supports(Class<?> type) 
    {
        return Libros.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Libros libros=(Libros)o;
         ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre",
        "required.nombre", "El campo Nombre es Obligatorio.");
         ValidationUtils.rejectIfEmptyOrWhitespace(errors, "editorial",
        "required.editorial", "El campo Editorial es Obligatorio.");
         
         ValidationUtils.rejectIfEmptyOrWhitespace(errors, "no_pag",
        "required.no_pag", "El campo Numero de paginas es Obligatorio.");
    }
}
