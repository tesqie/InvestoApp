package investoapp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 * To encrypt user's password's when creating and saving accounts.
 *
 * @author Abdul Tesqie
 */
public class SHA256InJava {

    /**
     * Encrypting a string into a password hash SHA-256
     *
     * @param data the data being encrypted
     * @return the hashed password in a hexadecimal string all upper case
     */
    String getSHA256Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash); // make it printable
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
        }
        return result;
    }

    /**
     *
     * converting byte array to a hexadecimal string in upper case
     *
     * @param hash the password hash to be converted
     * @return
     */
    private String bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
}
