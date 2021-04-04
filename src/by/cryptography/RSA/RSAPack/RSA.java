package by.cryptography.RSA.RSAPack;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {

    private final Scanner scanner;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    private BigInteger getPrimeNumber() {
        while (true) {
            long input = scanner.nextLong();
            BigInteger bigInteger = BigInteger.valueOf(input);

            if (RSAUtils.isPrime(bigInteger)) {
                return bigInteger;
            }

            System.out.format("Looks like %d isn't a prime number. Try again\n", bigInteger);
        }
    }

    private void startCalculations(BigInteger p, BigInteger q) {
        BigInteger r = p.multiply(q);
        BigInteger f = RSAUtils.calculateTheEulerFunction(p, q);

        BigInteger e = RSAUtils.generateE(f);
        BigInteger d = RSAUtils.generateD(f, e);

        System.out.format("p: %d\nq: %d\nr: %d\ne: %d\nd: %d\n", p, q, r, e, d);

        publicKey = new PublicKey(d, r);
        privateKey = new PrivateKey(e, r);

        System.out.println("Enter a message");
        scanner.nextLine();
        String message = scanner.nextLine();

        String encryptedStr = publicKey.encrypt(message);
        System.out.println(encryptedStr);

        String decryptedStr = privateKey.decrypt(encryptedStr);
        System.out.println(decryptedStr);
    }

    public RSA(Scanner scanner) {
        this.scanner = scanner;
    }

    public void manualGeneration() {
        System.out.println("Enter a prime number for 'p'");
        BigInteger p = getPrimeNumber();

        System.out.println("Enter a prime number for 'q'");
        BigInteger q = getPrimeNumber();

        if (p.equals(q)) {
            System.out.println("Numbers 'p' and 'q' must be unique. Try again");
            return;
        }

        startCalculations(p, q);
    }

    public void automaticGeneration() {

        Random random = new Random();

        BigInteger p = BigInteger.probablePrime(1024, random);
        BigInteger q = BigInteger.probablePrime(1024, random);

        startCalculations(p, q);
    }

}
