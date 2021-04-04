package by.cryptography.RSA.RSAPack;

import java.util.HashSet;
import java.util.Set;

public class RSAUtils {

    public static long calculateTheEulerFunction(long p, long q) {
        return (p - 1) * (q - 1);
    }

    public static long fastExp(long number, long power, long module) {
        long tempNumber = number;
        long tempPower = power;
        long result = 1;

        while (tempPower != 0) {
            while (tempPower % 2 == 0) {
                tempPower /= 2;
                tempNumber *= tempNumber;
                tempNumber %= module;
            }
            tempPower--;
            result = (result * tempNumber) % module;
        }

        return result;
    }

    public static boolean isPrime(long number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) return false;
        }

        return number > 1;
    }

    public static boolean isCoprime(long a, long b) {
        if (a == b) {
            return a == 1;
        } else {
            if (a > b) {
                return isCoprime(a - b, b);
            } else {
                return isCoprime(b - a, a);
            }
        }
    }

    public static long generateE(long f) {
        Set<Long> set = new HashSet<>();

        for (long e = 2; e < f - 1; e++) {
            if (isPrime(e) && isCoprime(e, f)) {
                set.add(e);
            }
        }

        return set.iterator().next();
    }

    public static long generateD(long a, long b) {
        long d0 = a; long d1 = b;
        long x0 = 1; long x1 = 0;
        long y0 = 0; long y1 = 1;

        while (d1 > 1) {
            long q = d0 / d1;
            long d2 = d0 % d1;
            long x2 = x0 - q * x1;
            long y2 = y0 - q * y1;

            d0 = d1; d1 = d2;
            x0 = x1; x1 = x2;
            y0 = y1; y1 = y2;
        }

        return y1 > 0 ? y1 : y1 + a;
    }

}
