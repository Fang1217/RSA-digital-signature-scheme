import java.math.BigInteger;

public class Verifier {
    public static BigInteger encodeMessage(String message) {
        return SHA256Hash.encode(message);
    }

    public static BigInteger decryptMessage(Signature signature, RSAPublicKey publicKey) {
        BigInteger decryptedHash = signature.signedHash.modPow(publicKey.e, publicKey.n);
        return decryptedHash;
    }
    
    public static boolean verifyMessage(Signature signature, RSAPublicKey publicKey) {
        BigInteger decryptedHash = signature.signedHash.modPow(publicKey.e, publicKey.n);
        return decryptedHash.equals(SHA256Hash.encode(signature.messageString));
    }
}
