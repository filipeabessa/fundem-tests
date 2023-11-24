package ifpe.edu.user;


import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.user.dtos.CreateUserDto;
import ifpe.edu.user.dtos.UpdateUserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserService(userRepository);


    @Test
    @DisplayName("FIL-TC001 - Realizar cadastro de usuário com sucesso")
    void realizarCadastroDeUsuarioComSucesso() {
        CreateUserDto createUserDto = new CreateUserDto(
                "João da Silva",
                "12345678910",
                "teste@gmail.com",
                "1999-01-01",
                "81999999999",
                "12345678"
        );


        User createdUser = new User(createUserDto);
        createdUser.setId(1L);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        when(userRepository.save(userCaptor.capture())).thenReturn(createdUser);

        User user = userService.registerUser(createUserDto);

        assertEquals("João da Silva", user.getNomeCompleto());
        assertEquals("12345678910", user.getCpf());
        assertEquals("teste@gmail.com", user.getEmail());
        assertEquals("1999-01-01", user.getDataNascimento());
        assertEquals("81999999999", user.getNumeroTelefone());
        assertEquals("12345678", user.getSenha());
    }

    @Test
    @DisplayName("FIL-TC002 - Realizar cadastro de usuário com email já cadastrado")
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
        when(userRepository.findByEmail(email)).thenReturn(new User(createUserDto));

        assertThrows(ValidationException.class, () -> {
            userService.registerUser(createUserDto);
        }, "Erro. Uma conta já foi criada com esse email!");
    }

    @Test
    @DisplayName("FIL-TC003 - Realizar cadastro de usuário com email inválido")
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
    @DisplayName("FIL-TC004 - Realizar cadastro de usuário com telefone inválido")
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
    @DisplayName("FIL-TC005 - Realizar cadastro de usuário sem campo nome completo")
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
    @DisplayName("FIL-TC006 - Realizar cadastro de usuário com senha menor que 8 digitos")
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

    @Test
    @DisplayName("FIL-TC007 - Realizar cadastro de usuário com CPF inválido")
    void realizarCadastroDeUsuarioComCpfInvalido() {
        String cpf = "123456789";

        CreateUserDto createUserDto = new CreateUserDto(
                "Filipe",
                cpf,
                "teste@gmail.com",
                "1999-01-01",
                "81999999999",
                "12345678"
        );

        assertThrows(ValidationException.class, () -> {
            userService.registerUser(createUserDto);
        }, "Erro. CPF inválido!");
    }

    @Test
    @DisplayName("FIL-TC008 - Realizar cadastro de usuário com data de nascimento inválida")
    void realizarCadastroDeUsuarioComDataDeNascimentoInvalida() {
        String dataNascimento = "1999-01-01";

        CreateUserDto createUserDto = new CreateUserDto(
                "Filipe",
                "12345678910",
                "teste@gmail.com",
                dataNascimento,
                "81999999999",
                "12345678"
        );

        assertThrows(ValidationException.class, () -> {
            userService.registerUser(createUserDto);
        }, "Erro. Data de nascimento inválida. Use o formato DD/MM/YYYY");
    }

    @Test
    @DisplayName("FIL-TC009 - Realizar cadastro de usuário com data de nascimento inválida")
    void edicaoDoNomeDoUsuarioEfectuadaComSucesso() {
        User user = new User();
        user.setId(1L);
        user.setNomeCompleto("João da Silva");
        user.setEmail("teste@gmail.com");
        user.setCpf("12345678910");
        user.setDataNascimento("1999-01-01");
        user.setNumeroTelefone("81999999999");


        UpdateUserDto updateUserDto = new UpdateUserDto(
                1L,
                "Novo nome",
                "12345678910",
                "teste@gmail.com",
                "1999-01-01",
                "81999999999",
                "12345678"
        );

        when(userRepository.findById(1L)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        userService.updateUser(updateUserDto);

        assertEquals("Novo nome", user.getNomeCompleto());
    }
}