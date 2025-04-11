import java.util.Scanner;

public class CaesarCipher {
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                result.append((char) ((ch - base + shift) % 26 + base));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
    
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the message to encrypt: ");
            String message = scanner.nextLine();
            
            System.out.print("Enter the shift value (1-25): ");
            int shift = scanner.nextInt();
            
            String encrypted = encrypt(message, shift);
            System.out.println("Original: " + message);
            System.out.println("Encrypted: " + encrypted);
            System.out.println("Decrypted: " + decrypt(encrypted, shift));
        }
    }
}
