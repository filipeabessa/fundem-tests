package ifpe.edu.doacao;

import ifpe.edu.doacao.dtos.CreateDoacaoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DoacaoServiceTest {

    private static final String TIPO_OBJETO_VALIDO = "Roupas";
    private static final String DESCRICAO_OBJETO_VALIDA = "Roupas usadas";
    private static final LocalDateTime VALIDADE_VALIDA = LocalDateTime.of(2024, Month.APRIL, 21, 12, 0);
    private static final String QUANTIDADE_VALIDA = "20";

    @Mock
    DoacaoRepository doacaoRepository;

    @InjectMocks
    DoacaoService doacaoService = new DoacaoService(doacaoRepository);

    @Test
    @DisplayName("Realizar doacao com informacoes validas")
    void realizarDoacaoComSucesso() {
        CreateDoacaoDto createDoacaoDto = new CreateDoacaoDto(
                TIPO_OBJETO_VALIDO,
                DESCRICAO_OBJETO_VALIDA,
                VALIDADE_VALIDA,
                QUANTIDADE_VALIDA
        );

        Doacao doacaoCriada = new Doacao(createDoacaoDto);
        doacaoCriada.setId(1L);

        ArgumentCaptor<Doacao> doacaoCaptor = ArgumentCaptor.forClass(Doacao.class);
        when(doacaoRepository.save(doacaoCaptor.capture())).thenReturn(doacaoCriada);

        Doacao doacao = doacaoService.registrarDoacao(createDoacaoDto);

        assertEquals(TIPO_OBJETO_VALIDO, doacao.getTipoObjeto());
        assertEquals(DESCRICAO_OBJETO_VALIDA, doacao.getDescricaoObjeto());
        assertEquals(VALIDADE_VALIDA, doacao.getValidade());
        assertEquals(QUANTIDADE_VALIDA, doacao.getQuantidade());
    }
}
