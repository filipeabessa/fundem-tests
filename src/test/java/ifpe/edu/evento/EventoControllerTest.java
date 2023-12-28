package ifpe.edu.evento;

import ifpe.edu.common.dtos.UserEventDto;
import ifpe.edu.evento.dtos.CreateEventoDto;
import ifpe.edu.user.User;
import ifpe.edu.user.UserController;
import ifpe.edu.user.UserRepository;
import ifpe.edu.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

 class EventoControllerTest {
    private static final Long ID_VALIDA = 10L;

    private static final String NOME_EVENTO_VALIDO = "Doutores da Alegria";

    private static final String DESCRICAO_VALIDA = "Ajude-nos com seu tempo! Precisamos de voluntarios para uma acao no hospital IMIP";

    private static final List<String> LOCALIZACAO_VALIDA = List.of("Recife, Brasil", "Rua dos Coelhos");

    private static final double META_VALIDA = 2.500;

    private static final Evento EVENTO_VALIDO = new Evento(ID_VALIDA,
              NOME_EVENTO_VALIDO,
              DESCRICAO_VALIDA,
              LOCALIZACAO_VALIDA,
              META_VALIDA);

    private static final String NOME_COMPLETO = "Filipe Bessa";
    private static final String EMAIL_VALIDO = "teste@gmail.com";
    private static final String CPF_VALIDO = "365.420.560-76";
    private static final String DATA_NASCIMENTO_VALIDA = "01/01/1999";
    private static final String SENHA_VALIDA = "12345678";

    private static final User USUARIO_VALIDO = new User(10L,
            NOME_COMPLETO,
            CPF_VALIDO,
            EMAIL_VALIDO,
            DATA_NASCIMENTO_VALIDA,
            SENHA_VALIDA);

    @Mock
    EventoRepository eventoRepository;
    @InjectMocks
    EventoService eventoService;

    EventoController eventoController;

     @Mock
     UserRepository userRepository;
     @InjectMocks
     UserService userService;

     UserController userController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        eventoController = new EventoController(eventoService);
        userController = new UserController(userService);
    }

     @Test
     @DisplayName("Participar de um evento")
     void participarDeUmEvento() {
         CreateEventoDto createEventoDto = new CreateEventoDto(
                 NOME_EVENTO_VALIDO,
                 DESCRICAO_VALIDA,
                 LOCALIZACAO_VALIDA,
                 META_VALIDA
         );

         Evento eventoCriado = new Evento(createEventoDto);

         ArgumentCaptor<Evento> eventoCaptor = ArgumentCaptor.forClass(Evento.class);
         when(eventoRepository.save(eventoCaptor.capture())).thenReturn(eventoCriado);

         Evento evento = eventoController.registrarEvento(createEventoDto);

         ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
         when(userRepository.save(userCaptor.capture())).thenReturn(USUARIO_VALIDO);

         UserEventDto userEventoDto = eventoController.participarEvento(evento, USUARIO_VALIDO);

         when(eventoController.findEventosByUser(userEventoDto.getUser())).thenReturn(Collections.singletonList(evento));
         when(userController.findUsersByEvento(userEventoDto.getEvento())).thenReturn(Collections.singletonList(userEventoDto.getUser()));

         assertDoesNotThrow(() -> {
             List<Evento> result = eventoController.findEventosByUser(userEventoDto.getUser());
             List<User> userResult = userController.findUsersByEvento(userEventoDto.getEvento());

             assertEquals(NOME_EVENTO_VALIDO, result.get(0).getNomeEvento());
             assertEquals(META_VALIDA, result.get(0).getMetaEvento());
             assertEquals(DESCRICAO_VALIDA, result.get(0).getDescricao());
             assertEquals(LOCALIZACAO_VALIDA, result.get(0).getLocalizacao());

             assertEquals(NOME_COMPLETO, userResult.get(0).getNomeCompleto());
             assertEquals(EMAIL_VALIDO, userResult.get(0).getEmail());
             assertEquals(CPF_VALIDO, userResult.get(0).getCpf());
             assertEquals(DATA_NASCIMENTO_VALIDA, userResult.get(0).getDataNascimento());
         });
     }
 }
