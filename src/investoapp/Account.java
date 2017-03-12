package investoapp;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
// Serialize data object to a file
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("creds.ser"));
            out.writeObject(accounts);
            out.close();

// Serialize data object to a byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bos);
            out.writeObject(accounts);
            out.close();

// Get the bytes of the serialized object
            byte[] buf = bos.toByteArray();
        } catch (IOException e) {
        }
    }
    public void load() throws FileNotFoundException, IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream("creds.ser"); ObjectInputStream ois = new ObjectInputStream(fis)) {
            ArrayList<Account> accounts = (ArrayList<Account>) ois.readObject();

        } catch (IOException ioe) {
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
        }
        //  System.out.println(accounts);
        // return accounts;
    }
   
}
