package ifpe.edu.common.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements Validator {
    // Padrão para validar números de celular brasileiros
    private final String PHONE_NUMBER_REGEX =
            "^\\+[1-9]\\d{0,2}\\s?\\(?\\d{2,3}\\)?[-.\\s]?\\d{3,4}[-.\\s]?\\d{4}$";

    private final Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);

    @Override
    public boolean validate(String value) {
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}

