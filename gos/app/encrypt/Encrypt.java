package gos.app.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Encrypt {
    private static MessageDigest md;
    
    public static String crypt(String algorithm, String text) {
        try {
            md = MessageDigest.getInstance(algorithm);
            byte[] textBytes = text.getBytes();
            md.reset();
            byte[] digested = md.digest(textBytes);
            String s = new String();
            for (int i = 0; i < digested.length; i++) {
            	String current = Integer.toHexString(0xff & digested[i]).toString();
                switch (current.length()) {
                    case 1: s = s + "0" + current; break;
                    case 2: s += current; break;
                    default: break;
                }
            }
            return s;
        } catch (NoSuchAlgorithmException ex) {
        }
        return null;
    }
}
