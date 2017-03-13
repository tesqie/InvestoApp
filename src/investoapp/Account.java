package investoapp;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author tesqie
 */
public class Account implements Serializable {

    private String username;
    private String password;
    private String email;
    private ArrayList<Portfolio> portfolio;

    public Account() {
    }

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return getUsername() + "=" + getPassword();
    }

    public void save(ArrayList<Account> accounts) {
        try {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("creds.ser"))) {
                out.writeObject(accounts);
            }

        } catch (IOException e) {
        }
    }

    public ArrayList<Account> load() throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
        try (FileInputStream fis = new FileInputStream("creds.ser"); ObjectInputStream ois = new ObjectInputStream(fis)) {
            ArrayList<Account> accounts = (ArrayList<Account>) ois.readObject();
            return accounts;
        } catch (IOException ioe) {
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
        }
        Exception IllegalStateException = null;
        throw IllegalStateException;

    }
    public void newAccount() throws IOException, ClassNotFoundException, Exception {
        File varTmpDir = new File("creds.ser");
        boolean exists = varTmpDir.exists();
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Username");
        String uname = input.nextLine();
        System.out.println("Enter Password");
        String pass = input.nextLine();
        SHA256InJava sj = new SHA256InJava();
        String passwordHash = sj.getSHA256Hash(pass);
        System.out.println("Enter Email");
        String inputEmail = input.nextLine();

        if (!exists) {
            ArrayList<Account> accounts = new ArrayList<>();
            accounts.add(new Account(uname, passwordHash, inputEmail));
            save(accounts);
        } else {
            ArrayList<Account> accounts = load();
            accounts.add(new Account(uname, passwordHash, inputEmail));
            save(accounts);         
        }
    }

}
