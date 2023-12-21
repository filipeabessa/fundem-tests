package ifpe.edu.doacao;

import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.doacao.Doacao;
import ifpe.edu.doacao.dtos.CreateDoacaoDto;
import ifpe.edu.doacao.dtos.UpdateDoacaoDto;
import ifpe.edu.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DoacaoControllerTest {

    @Mock
    DoacaoRepository doacaoRepository;

    @Mock
    DoacaoService doacaoService;

    DoacaoController doacaoController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        doacaoService = new DoacaoService(doacaoRepository);
        doacaoController = new DoacaoController(doacaoService);
    }


    private static final String TIPO_OBJETO_VALIDO = "Roupas";
    private static final String DESCRICAO_OBJETO_VALIDA = "Roupas usadas";
    private static final LocalDateTime VALIDADE_VALIDA = LocalDateTime.of(2024, Month.APRIL, 21, 12, 0);
    private static final String QUANTIDADE_VALIDA = "20";

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

    @Test
    @DisplayName("Criar doacao com validade incorreta")
    void createDoacaoWithInvalidValidade() {
        LocalDateTime VALIDADE_INVALIDA = LocalDateTime.of(2021, Month.JANUARY, 31, 12, 0);

        CreateDoacaoDto createDoacaoDto = new CreateDoacaoDto(
                null,
                DESCRICAO_OBJETO_VALIDA,
                VALIDADE_INVALIDA,
                QUANTIDADE_VALIDA,
                USUARIO_VALIDO
        );

        assertThrows(ValidationException.class, () -> doacaoController.registerDoacao(createDoacaoDto));
    }

    @Test
    @DisplayName("Criar doacao com tipo de objeto vazio")
    void createDoacaoWithInvalidTipoObjeto() {

        CreateDoacaoDto createDoacaoDto = new CreateDoacaoDto(
                null,
                DESCRICAO_OBJETO_VALIDA,
                VALIDADE_VALIDA,
                QUANTIDADE_VALIDA,
                USUARIO_VALIDO
        );

        assertThrows(ValidationException.class, () -> doacaoController.registerDoacao(createDoacaoDto));
    }

    @Test
    @DisplayName("Criar doacao com quantidade vazia")
    void createDoacaoWithInvalidQuatidade() {

        CreateDoacaoDto createDoacaoDto = new CreateDoacaoDto(
                TIPO_OBJETO_VALIDO,
                DESCRICAO_OBJETO_VALIDA,
                VALIDADE_VALIDA,
                "",
                USUARIO_VALIDO
        );

        assertThrows(ValidationException.class, () -> doacaoController.registerDoacao(createDoacaoDto));
    }

    @Test
    @DisplayName("Criar doacao com descricao vazia")
    void createDoacaoWithInvalidDescricao() {

        CreateDoacaoDto createDoacaoDto = new CreateDoacaoDto(
                TIPO_OBJETO_VALIDO,
                "",
                VALIDADE_VALIDA,
                QUANTIDADE_VALIDA,
                USUARIO_VALIDO
        );

        assertThrows(ValidationException.class, () -> doacaoController.registerDoacao(createDoacaoDto));
    }

    @Test
    @DisplayName("Realizar doacao com informacoes validas")
    void realizarDoacaoComSucesso() {
        CreateDoacaoDto createDoacaoDto = new CreateDoacaoDto(
                TIPO_OBJETO_VALIDO,
                DESCRICAO_OBJETO_VALIDA,
                VALIDADE_VALIDA,
                QUANTIDADE_VALIDA,
                USUARIO_VALIDO
        );

        Doacao doacaoCriada = new Doacao(createDoacaoDto);
        doacaoCriada.setId(1L);

        ArgumentCaptor<Doacao> doacaoCaptor = ArgumentCaptor.forClass(Doacao.class);
        when(doacaoService.doacaoRepository.save(doacaoCaptor.capture())).thenReturn(doacaoCriada);

        Doacao doacao = doacaoService.registrarDoacao(createDoacaoDto);

        assertEquals(TIPO_OBJETO_VALIDO, doacao.getTipoObjeto());
        assertEquals(DESCRICAO_OBJETO_VALIDA, doacao.getDescricaoObjeto());
        assertEquals(VALIDADE_VALIDA, doacao.getValidade());
        assertEquals(QUANTIDADE_VALIDA, doacao.getQuantidade());
    }

    @Test
    @DisplayName("Visualizar doacoes com informacoes validas")
    void visualizarDoacaoComSucesso() {

        CreateDoacaoDto createDoacaoDto = new CreateDoacaoDto(
                TIPO_OBJETO_VALIDO,
                DESCRICAO_OBJETO_VALIDA,
                VALIDADE_VALIDA,
                QUANTIDADE_VALIDA,
                USUARIO_VALIDO
        );

        Doacao doacaoCriada = new Doacao(createDoacaoDto);

        ArgumentCaptor<Doacao> doacaoCaptor = ArgumentCaptor.forClass(Doacao.class);
        when(doacaoRepository.save(doacaoCaptor.capture())).thenReturn(doacaoCriada);

        Doacao doacao = doacaoService.registrarDoacao(createDoacaoDto);

        when(doacaoService.findDoacoesByUser(USUARIO_VALIDO)).thenReturn(Collections.singletonList(doacao));

        assertDoesNotThrow(() -> {
            List<Doacao> result = doacaoService.findDoacoesByUser(USUARIO_VALIDO);

            assertEquals(TIPO_OBJETO_VALIDO, result.get(0).getTipoObjeto());
            assertEquals(DESCRICAO_OBJETO_VALIDA, result.get(0).getDescricaoObjeto());
            assertEquals(VALIDADE_VALIDA, result.get(0).getValidade());
            assertEquals(QUANTIDADE_VALIDA, result.get(0).getQuantidade());
        });
    }

}
