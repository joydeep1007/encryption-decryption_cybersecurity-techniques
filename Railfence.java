import java.util.Scanner;

public class Railfence {
    static String encryptRailFence(String text, int key) {
        char[][] rail = new char[key][text.length()];
        
        // Fill the rail array with placeholder
        for (int i = 0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                rail[i][j] = '.';
        
        // Find the direction
        boolean dirDown = true;
        int row = 0, col = 0;
        
        // Fill the rail matrix
        for (int i = 0; i < text.length(); i++) {
            if (row == 0)
                dirDown = true;
            if (row == key - 1)
                dirDown = false;
            
            rail[row][col++] = text.charAt(i);
            
            if (dirDown) row++;
            else row--;
        }
        
        // Create the encrypted text
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                if (rail[i][j] != '.')
                    result.append(rail[i][j]);
        
        return result.toString();
    }
    
    static String decryptRailFence(String cipher, int key) {
        char[][] rail = new char[key][cipher.length()];
        
        // Fill the rail array with placeholder
        for (int i = 0; i < key; i++)
            for (int j = 0; j < cipher.length(); j++)
                rail[i][j] = '.';
        
        // Find the direction
        boolean dirDown = true;
        int row = 0, col = 0;
        
        // Mark the positions in rail matrix
        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0) dirDown = true;
            if (row == key - 1) dirDown = false;
            
            rail[row][col++] = '*';
            
            if (dirDown) row++;
            else row--;
        }
        
        // Fill the rail matrix with cipher text
        int index = 0;
        for (int i = 0; i < key; i++)
            for (int j = 0; j < cipher.length(); j++)
                if (rail[i][j] == '*' && index < cipher.length())
                    rail[i][j] = cipher.charAt(index++);
        
        // Read off the matrix in zig-zag pattern
        StringBuilder result = new StringBuilder();
        row = 0;
        col = 0;
        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0) dirDown = true;
            if (row == key - 1) dirDown = false;
            
            if (rail[row][col] != '*')
                result.append(rail[row][col++]);
            
            if (dirDown) row++;
            else row--;
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while(true) {
            System.out.println("\nRail Fence Cipher Menu:");
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
            String text = scanner.nextLine();
            
            System.out.print("Enter the number of rails (key): ");
            int key = scanner.nextInt();
            
            if(choice == 1) {
                String encrypted = encryptRailFence(text, key);
                System.out.println("Encrypted text: " + encrypted);
            } else {
                String decrypted = decryptRailFence(text, key);
                System.out.println("Decrypted text: " + decrypted);
            }
        }
            }
        }
    }
