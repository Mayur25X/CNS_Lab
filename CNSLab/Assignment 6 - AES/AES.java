
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

public class AES {
     public static SecretKey generateAESKey(int keySize) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(keySize);
        return keyGenerator.generateKey();
    }

    public static byte[] encrypt(String plaintext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(plaintext.getBytes("UTF-8"));
    }

    public static String decrypt(byte[] ciphertext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plaintextBytes = cipher.doFinal(ciphertext);
        return new String(plaintextBytes, "UTF-8");
    }

    public static void main(String[] args) {
        int keySize = 128;

        try {
            SecretKey secretKey = generateAESKey(keySize);
            String textToEncrypt = "I'm AES";
            System.out.println("Original text: " + textToEncrypt);

            byte[] encryptedBytes = encrypt(textToEncrypt, secretKey);
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("Encrypted text: " + encryptedText);

            byte[] decryptedBytes = Base64.getDecoder().decode(encryptedText);
            String decryptedText = decrypt(decryptedBytes, secretKey);
            System.out.println("Decrypted text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
