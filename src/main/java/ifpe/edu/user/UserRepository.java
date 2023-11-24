package ifpe.edu.user;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

}
