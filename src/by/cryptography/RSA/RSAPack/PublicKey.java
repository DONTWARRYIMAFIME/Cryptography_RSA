package by.cryptography.RSA.RSAPack;

import java.math.BigInteger;

public class PublicKey {
    private final BigInteger d;
    private final BigInteger r;

    public PublicKey(BigInteger d, BigInteger r) {
        this.d = d;
        this.r = r;
    }

    public String encrypt (String str) {
        StringBuilder result = new StringBuilder();
        int len = str.length();

        for (int i = 0; i < len; i++) {
            result.append(RSAUtils.fastExp(BigInteger.valueOf(str.charAt(i)), d, r));
            result.append("-");
        }

        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }
}
