package by.cryptography.RSA.RSAPack;

public class PrivateKey {
    private final long e;
    private final long r;

    public PrivateKey(long e, long r) {
        this.e = e;
        this.r = r;
    }

    public String decrypt (String str) {
        StringBuilder result = new StringBuilder();
        String[] symbols = str.split("-");

        for (String s : symbols) {
            char symbol = (char) (RSAUtils.fastExp(Integer.parseInt(s), e, r));

            if ('-' != symbol)
                result.append(symbol);
        }

        return result.toString();
    }
}
