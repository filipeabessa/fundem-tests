package ifpe.edu.evento;

import ifpe.edu.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Evento> findEventosByUser(User user) {
        return eventos.stream()
                .filter(evento -> evento.equals(user))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        eventos.removeIf(evento -> evento.getId().equals(id));
    }

    public List<Evento> getEventos() {
        return eventos;
    }
}