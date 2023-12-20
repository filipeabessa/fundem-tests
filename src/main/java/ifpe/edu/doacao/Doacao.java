package ifpe.edu.doacao;

import ifpe.edu.doacao.dtos.CreateDoacaoDto;
import ifpe.edu.doacao.dtos.UpdateDoacaoDto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Doacao {

    Long id;

    String tipoObjeto;

    String descricaoObjeto;

    LocalDateTime validade;

    String quantidade;

    public Doacao(CreateDoacaoDto createDoacaoDto) {
        this.tipoObjeto = createDoacaoDto.tipoObjeto();
        this.descricaoObjeto = createDoacaoDto.descricaoObjeto();
        this.validade = createDoacaoDto.validade();
        this.quantidade = createDoacaoDto.quantidade();
    }

    public Doacao(UpdateDoacaoDto updateDoacaoDto) {
        this.id = updateDoacaoDto.id();
        this.tipoObjeto = updateDoacaoDto.tipoObjeto();
        this.descricaoObjeto = updateDoacaoDto.descricaoObjeto();
        this.validade = updateDoacaoDto.validade();
        this.quantidade = updateDoacaoDto.quantidade();
    }

    public Doacao() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public String getDescricaoObjeto() {
        return descricaoObjeto;
    }

    public void setDescricaoObjeto(String descricaoObjeto) {
        this.descricaoObjeto = descricaoObjeto;
    }

    public LocalDateTime getValidade() {
        return validade;
    }

    public void setValidade(LocalDateTime validade) {
        this.validade = validade;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}

