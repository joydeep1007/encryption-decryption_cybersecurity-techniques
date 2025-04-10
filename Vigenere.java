// Java code to implement Vigenere Cipher

import java.util.Scanner;

class Vigenere
{

// This function generates the key in
// a cyclic manner until it's length isi'nt
// equal to the length of original text
static String generateKey(String str, String key)
{
    int x = str.length();

    for (int i = 0; ; i++)
    {
        if (x == i)
            i = 0;
        if (key.length() == str.length())
            break;
        key+=(key.charAt(i));
    }
    return key;
}

// This function returns the encrypted text
// generated with the help of the key
static String cipherText(String str, String key)
{
    String cipher_text="";

    for (int i = 0; i < str.length(); i++)
    {
        // converting in range 0-25
        int x = (str.charAt(i) + key.charAt(i)) %26;

        // convert into alphabets(ASCII)
        x += 'A';

        cipher_text+=(char)(x);
    }
    return cipher_text;
}

// This function decrypts the encrypted text
// and returns the original text
static String originalText(String cipher_text, String key)
{
    String orig_text="";

    for (int i = 0 ; i < cipher_text.length() && 
                            i < key.length(); i++)
    {
        // converting in range 0-25
        int x = (cipher_text.charAt(i) - 
                    key.charAt(i) + 26) %26;

        // convert into alphabets(ASCII)
        x += 'A';
        orig_text+=(char)(x);
    }
    return orig_text;
}

// This function will convert the lower case character to Upper case
static String LowerToUpper(String s)
{
    StringBuffer str =new StringBuffer(s); 
    for(int i = 0; i < s.length(); i++)
    {
        if(Character.isLowerCase(s.charAt(i)))
        {
            str.setCharAt(i, Character.toUpperCase(s.charAt(i)));
        }
    }
    s = str.toString();
    return s;
}

// Driver code
public static void main(String[] args) 
{
    Scanner scanner = new Scanner(System.in);
    
    while(true) {
        System.out.println("\nVigenere Cipher Menu:");
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
        String str = scanner.nextLine();
        
        System.out.print("Enter the keyword: ");
        String keyword = scanner.nextLine();
        
        str = LowerToUpper(str);
        keyword = LowerToUpper(keyword);
        
        String key = generateKey(str, keyword);
        
        if(choice == 1) {
            String cipher_text = cipherText(str, key);
            System.out.println("Encrypted text: " + cipher_text);
        } else {
            String original_text = originalText(str, key);
            System.out.println("Decrypted text: " + original_text);
        }
    }
    
    scanner.close();
}
}

