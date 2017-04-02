package investoapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tesqie
 */
public class Account implements Serializable {

    private String username;
    private String password;
    private String email;
    private Portfolio portfolio;

    public Account() {
    }

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


//    @Override
//    public String toString() {
//        return getUsername() + "=" + getPassword();
//    }
    
    public void save(Account saveAccount,int index) {
        File file = new File("creds.ser");
        ArrayList<Account> accounts;

        try {
            if (!file.exists()) {
                accounts = new ArrayList<>();
                accounts.add(saveAccount);

            } else {
                accounts = load();
                if (index == -1){
                accounts.add(saveAccount);
                }else if (index >=0){
                accounts.set(index, saveAccount);
                }
                    
                
            }

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("creds.ser"))) {
                out.writeObject(accounts);
            }

        } catch (IOException e) {
        } catch (Exception ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Account> load() {
        
        
        try (FileInputStream fis = new FileInputStream("creds.ser");
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            ArrayList<Account> accounts = (ArrayList<Account>) ois.readObject();
            return accounts;

        } catch (IOException ioe) {
            System.out.println(ioe.toString());
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
        }
        return null;
    }

}
