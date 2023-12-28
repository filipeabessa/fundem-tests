package ifpe.edu.evento;

import ifpe.edu.common.dtos.UserEventDto;
import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.evento.dtos.CreateEventoDto;
import ifpe.edu.evento.dtos.UpdateEventoDto;
import ifpe.edu.user.User;
import ifpe.edu.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class EventoService {

    EventoRepository eventoRepository;

    UserRepository userRepository;

    public EventoService(EventoRepository eventoRepository,
                         UserRepository userRepository) {
        this.eventoRepository = eventoRepository;
        this.userRepository = userRepository;
    }

    public Evento registrarEvento(CreateEventoDto createEventoDto) {

        Evento evento = new Evento(createEventoDto);
        return eventoRepository.save(evento);
    }

    public Evento findById(Long id) {
        return eventoRepository.findById(id);
    }

    public List<Evento> findEventosByUser(User user) {

        return eventoRepository.findEventosByUser(user);
    }

    public UserEventDto adicionarParticipante(Evento evento, User user) {
        List<Evento> eventos = new ArrayList<>();
        eventos.add(evento);
        user.setEventos(eventos);

        List<User> participantes = new ArrayList<>();
        participantes.add(user);
        evento.setParticipantes(participantes);

        Evento eventoAtualizado = eventoRepository.save(evento);
        User userAtualizado = userRepository.save(user);

        return new UserEventDto(userAtualizado, eventoAtualizado);
    }

    public void deleteById(Long id) {
        eventoRepository.deleteById(id);
    }

    public Evento updateEvento(UpdateEventoDto updateEventoDto) {
        Evento evento = eventoRepository.findById(updateEventoDto.id());

        if(evento == null) {
            throw new ValidationException("Erro. Evento nao encontrado");
        }

        evento.setNomeEvento(updateEventoDto.nomeEvento());
        evento.setDescricao(updateEventoDto.descricao());
        evento.setLocalizacao(updateEventoDto.localizacao());
        evento.setMetaArrecadada(updateEventoDto.metaArrecadada());
        evento.setMetaEvento(updateEventoDto.metaEvento());
        evento.setParticipantes(updateEventoDto.participantes());

        return eventoRepository.save(evento);
    }
}


