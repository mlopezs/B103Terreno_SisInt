/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
package b1_03.utilidades;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    /**
     * md5(String entrada) toma una cadena de texto y devuelve un hash md5.
     *
     * @param entrada
     * @return String
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String entrada) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(entrada.getBytes(), 0, entrada.length());
        return new BigInteger(1, m.digest()).toString(16);
    }
}
