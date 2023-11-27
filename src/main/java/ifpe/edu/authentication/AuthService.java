package ifpe.edu.authentication;

import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.user.User;
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
            throw new ValidationException("Erro. Não existe usuário com esse email");
        }

        if (!user.getSenha().equals(senha) || !user.getEmail().equals(email)) {
            throw new ValidationException("Erro. Email e/ou senha inválidos!");
        }

        authRepository.setLoggedUser(user);

        return "Usuário logado com sucesso";
    }

    public String logout() {
        var loggedUser = authRepository.getLoggedUser();

        if (loggedUser == null) {
            throw new ValidationException("Não há usuário logado");
        }

        authRepository.setLoggedUser(null);
        return "Usuário foi desloggado com sucesso";
    }

    public ReturnedSocialMediaInfos getInfosFromSocialMedia() {
        return new ReturnedSocialMediaInfos(
                "Nome",
                "filipegbessa@gmail.com",
                "21/07/1997",
                "+55 81 99997-3333",
                "S13bvcbcvsadsa");
    }

    public String socialMediaLogin() {
        var socialMediaInfos = getInfosFromSocialMedia();
        var user = userRepository.findByEmail(socialMediaInfos.email());

        if (user == null) {
            user = new User(socialMediaInfos);
            userRepository.save(user);
            authRepository.setLoggedUser(user);

            return "Usuário criado e logado com sucesso";
        }
        authRepository.setLoggedUser(user);

        return "Login efetuado";
    }
}
