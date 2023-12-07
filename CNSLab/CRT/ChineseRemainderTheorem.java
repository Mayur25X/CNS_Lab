import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChineseRemainderTheorem {
    public static long findMultiplicativeInverse(long a, long b) {
        long q, r, t1 = 0, t2 = 1, t, mainA = a;

        while (b > 0) {
            q = a / b;
            r = a % b;
            t = t1 - (t2 * q);

            a = b;
            b = r;
            t1 = t2;
            t2 = t;
        }

        if (t1 < 0) {
            t1 += mainA;
        }
        return t1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("________________________________________________________");
        System.out.println("Chinese Remainder Theorem Problem");
        System.out.println("________________________________________________________");
        System.out.println("Suppose that the equation needs to be in the form of X = a (mod m)");

        System.out.print("How many equations do you want to perform: ");
        int count = scanner.nextInt();
        System.out.println("________________________________________________________");

        long M = 1;
        List<Long> a = new ArrayList<>();
        List<Long> m = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            System.out.println("Equation No: " + (i + 1));
            System.out.print("Enter a: ");
            long aData = scanner.nextLong();
            System.out.print("Enter m: ");
            long mData = scanner.nextLong();
            a.add(aData);
            m.add(mData);
            System.out.println("____________________________________________________________________________________");
            M *= mData;
        }

        System.out.println("\nValue of M: " + M);

        List<Long> MVector = new ArrayList<>();
        List<Long> MinverseVector = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            MVector.add(M / m.get(i));
        }

        for (int i = 0; i < count; i++) {
            MinverseVector.add(findMultiplicativeInverse(m.get(i), MVector.get(i)));
        }

        long sum = 0;

        System.out.println("After calculations:");
        System.out.println(
                "______________________________________________________________________________________________");
        System.out.println("|   Eq. No   |   a[i]   |   m[i]   |   M[i]   |   M_inverse[i]   |");
        System.out.println(
                "______________________________________________________________________________________________");

        for (int i = 0; i < count; i++) {
            sum += a.get(i) * MVector.get(i) * MinverseVector.get(i);
            System.out.println("|   " + (i + 1) + "   |   " + a.get(i) + "   |   " + m.get(i) + "   |   "
                    + MVector.get(i) + "   |   " + MinverseVector.get(i) + "   |");
            System.out.println(
                    "______________________________________________________________________________________________");
        }

        System.out.println("Using the formula X = Σ(a[i] * m[i] * m^-1[i]) mod M");
        long ans = sum % M;
        System.out.println("Value of X is approximately equal to: " + ans);
        scanner.close();
    }
}