package by.cryptography.RSA.RSAPack;

import java.math.BigInteger;

public class PrivateKey {
    private final BigInteger e;
    private final BigInteger r;

    public PrivateKey(BigInteger e, BigInteger r) {
        this.e = e;
        this.r = r;
    }

    public String decrypt (String str) {
        StringBuilder result = new StringBuilder();
        String[] symbols = str.split("-");

        for (String s : symbols) {
            char symbol = (char) (RSAUtils.fastExp(new BigInteger(s), e, r)).intValue();

            if ('-' != symbol)
                result.append(symbol);
        }

        return result.toString();
    }
}
