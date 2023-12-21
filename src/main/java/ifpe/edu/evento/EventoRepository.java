package ifpe.edu.evento;

import ifpe.edu.user.User;

import java.util.ArrayList;
import java.util.List;

public class EventoRepository {

    private static EventoRepository instance;

    private final List<Evento> eventos = new ArrayList<>();

    public static EventoRepository getInstance() {
        if (instance == null) {
            instance = new EventoRepository();
        }

        return instance;
    }

    public Evento save(Evento evento) {
        evento.setId((long) (eventos.size() + 1));
        eventos.add(evento);

        return evento;
    }

    public List<Evento> findAll() {
        return eventos;
    }

    public Evento findById(Long id) {
        return eventos.stream()
                .filter(evento -> evento.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteById(Long id) {
        eventos.removeIf(evento -> evento.getId().equals(id));
    }
}