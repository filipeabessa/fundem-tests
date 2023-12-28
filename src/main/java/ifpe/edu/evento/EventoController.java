package ifpe.edu.evento;

import ifpe.edu.common.dtos.UserEventDto;
import ifpe.edu.evento.dtos.CreateEventoDto;
import ifpe.edu.evento.dtos.UpdateEventoDto;
import ifpe.edu.user.User;

import java.util.List;

public class EventoController {
    EventoService eventoService;

   public EventoController(EventoService eventoService) {
       this.eventoService = eventoService;
   }

   public Evento registrarEvento(CreateEventoDto createEventoDto) {
       return eventoService.registrarEvento(createEventoDto);
   }

   public Evento findById(Long id) {
       return eventoService.findById(id);
   }

    public List<Evento> findEventosByUser(User user) {
        return eventoService.findEventosByUser(user);
    }

   public Evento updateEvento(UpdateEventoDto updateEventoDto) {
       return eventoService.updateEvento(updateEventoDto);
   }

   public UserEventDto participarEvento(Evento evento, User user) {
       return eventoService.adicionarParticipante(evento, user);
   }
}
