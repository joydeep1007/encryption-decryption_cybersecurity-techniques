import java.util.Scanner;   

public class lalu   
{   
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";   
    
    public static String encryptData(String inputStr, int shiftKey)   
    {   
        StringBuilder encryptStr = new StringBuilder();   
          
        for (char c : inputStr.toLowerCase().toCharArray()) {   
            if (Character.isLetter(c)) {
                int pos = ALPHABET.indexOf(c);   
                int encryptPos = Math.floorMod((shiftKey + pos), 26);   
                encryptStr.append(ALPHABET.charAt(encryptPos));   
            } else {
                encryptStr.append(c); // Keep spaces and special characters unchanged
            }
        }   
        return encryptStr.toString();   
    }   
      
    public static String decryptData(String inputStr, int shiftKey)   
    {   
        StringBuilder decryptStr = new StringBuilder();   
          
        for (char c : inputStr.toLowerCase().toCharArray()) {   
            if (Character.isLetter(c)) {
                int pos = ALPHABET.indexOf(c);   
                int decryptPos = Math.floorMod((pos - shiftKey), 26);   
                decryptStr.append(ALPHABET.charAt(decryptPos));   
            } else {
                decryptStr.append(c); // Keep spaces and special characters unchanged
            }
        }   
        return decryptStr.toString();   
    }   
      
    public static void main(String[] args)   
    {   
        Scanner sc = new Scanner(System.in);   
        
        while(true) {
            System.out.println("\nCaesar Cipher Menu:");
            System.out.println("1. Encrypt a message");
            System.out.println("2. Decrypt a message");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            
            if(choice == 3) {
                System.out.println("Goodbye!");
                break;
            }
            
            if(choice != 1 && choice != 2) {
                System.out.println("Invalid choice! Please try again.");
                continue;
            }
            
            System.out.println("\nEnter the message: ");   
            String inputStr = sc.nextLine();   
            
            System.out.println("Enter the shift key: ");   
            int shiftKey = Integer.valueOf(sc.nextLine());   
            
            if(choice == 1) {
                String encrypted = encryptData(inputStr, shiftKey);
                System.out.println("Encrypted Message: " + encrypted);
            } else {
                String decrypted = decryptData(inputStr, shiftKey);
                System.out.println("Decrypted Message: " + decrypted);
            }
        }
        
        sc.close();   
    }   
}