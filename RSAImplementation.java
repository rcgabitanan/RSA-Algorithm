import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSAImplementation {

    private BigInteger n;
    private BigInteger e;
    private BigInteger d;

    public RSAImplementation(int bitLength) {
        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);
        n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.valueOf(65537); // Commonly used prime number for e
        d = e.modInverse(phi);
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }

    public static BigInteger encrypt(String message, BigInteger e, BigInteger n) {
        return new BigInteger(message.getBytes()).modPow(e, n);
    }

    public static String decrypt(BigInteger encryptedMessage, BigInteger d, BigInteger n) {
        BigInteger decryptedBigInt = encryptedMessage.modPow(d, n);
        return new String(decryptedBigInt.toByteArray());
    }

    public static void main(String[] args) {
        RSAImplementation keyPair = new RSAImplementation(2048);
        BigInteger n = keyPair.getN();
        BigInteger e = keyPair.getE();
        BigInteger d = keyPair.getD();

        System.out.println("Public Key: (n = " + n + ", e = " + e + ")\n");
        System.out.println("Private Key: (n = " + n + ", d = " + d + ")\n");

        String s;
        Scanner save = new Scanner(System.in);
        System.out.println("Input the message that you want to send: ");
        s = save.nextLine();

        String message = s;

        BigInteger encryptedMessage = encrypt(message, e, n);
        System.out.println("\nEncrypted Message: " + encryptedMessage);

        String decryptedMessage = decrypt(encryptedMessage, d, n);
        System.out.println("\nDecrypted Message: " + decryptedMessage);
    }
}
