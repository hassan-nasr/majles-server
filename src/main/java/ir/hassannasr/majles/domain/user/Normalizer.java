package ir.hassannasr.majles.domain.user;

/**
 * Created by hassan on 30/12/2015.
 */
public class Normalizer {

    private String defaultCountryCode = "98";

    public String normalizePhone(String input) {
        if (input.length() < 4)
            return input;
        if (input.startsWith("+"))
            input = "00" + input.substring(1);
        if (input.charAt(0) == '0' && input.charAt(1) != '0')
            input = "00" + defaultCountryCode + input.substring(1);
        return input;

    }
}
