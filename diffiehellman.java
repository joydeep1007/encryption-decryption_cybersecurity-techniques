import java.math.BigInteger;
import java.util.Scanner;

public class diffiehellman {
    public static BigInteger calculatePublicKey(BigInteger base, BigInteger privateKey, BigInteger prime) {
        return base.modPow(privateKey, prime);
    }
    
    public static BigInteger calculateSharedSecret(BigInteger publicKey, BigInteger privateKey, BigInteger prime) {
        return publicKey.modPow(privateKey, prime);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Get prime number and base
            System.out.print("Enter the prime number (p): ");
            BigInteger prime = new BigInteger(scanner.nextLine());
            
            System.out.print("Enter the base/generator (g): ");
            BigInteger base = new BigInteger(scanner.nextLine());
            
            // Alice's private key
            System.out.print("Enter Alice's private key: ");
            BigInteger alicePrivateKey = new BigInteger(scanner.nextLine());
            
            // Bob's private key
            System.out.print("Enter Bob's private key: ");
            BigInteger bobPrivateKey = new BigInteger(scanner.nextLine());
            
            // Calculate public keys
            BigInteger alicePublicKey = calculatePublicKey(base, alicePrivateKey, prime);
            BigInteger bobPublicKey = calculatePublicKey(base, bobPrivateKey, prime);
            
            System.out.println("\nPublic Keys Generated:");
            System.out.println("Alice's Public Key: " + alicePublicKey);
            System.out.println("Bob's Public Key: " + bobPublicKey);
            
            // Calculate shared secrets
            BigInteger aliceSharedSecret = calculateSharedSecret(bobPublicKey, alicePrivateKey, prime);
            BigInteger bobSharedSecret = calculateSharedSecret(alicePublicKey, bobPrivateKey, prime);
            
            System.out.println("\nShared Secrets Generated:");
            System.out.println("Alice's Shared Secret: " + aliceSharedSecret);
            System.out.println("Bob's Shared Secret: " + bobSharedSecret);
            
            // Verify if both shared secrets match
            System.out.println("\nVerification:");
            if(aliceSharedSecret.equals(bobSharedSecret)) {
                System.out.println("Shared secrets match! Secure communication can begin.");
            } else {
                System.out.println("Error: Shared secrets do not match!");
            }
            
        } catch(NumberFormatException e) {
            System.out.println("Please enter valid numbers!");
        } finally {
            scanner.close();
        }
    }
}
