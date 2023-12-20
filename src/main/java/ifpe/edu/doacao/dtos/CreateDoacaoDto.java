package ifpe.edu.doacao.dtos;

import ifpe.edu.user.User;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record CreateDoacaoDto(
        String tipoObjeto,

        String descricaoObjeto,

        LocalDateTime validade,

        String quantidade,

        User user
) {
}
