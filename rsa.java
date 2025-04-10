import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class rsa {
    private BigInteger p, q, n, phi, e, d;
    private int bitLength = 1024;
    private Random random = new Random();

    public void generateKeys() {
        // Generate two large prime numbers
        p = BigInteger.probablePrime(bitLength, random);
        q = BigInteger.probablePrime(bitLength, random);
        
        // Calculate n = p * q
        n = p.multiply(q);
        
        // Calculate phi = (p-1) * (q-1)
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        
        // Choose e
        e = BigInteger.valueOf(65537); // Commonly used value for e
        
        // Calculate d (multiplicative inverse of e modulo phi)
        d = e.modInverse(phi);
    }
    
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }
    
    public BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(d, n);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        rsa rsa = new rsa();
        
        try {
            System.out.println("Generating RSA keys...");
            rsa.generateKeys();
            
            while(true) {
                System.out.println("\nRSA Cipher Menu:");
                System.out.println("1. View Public/Private Keys");
                System.out.println("2. Encrypt a number");
                System.out.println("3. Decrypt a number");
                System.out.println("4. Exit");
                System.out.print("Enter your choice (1-4): ");
                
                int choice = scanner.nextInt();
                
                switch(choice) {
                    case 1:
                        System.out.println("\nPublic Key (e,n):");
                        System.out.println("e = " + rsa.e);
                        System.out.println("n = " + rsa.n);
                        System.out.println("\nPrivate Key (d,n):");
                        System.out.println("d = " + rsa.d);
                        break;
                        
                    case 2:
                        System.out.print("\nEnter a number to encrypt (smaller than n): ");
                        BigInteger message = scanner.nextBigInteger();
                        if (message.compareTo(rsa.n) >= 0) {
                            System.out.println("Error: Message must be smaller than n");
                            continue;
                        }
                        BigInteger encrypted = rsa.encrypt(message);
                        System.out.println("Encrypted message: " + encrypted);
                        break;
                        
                    case 3:
                        System.out.print("\nEnter a number to decrypt: ");
                        BigInteger toDecrypt = scanner.nextBigInteger();
                        BigInteger decrypted = rsa.decrypt(toDecrypt);
                        System.out.println("Decrypted message: " + decrypted);
                        break;
                        
                    case 4:
                        System.out.println("Goodbye!");
                        return;
                        
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
