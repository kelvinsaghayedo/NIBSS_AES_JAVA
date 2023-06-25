/* AUTHOR: KELVINS EGHOSA AGHAYEDO, @eghenimose */

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
 
public class NIBSS_DECRYPT
{  
    //replace your keys from NIBSS below here BEFORE running this code
    public static final String key = "yourKeyHere"; 
    public static final String initVector = "yourIVhere";
 
   public static void main(String[] args) {
       String originalString = "ce52d90be8b6f5d1e3ce7dc845cc73d12893fb088cdafdd345feb94c1f2454463afe33365385e7f831a1ebf6841678e19a5d13f16f8daafd585c238ba4d89f6c1c0bdbe9e3564e6e55cbec6180b66f91d5ca32550cb7db2fb22e085821de488e45d24f2cb618aaf7af30a1b032c48f28b16185f805bfa588af2d3f20289e9b02";
       String decryptedString = decrypt(originalString);
       System.out.println("After decryption - " + decryptedString);
   }
    
   public static String decrypt(String encrypted) {
       try {
           IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
           SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
    
           Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
           cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv); 
           
            byte[] bytes = hexStringToByteArray(encrypted);
            String decryptedBase64 = Base64.getEncoder().encodeToString(bytes);

            byte[] encryptedDecoded = Base64.getDecoder().decode(decryptedBase64);
            byte[] original = cipher.doFinal(encryptedDecoded); 

           return new String(original);
       } catch (Exception ex) {
           ex.printStackTrace();
       }
    
       return null;
   }
   
    public static byte[] hexStringToByteArray(String s) 
    {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }   
    
}