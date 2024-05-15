import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Hash {

    /**
     * Encode string using SHA-256 algorithm
     * @param input String to encode
     * @return Encoded {@code BigInteger}
     */
    public static BigInteger encode(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
            BigInteger output = new BigInteger(1, hashBytes);
            return output;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }

    }


    public static void main(String[] args) {
        String input = "Hello, World!";
        BigInteger hash = encode(input);
        System.out.println("SHA-256 hash of '" + input + "': " + hash.toString(16));
    }
}

