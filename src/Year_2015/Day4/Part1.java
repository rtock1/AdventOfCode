package Year_2015.Day4;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part1 {
    public String start(String input){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        for (int i=0;i<200000;i++) {
            md.update((input + (i + "")).getBytes());
            byte[] digest = md.digest();
            String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            if (hash.startsWith("00000")) {
                return i + "";
            }
        }
        return "";
    }
}
