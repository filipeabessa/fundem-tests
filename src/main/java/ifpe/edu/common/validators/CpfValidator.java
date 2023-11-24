package ifpe.edu.common.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CpfValidator implements Validator {

    private static final String CPF_REGEX = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";

    private final Pattern pattern = Pattern.compile(CPF_REGEX);

    @Override
    public boolean validate(String value) {
        Matcher matcher = pattern.matcher(value);
        return matcher.matches() && isValidCpf(value);
    }

    private boolean isValidCpf(String cpf) {
        cpf = cpf.replaceAll("[.-]", ""); // Remove dots and hyphen

        // Check if all digits are the same
        if (cpf.matches("(\\d)\\1*")) {
            return false;
        }

        // Check CPF algorithm
        int[] weights1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        // Validate first digit
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * weights1[i];
        }
        int remainder1 = sum % 11;
        int digit1 = (remainder1 < 2) ? 0 : (11 - remainder1);

        // Validate second digit
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * weights2[i];
        }
        int remainder2 = sum % 11;
        int digit2 = (remainder2 < 2) ? 0 : (11 - remainder2);

        // Check if calculated digits match the provided digits
        return digit1 == Character.getNumericValue(cpf.charAt(9)) &&
                digit2 == Character.getNumericValue(cpf.charAt(10));
    }
}