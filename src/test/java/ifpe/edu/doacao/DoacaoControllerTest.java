package ifpe.edu.doacao;

import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.doacao.Doacao;
import ifpe.edu.doacao.dtos.CreateDoacaoDto;
import ifpe.edu.doacao.dtos.UpdateDoacaoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class DoacaoControllerTest {

    @Mock
    DoacaoService doacaoService;
    DoacaoController doacaoController = new DoacaoController(doacaoService);

    private static final String TIPO_OBJETO_VALIDO = "Clothing";
    private static final String DESCRICAO_OBJETO_VALIDA = "Used clothes";
    private static final LocalDateTime VALIDADE_VALIDA = LocalDateTime.of(2024, Month.APRIL, 21, 12, 0);
    private static final String QUANTIDADE_VALIDA = "10 bags";

    @Test
    @DisplayName("Criar doacao com validade incorreta")
    void createDoacaoWithInvalidValidade() {
        LocalDateTime VALIDADE_INVALIDA = LocalDateTime.of(2021, Month.JANUARY, 31, 12, 0);

        CreateDoacaoDto createDoacaoDto = new CreateDoacaoDto(
                null,
                DESCRICAO_OBJETO_VALIDA,
                VALIDADE_INVALIDA,
                QUANTIDADE_VALIDA
        );

        assertThrows(ValidationException.class, () -> doacaoController.registerDoacao(createDoacaoDto));
    }

    @Test
    @DisplayName("Criar doacao com tipo de objeto vazio")
    void createDoacaoWithInvalidData() {
        LocalDateTime VALIDADE_INVALIDA = LocalDateTime.of(2021, Month.JANUARY, 27, 12, 0);

        CreateDoacaoDto createDoacaoDto = new CreateDoacaoDto(
                null,
                DESCRICAO_OBJETO_VALIDA,
                VALIDADE_VALIDA,
                QUANTIDADE_VALIDA
        );

        assertThrows(ValidationException.class, () -> doacaoController.registerDoacao(createDoacaoDto));
    }

}
