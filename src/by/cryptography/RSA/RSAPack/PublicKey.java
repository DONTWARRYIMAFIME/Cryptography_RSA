package by.cryptography.RSA.RSAPack;

public class PublicKey {
    private final long d;
    private final long r;

    public PublicKey(long d, long r) {
        this.d = d;
        this.r = r;
    }

    public String encrypt (String str) {
        StringBuilder result = new StringBuilder();
        int len = str.length();

        for (int i = 0; i < len; i++) {
            result.append(RSAUtils.fastExp((int)str.charAt(i), d, r));
            result.append("-");
        }

        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }
}
