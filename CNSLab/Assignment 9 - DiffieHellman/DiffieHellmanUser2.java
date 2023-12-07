import java.io.*;
import java.net.*;
import java.math.BigInteger;

public class DiffieHellmanUser2 {
    public static BigInteger generateKeyPair(BigInteger prime, BigInteger primitiveRoot) {
        System.out.print("Enter your (User2's) private key (Xb): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BigInteger Xb = null;
        try {
            Xb = new BigInteger(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BigInteger Yb = primitiveRoot.modPow(Xb, prime);
        System.out.println("User2's Public key is (Yb): " + Yb);
        return Xb;
    }

    public static BigInteger calculateKey(BigInteger privateKey, BigInteger otherPublicKey, BigInteger prime) {
        BigInteger key = otherPublicKey.modPow(privateKey, prime);
        return key;
    }

    public static void main(String[] args) {
        System.out.print("Enter a prime number (P): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BigInteger prime = null;
        try {
            prime = new BigInteger(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Enter a primitive root modulo (Prime no. > primitive root modulo)(α): ");
        BigInteger primitiveRoot = null;
        try {
            primitiveRoot = new BigInteger(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // User2's side
        BigInteger Xb = generateKeyPair(prime, primitiveRoot);

        // Create a socket
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("0.0.0.0", 1030); // Change this to match Alice's server address
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            try {
                // Receive Alice's public key
                BigInteger alicePublicKey = (BigInteger) in.readObject();

                // Send User2's public key to Alice
                out.writeObject(Xb);

                // Shared secret calculation on User2's side
                BigInteger k2 = calculateKey(Xb, alicePublicKey, prime);
                System.out.println("User2's shared secret key is (K2): " + k2);

                int a = Integer.(Math.pow(4, 2));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                // Clean up the connection
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
