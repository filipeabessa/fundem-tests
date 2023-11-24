package ifpe.edu.user;

import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.user.dtos.CreateUserDto;
import ifpe.edu.user.dtos.UpdateUserDto;

public class UserController {
    UserService userService;
    UserFieldsValidator userFieldsValidator = new UserFieldsValidator();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void registerUser(CreateUserDto createUserDto) {

        userFieldsValidator.validateFields(createUserDto.cpf(), createUserDto.email(), createUserDto.dataNascimento(), createUserDto.numeroTelefone(), createUserDto.senha());

        if (createUserDto.nomeCompleto() == null || createUserDto.nomeCompleto().isEmpty()) {
            throw new ValidationException("Erro. Campo 'Nome completo' não foi inserido!");
        }

        userService.registerUser(createUserDto);
    }

    public User findById(Long id) {
        return userService.findById(id);
    }

    public void deleteById(Long id) {
        userService.deleteById(id);
    }

    public User updateUser(UpdateUserDto updateUserDto){
        userFieldsValidator.validateFields(updateUserDto.cpf(), updateUserDto.email(), updateUserDto.dataNascimento(), updateUserDto.numeroTelefone(), updateUserDto.senha());
        return userService.updateUser(updateUserDto);
    }
}
