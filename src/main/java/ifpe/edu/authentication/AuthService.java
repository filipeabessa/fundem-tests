package ifpe.edu.authentication;

import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.user.UserRepository;

public class AuthService {
    UserRepository userRepository;
    AuthRepository authRepository;

    public AuthService(UserRepository userRepository, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
    }

    public String login(String email, String senha) {
        var user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ValidationException("Usuário não encontrado");
        }

        if (!user.getSenha().equals(senha) || !user.getEmail().equals(email)) {
            throw new ValidationException("Erro. Email e/ou senha inválidos!");
        }

        authRepository.setLoggedUser(user);

        return "Usuário logado com sucesso";
    }

    public String logout() {
        authRepository.setLoggedUser(null);
        return "Usuário foi desloggado com sucesso";
    }
}
