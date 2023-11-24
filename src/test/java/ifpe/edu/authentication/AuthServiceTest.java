package ifpe.edu.authentication;

import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.user.User;
import ifpe.edu.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    AuthRepository authRepository;

    @InjectMocks
    AuthService authService = new AuthService(userRepository, authRepository);

    @Test
    @DisplayName("FIL-TC010 - Realizar loggout com sucesso")
    void realizarLoggoutComSucesso() {
        User user = new User();

        when(authRepository.getLoggedUser()).thenReturn(user);
        assertEquals("Usuário foi desloggado com sucesso", authService.logout());
    }

    @Test
    @DisplayName("FIL-TC011 - Realizar login com rede social pela primeira vez")
    void realizarLoginComRedeSocialPelaPrimeiraVez() {
        authService.socialMediaLogin();
    }

    @Test
    @DisplayName("FIL-TC012 -  Realizar Login com rede social já cadastrada")
    void realizarLoginComRedeSocialJaCadastrada() {
        authService.socialMediaLogin();
    }

    @Test
    @DisplayName("FIL-TC013 - Realizar login com sucesso")
    void realizarLoginComSucesso() {
        String email = "teste@gmail.com";
        String senha = "12345678";

        User user = new User();
        user.setEmail(email);
        user.setSenha(senha);

        when(userRepository.findByEmail(email)).thenReturn(user);

        assertEquals("Usuário logado com sucesso", authService.login(email, senha));
    }

    @Test
    @DisplayName("FIL-TC014 - Realizar login com email não cadastrado")
    void realizarLoginComEmailNaoCadastrado() {
        String email = "teste@gmail.com";
        String senha = "12345678";

        when(userRepository.findByEmail(email)).thenReturn(null);

        assertThrows(ValidationException.class, () -> {
            authService.login(email, senha);
        }, "Erro. Não existe usuário com esse email");
    }

    @Test
    @DisplayName("FIL-TC015 - Realizar login com senha errada")
    void realizarLoginComSenhaErrada() {
        String email = "teste@gmail.com";
        String senha = "12345678";

        User user = new User();
        user.setEmail(email);
        user.setSenha("senhaincorreta");

        when(userRepository.findByEmail(email)).thenReturn(user);

        assertThrows(ValidationException.class, () -> {
            authService.login(email, senha);
        }, "Erro. Email e/ou senha inválidos!");
    }
}