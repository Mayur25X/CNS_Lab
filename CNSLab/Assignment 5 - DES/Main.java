import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import function.decrypt.decrypt;
import function.encrypt.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws Exception {
        String text = "Walchand College of Engineering, Sangli";
        String password = "DemoPassword";

        // Encrypt the text.

        String encryptedText = encrypt.encryptText(text, password);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the text.
        String decryptedText = decrypt.decryptText(encryptedText, password);
        System.out.println("Decrypted Text: " + decryptedText);
    }

}