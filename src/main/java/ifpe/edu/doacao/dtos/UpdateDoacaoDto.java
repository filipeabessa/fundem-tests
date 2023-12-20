package ifpe.edu.doacao.dtos;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record UpdateDoacaoDto(

        Long id,

        String tipoObjeto,

        String descricaoObjeto,

        LocalDateTime validade,

        String quantidade
) {
}
