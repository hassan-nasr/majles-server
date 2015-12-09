package crowling;

import org.apache.lucene.analysis.fa.PersianNormalizer;

/**
 * Created by hassan on 09/12/2015.
 */
public class Utils {
    public static String persianNormalize(String a) {
        final char[] s = a.toCharArray();
        int newLen = new PersianNormalizer().normalize(s, a.length());
        return new String(s, 0, newLen).replace('ي', 'ی');
    }

    public String convertToEnglishDigits(String s) {
        Character[] englishDigit = new Character[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',};
        Character[] farsiDigit = new Character[]{'۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹',};
        return null;
    }

    public static String persianNormalize(String a) {
        final char[] s = a.toCharArray();
        int newLen = new PersianNormalizer().normalize(s, a.length());
        return new String(s, 0, newLen).replace('ي', 'ی');
    }
}
