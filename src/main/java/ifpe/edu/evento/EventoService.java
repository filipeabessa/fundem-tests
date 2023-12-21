package ifpe.edu.evento;

import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.evento.dtos.CreateEventoDto;
import ifpe.edu.evento.dtos.UpdateEventoDto;
import ifpe.edu.user.User;
import jdk.jfr.Event;

import java.util.List;

public class EventoService {

    EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento registrarEvento(CreateEventoDto createEventoDto) {

        Evento evento = new Evento(createEventoDto);
        return eventoRepository.save(evento);
    }

    public Evento findById(Long id) {
        return eventoRepository.findById(id);
    }

    public List<User> findUsersByEvento(Long id) {
        Evento evento = findById(id);
        return evento.participantes;
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


