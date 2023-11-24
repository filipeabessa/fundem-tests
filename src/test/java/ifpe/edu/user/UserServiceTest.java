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
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    String NOME_COMPLETO = "Filipe Bessa";
    String TELEFONE_VALIDO = "+55 (81) 9999-9999";
    String EMAIL_VALIDO = "teste@gmail.com";
    String CPF_VALIDO = "365.420.560-76";
    String DATA_NASCIMENTO_VALIDA = "01/01/1999";
    String SENHA_VALIDA = "12345678";
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserService(userRepository);


    @Test
    @DisplayName("FIL-TC001 - Realizar cadastro de usuário com sucesso")
    void realizarCadastroDeUsuarioComSucesso() {
        CreateUserDto createUserDto = new CreateUserDto(
                NOME_COMPLETO,
                CPF_VALIDO,
                EMAIL_VALIDO,
                DATA_NASCIMENTO_VALIDA,
                TELEFONE_VALIDO,
                SENHA_VALIDA
        );


        User createdUser = new User(createUserDto);
        createdUser.setId(1L);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        when(userRepository.save(userCaptor.capture())).thenReturn(createdUser);

        User user = userService.registerUser(createUserDto);

        assertEquals(NOME_COMPLETO, user.getNomeCompleto());
        assertEquals(CPF_VALIDO, user.getCpf());
        assertEquals(EMAIL_VALIDO, user.getEmail());
        assertEquals(DATA_NASCIMENTO_VALIDA, user.getDataNascimento());
        assertEquals(TELEFONE_VALIDO, user.getNumeroTelefone());
        assertEquals(SENHA_VALIDA, user.getSenha());
    }

    @Test
    @DisplayName("FIL-TC002 - Realizar cadastro de usuário com email já cadastrado")
    void realizarCadastroDeUsuarioComEmailJaCadastrado() {
        String email = EMAIL_VALIDO;

        CreateUserDto createUserDto = new CreateUserDto(
                NOME_COMPLETO,
                CPF_VALIDO,
                email,
                DATA_NASCIMENTO_VALIDA,
                TELEFONE_VALIDO,
                SENHA_VALIDA
        );
        User user = new User(createUserDto);

        lenient().when(userRepository.findByEmail(email)).thenReturn(user);

        assertThrows(ValidationException.class, () -> userService.registerUser(createUserDto), "Erro. Uma conta já foi criada com esse email!");
    }

    @Test
    @DisplayName("FIL-TC003 - Realizar cadastro de usuário com email inválido")
    void realizarCadastroDeUsuarioComEmailInvalido() {
        String emailInvalido = "teste";

        CreateUserDto createUserDto = new CreateUserDto(
                NOME_COMPLETO,
                CPF_VALIDO,
                emailInvalido,
                DATA_NASCIMENTO_VALIDA,
                TELEFONE_VALIDO,
                SENHA_VALIDA
        );

        assertThrows(ValidationException.class, () -> userService.registerUser(createUserDto), "Erro. Email inválido!");
    }

    @Test
    @DisplayName("FIL-TC004 - Realizar cadastro de usuário com telefone inválido")
    void realizarCadastroDeUsuarioComTelefoneInvalido() {
        String telefone = "8199999999";

        CreateUserDto createUserDto = new CreateUserDto(
                NOME_COMPLETO,
                CPF_VALIDO,
                EMAIL_VALIDO,
                DATA_NASCIMENTO_VALIDA,
                telefone,
                SENHA_VALIDA
        );

        assertThrows(ValidationException.class, () -> {
            userService.registerUser(createUserDto);
        }, "Erro. Formato de telefone inválido! Insira o telefone passando o código do país, estado e o seu número de telefone, seguindo o padrão +55 81 xxxxx-xxxx");

    }

    @Test
    @DisplayName("FIL-TC005 - Realizar cadastro de usuário sem campo nome completo")
    void realizarCadastroDeUsuarioSemCampoNomeCompleto() {
        String nomeCompletoVazio = "";

        CreateUserDto createUserDto = new CreateUserDto(
                nomeCompletoVazio,
                CPF_VALIDO,
                EMAIL_VALIDO,
                DATA_NASCIMENTO_VALIDA,
                TELEFONE_VALIDO,
                SENHA_VALIDA
        );

        assertThrows(ValidationException.class, () -> userService.registerUser(createUserDto), "Erro. Campo 'Nome completo' não foi inserido!");
    }

    @Test
    @DisplayName("FIL-TC006 - Realizar cadastro de usuário com senha menor que 8 digitos")
    void realizarCadastroDeUsuarioComSenhaMenorQue8Digitos() {
        String senhaInvalida = "1234567";

        CreateUserDto createUserDto = new CreateUserDto(
                NOME_COMPLETO,
                CPF_VALIDO,
                EMAIL_VALIDO,
                DATA_NASCIMENTO_VALIDA,
                TELEFONE_VALIDO,
                senhaInvalida
        );

        assertThrows(ValidationException.class, () -> {
            userService.registerUser(createUserDto);
        }, "Erro. A senha deve ter pelo menos 8 digitos.");
    }

    @Test
    @DisplayName("FIL-TC007 - Realizar cadastro de usuário com CPF inválido")
    void realizarCadastroDeUsuarioComCpfInvalido() {
        String cpfInvalido = "123456789";

        CreateUserDto createUserDto = new CreateUserDto(
                NOME_COMPLETO,
                cpfInvalido,
                EMAIL_VALIDO,
                DATA_NASCIMENTO_VALIDA,
                TELEFONE_VALIDO,
                SENHA_VALIDA
        );

        assertThrows(ValidationException.class, () -> userService.registerUser(createUserDto), "Erro. CPF inválido!");
    }

    @Test
    @DisplayName("FIL-TC008 - Realizar cadastro de usuário com data de nascimento inválida")
    void realizarCadastroDeUsuarioComDataDeNascimentoInvalida() {
        String dataNascimentoInvalida = "1999-01-01";

        CreateUserDto createUserDto = new CreateUserDto(
                NOME_COMPLETO,
                CPF_VALIDO,
                EMAIL_VALIDO,
                dataNascimentoInvalida,
                TELEFONE_VALIDO,
                SENHA_VALIDA
        );

        assertThrows(ValidationException.class, () -> {
            userService.registerUser(createUserDto);
        }, "Erro. Data de nascimento inválida. Use o formato DD/MM/YYYY");
    }

    @Test
    @DisplayName("FIL-TC009 - Edição do nome do usuário efectuada com sucesso")
    void edicaoDoNomeDoUsuarioEfectuadaComSucesso() {
        User user = new User();
        user.setNomeCompleto("João da Silva");


        UpdateUserDto updateUserDto = new UpdateUserDto(
                1L,
                "Novo nome",
                CPF_VALIDO,
                EMAIL_VALIDO,
                DATA_NASCIMENTO_VALIDA,
                TELEFONE_VALIDO,
                SENHA_VALIDA
        );

        when(userRepository.findById(updateUserDto.id())).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        userService.updateUser(updateUserDto);

        assertEquals("Novo nome", user.getNomeCompleto());
    }
}