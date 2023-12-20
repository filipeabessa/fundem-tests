package ifpe.edu.doacao;

import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.common.validators.DataValidadeValidator;

import java.time.LocalDateTime;

public class DoacaoFieldsValidator {
    private final DataValidadeValidator dataValidadeValidator = new DataValidadeValidator();
    public void validateFields(LocalDateTime validade) {
        boolean isValidValidade = dataValidadeValidator.validate(validade);

        if (!isValidValidade) {
            throw new ValidationException("Erro. Data de validade deve ser valida.");
        }
    }


}
