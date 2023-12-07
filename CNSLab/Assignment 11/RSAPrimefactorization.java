import java.util.Scanner;

public class RSAPrimefactorization {
    static long p, q, n, t, flag, e[], d[], temp[], j;
    static int m[], en[];
    static char[] msg;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nENTER FIRST PRIME NUMBER\n");
        p = sc.nextLong();
        flag = prime(p);
        if (flag == 0) {
            System.out.println("\nWRONG INPUT\n");
            System.exit(1);
        }
        System.out.println("\nENTER ANOTHER PRIME NUMBER\n");
        q = sc.nextLong();
        flag = prime(q);
        if (flag == 0 || p == q) {
            System.out.println("\nWRONG INPUT\n");
            System.exit(1);
        }
        System.out.println("\nENTER MESSAGE\n");
        msg = sc.next().toCharArray();
        m = new int[msg.length];
        for (int i = 0; i < msg.length; i++)
            m[i] = (int) msg[i];
        n = p * q;
        t = (p - 1) * (q - 1);
        ce();
        System.out.println("\nPOSSIBLE VALUES OF e AND d ARE\n");
        for (int i = 0; i < j - 1; i++)
            System.out.println(e[i] + "\t" + d[i] + "\n");
        encrypt();
        decrypt();
    }

    static int prime(long pr) {
        int i;
        j = (long) Math.sqrt(pr);
        for (i = 2; i <= j; i++) {
            if (pr % i == 0)
                return 0;
        }
        return 1;
    }

    static void ce() {
        int k;
        k = 0;
        e = new long[100];
        d = new long[100];
        for (long i = 2; i < t; i++) {
            if (t % i == 0)
                continue;
            flag = prime((int) i);
            if (flag == 1 && i != p && i != q) {
                e[k] = i;
                flag = cd(e[k]);
                if (flag > 0) {
                    d[k] = flag;
                    k++;
                }
                if (k == 99)
                    break;
            }
        }
    }

    static long cd(long x) {
        long k = 1;
        while (true) {
            k = k + t;
            if (k % x == 0)
                return (k / x);
        }
    }

    static void encrypt() {
        long pt, ct, key = e[0], k, len;
        int i = 0;
        len = msg.length;
        temp = new long[msg.length];
        en = new int[msg.length];
        while (i != len) {
            pt = m[i];
            pt = pt - 96;
            k = 1;
            for (j = 0; j < key; j++) {
                k = k * pt;
                k = k % n;
            }
            temp[i] = k;
            ct = k + 96;
            en[i] = (int) ct;
            i++;
        }
        en[i] = -1;
        System.out.println("\nTHE ENCRYPTED MESSAGE IS\n");
        for (i = 0; en[i] != -1; i++)
            System.out.printf("%c", en[i]);
    }

    static void decrypt() {
        long pt, ct, key = d[0], k;
        int i = 0;
        while (en[i] != -1) {
            ct = temp[i];
            k = 1;
            for (j = 0; j < key; j++) {
                k = k * ct;
                k = k % n;
            }
            pt = k + 96;
            m[i] = (int) pt;
            i++;
        }
        m[i] = -1;
        System.out.println("\nTHE DECRYPTED MESSAGE IS\n");
        for (i = 0; m[i] != -1; i++)
            System.out.printf("%c", m[i]);
    }
}
