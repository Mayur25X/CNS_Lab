import java.math.BigInteger;
import java.util.Scanner;

public class RSAForTextMessage {

    static BigInteger gcd(BigInteger a, BigInteger b) {
        if (a.equals(BigInteger.ZERO)) {
            return b;
        }
        return gcd(b.mod(a), a);
    }

    static BigInteger modInverse(BigInteger a, BigInteger m) {
        a = a.mod(m);
        for (BigInteger x = BigInteger.ONE; x.compareTo(m) < 0; x = x.add(BigInteger.ONE)) {
            if (a.multiply(x).mod(m).equals(BigInteger.ONE)) {
                return x;
            }
        }
        return BigInteger.ONE;
    }

    static BigInteger power(BigInteger x, BigInteger y, BigInteger p) {
        BigInteger res = BigInteger.ONE;
        x = x.mod(p);

        while (y.compareTo(BigInteger.ZERO) > 0) {
            if (y.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) {
                res = res.multiply(x).mod(p);
            }
            y = y.shiftRight(1);
            x = x.multiply(x).mod(p);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the value of p (prime number): ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter the value of q (prime number): ");
        BigInteger q = sc.nextBigInteger();

        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = BigInteger.TWO;
        while (e.compareTo(phi) < 0) {
            if (gcd(e, phi).equals(BigInteger.ONE)) {
                break;
            }
            e = e.add(BigInteger.ONE);
        }

        BigInteger d = modInverse(e, phi);

        System.out.println("\nPublic Key: {" + e + ", " + n + "}");
        System.out.println("Private Key: {" + d + ", " + n + "}");

        sc.nextLine(); // Clear the scanner's buffer

        System.out.print("\nEnter a message to encrypt: ");
        String message = sc.nextLine();

        // Encrypt the message
        StringBuilder encryptedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            BigInteger encryptedValue = power(BigInteger.valueOf(c), e, n);
            encryptedMessage.append(encryptedValue).append(" ");
        }

        System.out.println("Encrypted Message: " + encryptedMessage);

        // Decrypt the message
        StringBuilder decryptedMessage = new StringBuilder();
        String[] encryptedValues = encryptedMessage.toString().split(" ");
        for (String value : encryptedValues) {
            if (!value.isEmpty()) {
                BigInteger decryptedValue = power(new BigInteger(value), d, n);
                decryptedMessage.append((char) Integer.parseInt(decryptedValue.toString()));
            }
        }

        System.out.println("Decrypted Message: " + decryptedMessage);

        sc.close();
    }
}