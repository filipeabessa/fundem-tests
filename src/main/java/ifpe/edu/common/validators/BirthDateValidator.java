package ifpe.edu.common.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BirthDateValidator implements Validator {
    // Date pattern for dd/MM/yyyy format
    private final String DATE_PATTERN = "dd/MM/yyyy";

    @Override
    public boolean validate(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        sdf.setLenient(false);

        try {
            Date parsedDate = sdf.parse(value);

            return sdf.format(parsedDate).equals(value);
        } catch (ParseException e) {
            return false;
        }
    }
}