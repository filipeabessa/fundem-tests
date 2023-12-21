package ifpe.edu.evento.dtos;

import java.util.List;
public record CreateEventoDto(
        String nomeEvento,
        String descricao,
        List<String> localizacao,
        double metaEvento
) {
}
