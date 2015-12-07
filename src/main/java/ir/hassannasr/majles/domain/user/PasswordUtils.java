package ir.hassannasr.majles.domain.user;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * a class for hashing user password based on SHA-512
 * Created by hassan on 02/11/2015.
 */
public class PasswordUtils {
    private static  final String charsetName = "UTF-8";

    public  String hashPassword(String salt, String password) {
        String bigpassword = salt + password;
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-512");
            String digest = new String(instance.digest(bigpassword.getBytes()), charsetName);
            return Base64.getEncoder().encodeToString(digest.getBytes(charsetName));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            Logger.getLogger(PasswordUtils.class).error("could not hash password",e);
            e.printStackTrace();
        }
        return null;
    }
}
