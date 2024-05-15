import java.math.BigInteger;
import java.util.Scanner;

public class DSA {
    public static void main(String[] args) {
        
        Signer signer = new Signer();
        Verifier verifier = new Verifier();

        System.out.println("Input message");
        
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        BigInteger message = signer.encode(input);
        System.out.println("Message hash: " + message.toString(16));
        Signature signedSignature = signer.signMessage(message);
        System.out.println("Signed message: " + signedSignature.signedHash.toString(16));
        scanner.close();

        boolean verified = verifier.verifyMessage(signedSignature, signer.getPublicKey());
        System.out.println(verified ? "Digital Signature is verified." : "Can not authenticate the digital signature.");
    }        
}
