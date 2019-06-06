package credit.util;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


public class ValidationHelper {
    public static Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator;
    }
    
    public static String[] validate(Object object) {
        return getValidator().validate(object).stream().map(c -> c.getMessage()).toArray(String[]::new);
    }
}
