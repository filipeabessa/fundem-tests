package ifpe.edu.common.dtos;

import ifpe.edu.evento.Evento;
import ifpe.edu.user.User;

public class UserEventDto {

    private User user;
    private Evento evento;

    public UserEventDto(User user, Evento evento) {
        this.user = user;
        this.evento = evento;
    }

    public User getUser() {
        return user;
    }

    public Evento getEvento() {
        return evento;
    }
}
