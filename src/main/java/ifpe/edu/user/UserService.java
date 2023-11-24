package ifpe.edu.user;

import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.common.validators.BirthDateValidator;
import ifpe.edu.common.validators.EmailValidator;
import ifpe.edu.common.validators.PhoneNumberValidator;
import ifpe.edu.user.dtos.CreateUserDto;
import ifpe.edu.user.dtos.UpdateUserDto;

public class UserService {
    EmailValidator emailValidator = new EmailValidator();
    PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
    BirthDateValidator birthDateValidator = new BirthDateValidator();

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(CreateUserDto createUserDto) {
        boolean isValidEmail = emailValidator.validate(createUserDto.email());
        boolean isPhoneNumberValid = phoneNumberValidator.validate(createUserDto.numeroTelefone());
        boolean isBirthDateValid = birthDateValidator.validate(createUserDto.dataNascimento());

        if (!isBirthDateValid) {
            throw new ValidationException("Erro. Data de nascimento inválida. Use o formato DD/MM/YYYY");
        }

        if (!isPhoneNumberValid) {
            throw new ValidationException("Erro. Número de telefone inválido!");
        }

        if (!isValidEmail) {
            throw new ValidationException("Erro. Email inválido!");
        }

        if (createUserDto.nomeCompleto() == null || createUserDto.nomeCompleto().isEmpty()) {
            throw new ValidationException("Erro. Campo 'Nome completo' não foi inserido!");
        }

        if (createUserDto.senha().length() < 8) {
            throw new ValidationException("Erro. A senha deve ter pelo menos 8 digitos.");
        }

        if (userRepository.findByEmail(createUserDto.email()) != null) {
            throw new ValidationException("Erro. Uma conta já foi criada com esse email!");
        }

        User user = new User(createUserDto);
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(UpdateUserDto updateUserDto){
        User user = userRepository.findById(updateUserDto.id());

        if (user == null) {
            throw new ValidationException("Erro. Usuário não encontrado!");
        }

        user.setNomeCompleto(updateUserDto.nomeCompleto());
        user.setCpf(updateUserDto.cpf());
        user.setEmail(updateUserDto.email());
        user.setDataNascimento(updateUserDto.dataNascimento());
        user.setNumeroTelefone(updateUserDto.numeroTelefone());
        user.setSenha(updateUserDto.senha());

        return userRepository.save(user);
    }
}
