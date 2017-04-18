package investoapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the user's account. Includes username,password, email and
 * portfolio. The class is serializable to persist user information to a file.
 *
 * @author Abdul Tesqie
 */
public class Account implements Serializable {

    private String username;
    private String password;
    private String email;
    private Portfolio portfolio;

    /**
     * Empty Account constructor to access the save and load methods for other
     * accounts
     */
    public Account() {
    }

    /**
     * Account constructor used when creating an account for the first time.
     *
     *
     * @param username User's username
     * @param password User's password
     * @param email User's email
     */
    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Saving an Array list of accounts to a file
     *
     *
     *
     * @param saveAccount the Account being added and saved to the array list
     * @param index The index of the account relative to the array list
     */
    public void save(Account saveAccount, int index) {

        File file = new File("creds.ser");
        ArrayList<Account> accounts;

        try {
            if (!file.exists()) {
                accounts = new ArrayList<>();
                accounts.add(saveAccount);

            } else {
                accounts = load();
                if (index == -1) {
                    accounts.add(saveAccount);
                } else if (index >= 0) {
                    accounts.set(index, saveAccount);
                }
            }

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("creds.ser"))) {
                out.writeObject(accounts);
            }

        } catch (IOException e) {
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Loading an array list of accounts to be used when a user is logging in
     *
     * @return an array list of all the accounts in the system
     */

    public ArrayList<Account> load() {

        try (FileInputStream fis = new FileInputStream("creds.ser");
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            ArrayList<Account> accounts = (ArrayList<Account>) ois.readObject();
            return accounts;

        } catch (IOException ioe) {
            System.out.println(ioe.toString());
        } catch (ClassNotFoundException c) {
            System.out.println(c.toString());
        }
        return null;
    }
}
