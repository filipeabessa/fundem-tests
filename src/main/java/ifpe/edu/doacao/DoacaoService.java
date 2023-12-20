package ifpe.edu.doacao;

import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.doacao.dtos.CreateDoacaoDto;
import ifpe.edu.doacao.dtos.UpdateDoacaoDto;
import ifpe.edu.user.User;

import java.util.List;

public class DoacaoService {

    DoacaoRepository doacaoRepository;

    public DoacaoService(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
    }

    public Doacao registrarDoacao(CreateDoacaoDto createDoacaoDto) {

       Doacao doacao = new Doacao(createDoacaoDto);
       return doacaoRepository.save(doacao);
    }

    public Doacao findById(Long id) {
        return doacaoRepository.findById(id);
    }

    public List<Doacao> findDoacoesByUser(User user) {
        return doacaoRepository.findDoacoesByUser(user);
    }
    public void deleteById(Long id) {
        doacaoRepository.deleteById(id);
    }

    public Doacao updateDoacao(UpdateDoacaoDto updateDoacaoDto) {
        Doacao doacao = doacaoRepository.findById(updateDoacaoDto.id());

        if(doacao == null) {
            throw new ValidationException("Erro. Doacao nao encontrada!");
        }

        doacao.setQuantidade(updateDoacaoDto.quantidade());
        doacao.setDescricaoObjeto(updateDoacaoDto.descricaoObjeto());
        doacao.setValidade(updateDoacaoDto.validade());
        doacao.setTipoObjeto(updateDoacaoDto.tipoObjeto());

        return doacaoRepository.save(doacao);
    }
}
