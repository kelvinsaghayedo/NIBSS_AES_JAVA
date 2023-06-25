/* AUTHOR: KELVINS EGHOSA AGHAYEDO, @eghenimose */

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.math.*;
	
	
public class NIBSS_ENCRYPT
{
    public static String payloadToBeEncrypted = "{\"Fessage\":\"string\",\"Amount\":0,\"HasError\":true,\"ErrorMessage\":\"Missing or invalid Signature header\",\"Params\":[]}";
    
    //replace your keys from NIBSS below here BEFORE running this code
    public static String key = "648c54a8dc34f9.7";
    public static String initVector = "yX+VIxM8ZJavnyLt";

    public static void main(String []args) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

            cipher.init(
                Cipher.ENCRYPT_MODE,
                new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"),
                new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8))
            );

            byte[] encrypted = cipher.doFinal(payloadToBeEncrypted.getBytes());
            String encryptedString = Base64.getEncoder().encodeToString(encrypted);
            byte[] encryptedDecoded = Base64.getDecoder().decode(encryptedString);

           String encryptedPayload = String.format("%040x", new BigInteger(1, encryptedDecoded));
           
           System.out.println("Encrypted to NIBSS Standard: " + encryptedPayload);
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     
}