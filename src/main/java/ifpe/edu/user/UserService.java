package ifpe.edu.user;

import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.common.validators.BirthDateValidator;
import ifpe.edu.common.validators.CpfValidator;
import ifpe.edu.common.validators.EmailValidator;
import ifpe.edu.common.validators.PhoneNumberValidator;
import ifpe.edu.evento.Evento;
import ifpe.edu.user.dtos.CreateUserDto;
import ifpe.edu.user.dtos.UpdateUserDto;

import java.util.List;

public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(CreateUserDto createUserDto) {

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

    public List<User> findUsersByEvento(Evento evento) {
        return userRepository.findUsersByEvento(evento);
    }
}
