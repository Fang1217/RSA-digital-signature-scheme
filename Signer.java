import java.math.BigInteger;

public class Signer {
    RSAKeyPair rsaKeyPair;

    Signer() {
        rsaKeyPair = new RSAKeyPair();
        rsaKeyPair.generateKeys();
        //System.out.printf("Modulo: %s\nEncryption: %s\nDecryption:%s\n", n, e, d);
    }

    public BigInteger encode(String message) {
        return SHA256Hash.encode(message);
    }

    public Signature signMessage(String message) {
        BigInteger messageHash = encode(message);
        BigInteger signedHash = messageHash.modPow(rsaKeyPair.privateKey.d, rsaKeyPair.privateKey.n);
        return new Signature(message, signedHash);
    }

    public RSAPublicKey getPublicKey() {
        return rsaKeyPair.publicKey;
    }

    
}

class Signature {
    String messageString;
    BigInteger signedHash;
    
    Signature(String messageString, BigInteger signedHash) {
        this.messageString = messageString;
        this.signedHash = signedHash;
    }
}