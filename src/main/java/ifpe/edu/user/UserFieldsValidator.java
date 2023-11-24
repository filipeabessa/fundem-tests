package ifpe.edu.user;

import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.common.validators.BirthDateValidator;
import ifpe.edu.common.validators.CpfValidator;
import ifpe.edu.common.validators.EmailValidator;
import ifpe.edu.common.validators.PhoneNumberValidator;
import ifpe.edu.user.dtos.CreateUserDto;

public class UserFieldsValidator {
    private final EmailValidator emailValidator = new EmailValidator();
    private final PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
    private final BirthDateValidator birthDateValidator = new BirthDateValidator();
    private final CpfValidator cpfValidator = new CpfValidator();
    public void validateFields(String cpf, String email, String dataNascimento, String numeroTelefone, String senha) {
        boolean isValidEmail = emailValidator.validate(email);
        boolean isPhoneNumberValid = phoneNumberValidator.validate(numeroTelefone);
        boolean isBirthDateValid = birthDateValidator.validate(dataNascimento);
        boolean isCpfValid = cpfValidator.validate(cpf);

        if (senha.length() < 8) {
            throw new ValidationException("Erro. A senha deve ter pelo menos 8 digitos.");
        }

        if (!isCpfValid) {
            throw new ValidationException("Erro. CPF inválido.");
        }

        if (!isBirthDateValid) {
            throw new ValidationException("Erro. Data de nascimento inválida. Use o formato DD/MM/YYYY");
        }

        if (!isPhoneNumberValid) {
            throw new ValidationException("Erro. Número de telefone inválido!");
        }

        if (!isValidEmail) {
            throw new ValidationException("Erro. Email inválido!");
        }

    }
}
