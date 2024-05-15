import java.math.BigInteger;

public class Verifier {
    public BigInteger decryptMessage(Signature signature, RSAPublicKey publicKey) {
        BigInteger decryptedHash = signature.signedHash.modPow(publicKey.e, publicKey.n);
        return decryptedHash;
    }
    
    public boolean verifyMessage(Signature signature, RSAPublicKey publicKey) {
        BigInteger decryptedHash = signature.signedHash.modPow(publicKey.e, publicKey.n);
        return decryptedHash.equals(signature.messageHash);
    }
}
