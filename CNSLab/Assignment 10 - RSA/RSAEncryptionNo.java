import java.util.Scanner;

public class RSAEncryptionNo {

    static long gcd(long a, long b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    static long modInverse(long a, long m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }

    static long power(long x, long y, long p) {
        long res = 1;
        x = x % p;

        while (y > 0) {
            if (y % 2 == 1) {
                res = (res * x) % p;
            }
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the value of p (prime number): ");
        long p = sc.nextLong();

        System.out.print("Enter the value of q (prime number): ");
        long q = sc.nextLong();

        long n = p * q;
        long phi = (p - 1) * (q - 1);

        long e = 2;
        while (e < phi) {
            if (gcd(e, phi) == 1) {
                break;
            }
            e++;
        }

        long d = modInverse(e, phi);

        System.out.println("\nPublic Key: {" + e + ", " + n + "}");
        System.out.println("Private Key: {" + d + ", " + n + "}");

        System.out.print("\nEnter a message to encrypt: ");
        String message = sc.next();

        // Convert message to a numerical representation for encryption
        long numericalMessage = 0;
        for (char c : message.toCharArray()) {
            numericalMessage = numericalMessage * 100 + (c - 'A' + 1); // considering uppercase letters
        }

        long encrypted_message = power(numericalMessage, e, n);
        System.out.println("Encrypted Message: " + encrypted_message);

        long decrypted_message = power(encrypted_message, d, n);
        System.out.println("Decrypted Message: " + decrypted_message);

        sc.close();
    }
}
