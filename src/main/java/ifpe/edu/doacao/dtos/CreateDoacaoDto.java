package ifpe.edu.doacao.dtos;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record CreateDoacaoDto(
        String tipoObjeto,

        String descricaoObjeto,

        LocalDateTime validade,

        String quantidade
) {
}
