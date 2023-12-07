package function.encrypt;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class encrypt {
    public static String encryptText(String text, String password) throws Exception {
        // Create a key based on the password and a random salt.
        DESKeySpec keySpec = new DESKeySpec(password.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(keySpec);

        // Create a cipher object and initialize it with the key.
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Encrypt the text.
        byte[] encryptedBytes = cipher.doFinal(text.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

        return encryptedText;
    }
    
}
