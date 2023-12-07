import java.math.BigDecimal;
import java.util.Scanner;

public class ExtendedEuclideanAlgorithm {
    public static BigDecimal findLargeNumberGCD(BigDecimal a, BigDecimal b) {
        BigDecimal q, r;
        System.out.println("-".repeat(93));
        System.out.println("|      Q      |      A      |      B      |      R      |");
        System.out.println("-".repeat(93));

        while (b.compareTo(BigDecimal.ZERO) > 0) {
            q = a.divideToIntegralValue(b);
            r = a.remainder(b);
            System.out.printf("|%13s|%13s|%13s|%13s|\n", q, a, b, r);
            a = b;
            b = r;
        }

        System.out.println("-".repeat(93));
        return a;
    }

    public static void findMultiplicativeInverse(BigDecimal a, BigDecimal b) {
        BigDecimal q, r, t1, t2, t, mainA, A1, B1;
        q = r = t1 = BigDecimal.ZERO;
        t2 = BigDecimal.ONE;
        t = BigDecimal.ZERO;
        mainA = A1 = a;
        B1 = b;

        System.out.println("-".repeat(121));
        System.out.println(
                "|      Q      |      A      |      B      |      R      |      T1     |      T2     |      T      |");
        System.out.println("-".repeat(121));

        while (b.compareTo(BigDecimal.ZERO) > 0) {
            q = a.divideToIntegralValue(b);
            r = a.remainder(b);
            t = t1.subtract(t2.multiply(q));
            System.out.printf("|%13s|%13s|%13s|%13s|%13s|%13s|%13s|\n", q, a, b, r, t1, t2, t);
            a = b;
            b = r;
            t1 = t2;
            t2 = t;
        }

        System.out.printf("|%13s|%13s|%13s|%13s|%13s|%13s|%13s|\n", q, a, b, r, t1, t2, t);
        System.out.println("-".repeat(121));

        if (b.compareTo(BigDecimal.ZERO) != 0) {
            System.out.println("The numbers are not relatively prime, so no multiplicative inverse exists.");
        } else {
            t1 = t1.remainder(mainA); // Ensure the result is non-negative
            System.out.println("The multiplicative inverse of " + A1 + " modulo " + B1 + " is: " + t1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1) Euclidean Algorithm (GCD)");
            System.out.println("2) Extended Euclidean Algorithm (Multiplicative Inverse)");
            System.out.println("3) Exit");
            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.println("You can enter numbers with up to 100 digits.");
                System.out.print("Enter the first number (A): ");
                BigDecimal a = new BigDecimal(scanner.nextLine());
                System.out.print("Enter the second number (B): ");
                BigDecimal b = new BigDecimal(scanner.nextLine());
                BigDecimal result = findLargeNumberGCD(a, b);
                System.out.println("\nThe greatest common divisor (GCD) of " + a + " and " + b + " is: " + result);
            } else if (choice.equals("2")) {
                System.out.println(
                        "You can enter numbers with up to 100 digits. Note: A must be greater than B (A > B).");
                System.out.print("Enter the first number (A): ");
                BigDecimal a = new BigDecimal(scanner.nextLine());
                System.out.print("Enter the second number (B): ");
                BigDecimal b = new BigDecimal(scanner.nextLine());
                findMultiplicativeInverse(a, b);
            } else if (choice.equals("3")) {
                System.out.println("Exiting the program.");
                break;
            } else {
                System.out.println("Invalid Input! Please select a valid option.");
            }
        }
    }
}