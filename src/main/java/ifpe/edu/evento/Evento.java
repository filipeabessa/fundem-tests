package ifpe.edu.evento;

import ifpe.edu.evento.dtos.CreateEventoDto;
import ifpe.edu.evento.dtos.UpdateEventoDto;
import ifpe.edu.user.User;

import java.util.List;

public class Evento {

    Long id;

    String nomeEvento;

    String descricao;

    List<String> localizacao;

    double metaArrecadada;

    double metaEvento;

    List<User> participantes;

    public Evento(CreateEventoDto createEventoDto) {
        this.descricao = createEventoDto.descricao();
        this.nomeEvento = createEventoDto.nomeEvento();
        this.localizacao = createEventoDto.localizacao();
        this.metaEvento = createEventoDto.metaEvento();
    }

    public Evento(UpdateEventoDto updateEventoDto) {
        this.id = updateEventoDto.id();
        this.nomeEvento = updateEventoDto.nomeEvento();
        this.descricao = updateEventoDto.descricao();
        this.localizacao = updateEventoDto.localizacao();
        this.metaArrecadada = updateEventoDto.metaArrecadada();
        this.metaEvento = updateEventoDto.metaEvento();
        this.participantes = updateEventoDto.participantes();
    }

    public Evento() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(List<String> localizacao) {
        this.localizacao = localizacao;
    }

    public double getMetaArrecadada() {
        return metaArrecadada;
    }

    public void setMetaArrecadada(double metaArrecadada) {
        this.metaArrecadada = metaArrecadada;
    }

    public double getMetaEvento() {
        return metaEvento;
    }

    public void setMetaEvento(double metaEvento) {
        this.metaEvento = metaEvento;
    }

    public List<User> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<User> participantes) {
        this.participantes = participantes;
    }
}
