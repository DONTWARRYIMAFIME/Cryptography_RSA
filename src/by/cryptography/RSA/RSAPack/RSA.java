package by.cryptography.RSA.RSAPack;

import java.util.Scanner;

public class RSA {

    private final Scanner scanner;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    private long getPrimeNumber() {
        while (true) {
            long number = scanner.nextLong();

            if (RSAUtils.isPrime(number)) {
                return number;
            }

            System.out.format("Looks like %d isn't a prime number. Try again\n", number);
        }
    }

    public RSA(Scanner scanner) {
        this.scanner = scanner;
    }

    public void automaticGeneration() {


    }

    public void manualGeneration() {
        System.out.println("Enter a prime number for 'p'");
        long p = getPrimeNumber();

        System.out.println("Enter a prime number for 'q'");
        long q = getPrimeNumber();

        if (p == q) {
            System.out.println("Numbers 'p' and 'q' must be unique. Try again");
            return;
        }

        long r = p * q;
        long f = RSAUtils.calculateTheEulerFunction(p, q);

        long e = RSAUtils.generateE(f);
        long d = RSAUtils.generateD(f, e);

        //System.out.format("p: %d\nq: %d\nr: %d\ne: %d\nd: %d\n", p, q, r, e, d);

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

}
