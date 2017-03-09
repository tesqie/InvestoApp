package investoapp;

/**
 *
 * @author tesqie
 */
public class Account {
    private final String username;
    private final String password;
    private final String email;

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
    
    
    
}
