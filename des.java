import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class des {
    private SecretKey secretKey;
    private Cipher desCipher;
    
    public des() throws Exception {
        // Initialize DES cipher
        this.desCipher = Cipher.getInstance("DES");
        // Generate random DES key
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        this.secretKey = keyGen.generateKey();
    }
    
    public String encrypt(String data) throws Exception {
        // Initialize cipher for encryption
        desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        
        // Convert string to bytes, encrypt and encode to base64
        byte[] textBytes = data.getBytes();
        byte[] cipherBytes = desCipher.doFinal(textBytes);
        return Base64.getEncoder().encodeToString(cipherBytes);
    }
    
    public String decrypt(String encryptedData) throws Exception {
        // Initialize cipher for decryption
        desCipher.init(Cipher.DECRYPT_MODE, secretKey);
        
        // Decode from base64, decrypt and convert to string
        byte[] cipherBytes = Base64.getDecoder().decode(encryptedData);
        byte[] textBytes = desCipher.doFinal(cipherBytes);
        return new String(textBytes);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            des desEncryption = new des();
            
            while(true) {
                System.out.println("\nDES Encryption Menu:");
                System.out.println("1. Encrypt a message");
                System.out.println("2. Decrypt a message");
                System.out.println("3. Exit");
                System.out.print("Enter your choice (1-3): ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                if(choice == 3) {
                    System.out.println("Goodbye!");
                    break;
                }
                
                if(choice != 1 && choice != 2) {
                    System.out.println("Invalid choice! Please try again.");
                    continue;
                }
                
                System.out.print("Enter the message: ");
                String input = scanner.nextLine();
                
                if(choice == 1) {
                    String encrypted = desEncryption.encrypt(input);
                    System.out.println("Encrypted text: " + encrypted);
                } else {
                    String decrypted = desEncryption.decrypt(input);
                    System.out.println("Decrypted text: " + decrypted);
                }
            }
            
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
