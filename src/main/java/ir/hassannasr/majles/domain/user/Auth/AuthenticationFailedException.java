package ir.hassannasr.majles.domain.user.Auth;

/**
 * Created by hassan on 02/11/2015.
 */
public class AuthenticationFailedException extends Exception {
    public AuthenticationFailedException(String s) {
        super(s);
    }
}
