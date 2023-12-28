package ifpe.edu.user;

import ifpe.edu.doacao.Doacao;
import ifpe.edu.doacao.DoacaoController;
import ifpe.edu.doacao.DoacaoRepository;
import ifpe.edu.evento.Evento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class UserRepository {
    private static UserRepository instance;
    private final List<User> users = new ArrayList<>();

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }

        return instance;
    }

    public User save(User user) {
        user.setId((long) (users.size() + 1));
        users.add(user);

        return user;
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User findByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public List<User> findUsersByEvento(Evento evento) {
        return users.stream()
                .filter(user -> user.getEventos().contains(evento))
                .collect(Collectors.toList());

    }

    public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }


}
