package ifpe.edu.user;


import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.user.dtos.CreateUserDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {
    UserRepository userRepository = new UserRepository();
    UserService userService = new UserService(userRepository);

    @Mock
    private User user;


    @Test
    void realizarCadastroDeUsuarioComSucesso() {
        CreateUserDto createUserDto = new CreateUserDto(
                "João da Silva",
                "12345678910",
                "teste@gmail.com",
                "1999-01-01",
                "81999999999",
                "12345678"
        );

        User user = userService.registerUser(createUserDto);

        assertEquals("João da Silva", user.getNomeCompleto());
        assertEquals("12345678910", user.getCpf());
        assertEquals("teste@gmail.com", user.getEmail());
        assertEquals("1999-01-01", user.getDataNascimento());
        assertEquals("81999999999", user.getNumeroTelefone());
        assertEquals("12345678", user.getSenha());
    }

    @Test
    void realizarCadastroDeUsuarioComEmailJaCadastrado() {
        String email = "teste@gmail.com";

        CreateUserDto createUserDto = new CreateUserDto(
                "João da Silva",
                "12345678910",
                email,
                "1999-01-01",
                "81999999999",
                "12345678"
        );
        userService.registerUser(createUserDto);

        assertThrows(ValidationException.class, () -> {
            userService.registerUser(createUserDto);
        }, "Erro. Uma conta já foi criada com esse email!");
    }

    @Test
    void realizarCadastroDeUsuarioComEmailInvalido() {
        String email = "teste";

        CreateUserDto createUserDto = new CreateUserDto(
                "João da Silva",
                "12345678910",
                email,
                "1999-01-01",
                "81999999999",
                "12345678"
        );

        assertThrows(ValidationException.class, () -> {
            userService.registerUser(createUserDto);
        }, "Erro. Email inválido!");
    }

    @Test
    void realizarCadastroDeUsuarioComTelefoneInvalido() {
        String telefone = "8199999999";

        CreateUserDto createUserDto = new CreateUserDto(
                "João da Silva",
                "12345678910",
                "teste@gmail.com",
                "1999-01-01",
                telefone,
                "12345678"
        );

        assertThrows(ValidationException.class, () -> {
            userService.registerUser(createUserDto);
        }, "Erro. Formato de telefone inválido! Insira o telefone passando o código do país, estado e o seu número de telefone, seguindo o padrão +55 81 xxxxx-xxxx");

    }

    @Test
    void realizarCadastroDeUsuarioSemCampoNomeCompleto() {
        String nomeCompleto = "";

        CreateUserDto createUserDto = new CreateUserDto(
                 nomeCompleto,
                "12345678910",
                "teste@gmail.com",
                "1999-01-01",
                "81999999999",
                "12345678"
        );

        assertThrows(ValidationException.class, () -> {
            userService.registerUser(createUserDto);
        }, "Erro. Campo 'Nome completo' não foi inserido!");
    }

    @Test
    void realizarCadastroDeUsuarioComSenhaMenorQue8Digitos() {
        String senha = "1234567";

        CreateUserDto createUserDto = new CreateUserDto(
                "Filipe",
                "12345678910",
                "teste@gmail.com",
                "1999-01-01",
                "81999999999",
                senha
        );

        assertThrows(ValidationException.class, () -> {
            userService.registerUser(createUserDto);
        }, "Erro. A senha deve ter pelo menos 8 digitos.");
    }


}