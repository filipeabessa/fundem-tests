package ifpe.edu.evento;

import ifpe.edu.evento.dtos.CreateEventoDto;
import ifpe.edu.user.User;
import ifpe.edu.user.UserRepository;
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

    @Mock
    UserRepository userRepository;
    @InjectMocks
    EventoService eventoService;

    EventoController eventoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        eventoController = new EventoController(eventoService);
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

         User usuarioParticipante = eventoController.participarEvento(evento, USUARIO_VALIDO);

         when(eventoController.findEventosByUser(usuarioParticipante)).thenReturn(Collections.singletonList(evento));

         assertDoesNotThrow(() -> {
             List<Evento> result = eventoService.findEventosByUser(USUARIO_VALIDO);

             assertEquals(NOME_EVENTO_VALIDO, result.get(0).getNomeEvento());
             assertEquals(META_VALIDA, result.get(0).getMetaEvento());
             assertEquals(DESCRICAO_VALIDA, result.get(0).getDescricao());
             assertEquals(LOCALIZACAO_VALIDA, result.get(0).getLocalizacao());
         });
     }
 }
