import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAKeyPair {
    RSAPublicKey publicKey;
    RSAPrivateKey privateKey;
    

    private static final Integer RSA_KEY_SIZE = 1024;
    private static final Integer ENCRYPTION_EXPONENT = 65537;

    /**
     * Generate keypair using RSA Algorithm.
     * 
     * @return Array of {@code BigInteger} of size 3, containing System modulo
     *         {@code n},
     *         Encryption exponent {@code e}, Decryption exponent {@code d}.
     * 
     */
    public void generateKeys() {
        BigInteger p, q;
        SecureRandom random = new SecureRandom();
        // Generate two distinct probably primes p and q.
        p = BigInteger.probablePrime((RSA_KEY_SIZE / 2), random);
        do {
            q = BigInteger.probablePrime((RSA_KEY_SIZE / 2), random);
        } while (p == q);

        // Calculate product of the keys, n by multiplying p and q.
        BigInteger n = p.multiply(q);

        // System.out.println("\np:\n" + p.toString(16));
        // System.out.println("q:\n" + q.toString(16));
        // System.out.println("Product:\n" + product.toString(16));

        // Calculate Euler Totient Function, phi(n)
        BigInteger eulerTotientFunction = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // System.out.println("Euler Totient:\n" + eulerTotientFunction.toString(16));

        // Validate Encryption Exponent, e is relatively prime to phi(n)
        if (!BigInteger.valueOf(ENCRYPTION_EXPONENT).gcd(eulerTotientFunction).equals(BigInteger.ONE)) {
            generateKeys(); // Regenerate new keys
            return;
        }
        BigInteger e = BigInteger.valueOf(ENCRYPTION_EXPONENT);

        // Decryption Exponent Calculation, d
        BigInteger d = BigInteger.valueOf(ENCRYPTION_EXPONENT).modInverse(eulerTotientFunction);
        // System.out.println("Decryption Exponent:\n" + d.toString(16));

        // Validate Decryption Exponent, where (d * e) mod phi(n) = 1.
        if (!d.multiply(BigInteger.valueOf(ENCRYPTION_EXPONENT))
                .mod(eulerTotientFunction)
                .equals(BigInteger.ONE)) {
            System.err.println("Something is wrong");
            generateKeys();
            return;
        }
        publicKey = new RSAPublicKey(e, n);
        privateKey = new RSAPrivateKey(d, n);
    }
    public static void main(String[] args) {
        RSAKeyPair keyPair = new RSAKeyPair();
        keyPair.generateKeys();
        BigInteger input = BigInteger.valueOf(69);
        BigInteger encrypt = input.modPow(keyPair.privateKey.d, keyPair.publicKey.n);
        System.out.println(encrypt);
        BigInteger decrypt = encrypt.modPow(keyPair.publicKey.e, keyPair.privateKey.n);
        System.out.println(decrypt);
    }
}

class RSAPublicKey {
    BigInteger e;
    BigInteger n;

    public RSAPublicKey(BigInteger e, BigInteger n) {
        this.e = e;
        this.n = n;
    }
}

class RSAPrivateKey {
    BigInteger d;
    BigInteger n;

    public RSAPrivateKey(BigInteger d, BigInteger n) {
        this.d = d;
        this.n = n;
    }
}