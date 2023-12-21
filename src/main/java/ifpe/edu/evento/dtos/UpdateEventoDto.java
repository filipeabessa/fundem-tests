package ifpe.edu.evento.dtos;

import ifpe.edu.user.User;

import java.util.List;

public record UpdateEventoDto(
        Long id,
        String nomeEvento,
        String descricao,
        List<String> localizacao,
        double metaArrecadada,
        double metaEvento,
        List<User> participantes
) {
}
