import java.math.BigInteger;
import java.util.Scanner;

public class Signer {
    private RSAKeyPair rsaKeyPair;

    Signer() {
        rsaKeyPair = new RSAKeyPair();
        rsaKeyPair.generateKeys();
        //System.out.printf("Modulo: %s\nEncryption: %s\nDecryption:%s\n", n, e, d);
    }

    public BigInteger encode(String message) {
        return SHA256Hash.encode(message);
    }

    public Signature signMessage(BigInteger messageHash) {
        BigInteger signedHash = messageHash.modPow(rsaKeyPair.privateKey.d, rsaKeyPair.privateKey.n);
        return new Signature(messageHash, signedHash);
    }

    public RSAPublicKey getPublicKey() {
        return rsaKeyPair.publicKey;
    }

    
}

class Signature {
    BigInteger messageHash;
    BigInteger signedHash;
    
    Signature(BigInteger messageHash, BigInteger signedHash) {
        this.messageHash = messageHash;
        this.signedHash = signedHash;
    }
}