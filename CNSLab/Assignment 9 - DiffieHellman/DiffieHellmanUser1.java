import java.io.*;
import java.net.*;
import java.math.BigInteger;

public class DiffieHellmanUser1 {
    public static BigInteger generateKeyPair(BigInteger prime, BigInteger primitiveRoot) {
        System.out.print("Enter your (User1 : XA) private key: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BigInteger XA = null;
        try {
            XA = new BigInteger(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BigInteger YA = primitiveRoot.modPow(XA, prime);
        System.out.println("User1's Public Key (YA) is " + YA);
        return XA;
    }

    public static BigInteger calculateSharedSecret(BigInteger XA, BigInteger otherPublicKey, BigInteger prime) {
        BigInteger sharedSecret = otherPublicKey.modPow(XA, prime);
        return sharedSecret;
    }

    public static void main(String[] args) {
        System.out.print("Enter a Prime Number (P): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BigInteger prime = null;
        try {
            prime = new BigInteger(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Enter a primitive root modulo i.e α (prime number > primitive root modulo): ");
        BigInteger primitiveRoot = null;
        try {
            primitiveRoot = new BigInteger(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // User1's side
        BigInteger User1PrivateKey = generateKeyPair(prime, primitiveRoot);

        // Create a server socket
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(1030);  // Change to a suitable port number

            System.out.println("Waiting for User2 to connect...");

            Socket connection = serverSocket.accept();

            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

            try {
                // Send User1's public key to User2
                out.writeObject(User1PrivateKey);

                // Receive User2's public key
                BigInteger User2PublicKey = (BigInteger) in.readObject();

                // Shared secret calculation on User1's side
                BigInteger User1SharedSecret = calculateSharedSecret(User1PrivateKey, User2PublicKey, prime);
                System.out.println("User1's shared secret Key (K1) is: " + User1SharedSecret);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
