package ifpe.edu.authentication;

import ifpe.edu.user.User;

public final class AuthRepository {
    private static AuthRepository instance;
    User loggedUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public static AuthRepository getInstance() {
        if (instance == null) {
            instance = new AuthRepository();
        }

            return instance;
    }

    public void setLoggedUser(User user) {
        loggedUser = user;
    }
}
