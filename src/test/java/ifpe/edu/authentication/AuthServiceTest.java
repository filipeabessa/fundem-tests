package ifpe.edu.authentication;

import ifpe.edu.user.User;
import ifpe.edu.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals("Usuário foi desloggado com sucesso", authService.logout());
    }


}