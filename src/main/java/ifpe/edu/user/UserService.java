package ifpe.edu.user;

import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.common.validators.EmailValidator;
import ifpe.edu.user.dtos.CreateUserDto;

public class UserService {
    EmailValidator emailValidator = new EmailValidator();
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(CreateUserDto createUserDto) {
        boolean isValidEmail = emailValidator.validate(createUserDto.email());

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

    public User updateUser(Long id, CreateUserDto createUserDto) {
        User user = new User(createUserDto);
        user.setId(id);
        return userRepository.save(user);
    }
}
