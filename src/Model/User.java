package Model;

/**
 * The User class defines an instance of a user.
 */
public class User {

    private final int userId;
    private final String userName;
    private final String password;

    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     *
     * @param userNameProvided the username
     * @param passwordProvided the password
     * @return true if x.userName = x.password
     */
    public boolean credentialsValid(String userNameProvided, String passwordProvided) {
        return userName.equals(userNameProvided) && password.equals(passwordProvided);
    }
}
