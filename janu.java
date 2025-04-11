import java.util.Scanner;

public class janu {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    
    public static String decrypt(String ciphertext, int shift) {
        StringBuilder plaintext = new StringBuilder();
        
        for (char c : ciphertext.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                int pos = ALPHABET.indexOf(c);
                int decryptPos = (pos - shift) % 26;
                if (decryptPos < 0) {
                    decryptPos = 26 + decryptPos;
                }
                plaintext.append(ALPHABET.charAt(decryptPos));
            } else {
                plaintext.append(c);  // Preserve spaces and special characters
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the encrypted message: ");
            String encrypted = scanner.nextLine();
            
            System.out.print("Enter the shift value (1-25): ");
            int shift = scanner.nextInt();
            
            String decrypted = decrypt(encrypted, shift);
            System.out.println("Decrypted message: " + decrypted);
        }
    }
}
