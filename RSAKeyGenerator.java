import java.math.BigInteger;
import java.util.Random;

public class RSAKeyGenerator {

    private static final Integer RSA_KEY_SIZE = 1024;
    private static final Integer ENCRYPTION_EXPONENT = 65537;

    public static void generateKeys() {
        // Generate two distinct probably primes p and q.
        BigInteger p, q;
        Random random = new Random();
        p = BigInteger.probablePrime((RSA_KEY_SIZE/2), random);
        do 
        {
            q = BigInteger.probablePrime((RSA_KEY_SIZE/2), random);
        }
        while (p == q);

        // Calculate product of the keys, p and q.
        BigInteger product = p.multiply(q);

        System.out.println("\np:\n" + p);
        System.out.println("q:\n" + q);
        System.out.println("Product:\n" + product);
        
        // Calculate Euler Totient Function, phi(n)
        BigInteger eulerTotientFunction = 
            p.subtract(BigInteger.ONE).multiply
            (q.subtract(BigInteger.ONE));

        System.out.println("Euler Totient:\n" + eulerTotientFunction);
        
        // Validate Encryption Exponent, e is relatively prime to phi(n)
        if (!BigInteger.valueOf(ENCRYPTION_EXPONENT).gcd(eulerTotientFunction).equals(BigInteger.ONE)) {
            generateKeys(); // Regenerate new keys
            return;
        }
        
        // Decryption Exponent Calculation, d
        BigInteger d = BigInteger.valueOf(ENCRYPTION_EXPONENT).modInverse(eulerTotientFunction);
        System.out.println("Decryption Exponent:\n" + d);
        
        // Validate Decryption Exponent, where (d * e) mod phi(n) = 1.
        if (!d.multiply(BigInteger.valueOf(ENCRYPTION_EXPONENT))
                .mod(eulerTotientFunction)
                .equals(BigInteger.ONE)) {
            System.err.println("Something is wrong");
            return;
        }

        //

    }

    public static void main(String[] args) {
        generateKeys();
    }

} 