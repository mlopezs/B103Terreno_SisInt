package b1_03.utilidades;

import java.math.BigInteger;
import java.security.MessageDigest;
import static java.security.MessageDigest.getInstance;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author pacog
 */
public class Hash {

    /**
     * md5(..) toma una cadena de texto y devuelve un hash md5.
     *
     * @param entrada
     * @return String
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String entrada) throws NoSuchAlgorithmException {
        MessageDigest m = getInstance("MD5");
        m.update(entrada.getBytes(), 0, entrada.length());
        return new BigInteger(1, m.digest()).toString(16);
    }

    private Hash() {
    }
}
