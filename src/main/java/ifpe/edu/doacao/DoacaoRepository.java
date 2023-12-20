package ifpe.edu.doacao;

import ifpe.edu.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class DoacaoRepository {

    private static DoacaoRepository instance;

    private final List<Doacao> doacoes = new ArrayList<>();

    public static DoacaoRepository getInstance() {
        if(instance == null) {
            instance = new DoacaoRepository();
        }

        return instance;
    }

    public Doacao save(Doacao doacao) {
        doacao.setId((long) (doacoes.size() + 1));
        doacoes.add(doacao);

        return doacao;
    }

    public List<Doacao> findAll() {
        return doacoes;
    }

    public Doacao findById(Long id) {
        return doacoes.stream()
                .filter(doacao -> doacao.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public List<Doacao> findDoacoesByUser(User user) {
        return doacoes.stream()
                .filter(doacao -> doacao.equals(user))
                .collect(Collectors.toList());
    }


    public void deleteById(Long id) {
        doacoes.removeIf(doacao -> doacao.getId().equals(id));
    }
}
