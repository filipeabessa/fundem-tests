package ifpe.edu.doacao;

import ifpe.edu.common.exceptions.ValidationException;
import ifpe.edu.doacao.dtos.CreateDoacaoDto;
import ifpe.edu.doacao.dtos.UpdateDoacaoDto;
import ifpe.edu.user.User;

import java.util.List;

public class DoacaoController {

    DoacaoService doacaoService;

    DoacaoFieldsValidator doacaoFieldsValidator = new DoacaoFieldsValidator();

    public DoacaoController(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }

    public void registerDoacao(CreateDoacaoDto createDoacaoDto) {

        doacaoFieldsValidator.validateFields(createDoacaoDto.validade());

        if(createDoacaoDto.tipoObjeto() == null || createDoacaoDto.tipoObjeto().isEmpty()) {
            throw new ValidationException("Erro. Campo 'Tipo Objeto' não foi inserido!");
        }

        if (createDoacaoDto.descricaoObjeto() == null || createDoacaoDto.descricaoObjeto().isEmpty()) {
            throw new ValidationException("Erro. Campo 'Descrição' não foi inserido!");
        }

        if (createDoacaoDto.quantidade() == null || createDoacaoDto.quantidade().isEmpty()) {
            throw new ValidationException("Erro. Campo 'Quantidade' não foi inserido!");
        }

        doacaoService.registrarDoacao(createDoacaoDto);
    }

    public Doacao findById(Long id) {
        return doacaoService.findById(id);
    }

    public List<Doacao> findDoacoesByUser(User user) {
        return doacaoService.findDoacoesByUser(user);
    }

    public void deleteById(Long id) {
        doacaoService.deleteById(id);
    }

    public Doacao updateDoacao(UpdateDoacaoDto updateDoacaoDto) {
        doacaoFieldsValidator.validateFields(updateDoacaoDto.validade());
        return doacaoService.updateDoacao(updateDoacaoDto);
    }
}
