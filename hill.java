import java.util.Scanner;

public class hill {
    private static int[][] keyMatrix;
    private static int[][] inverseKeyMatrix;
    private static int dimension;
    
    // Method to get matrix modulo inverse
    private static int[][] getInverseMatrix(int[][] matrix) {
        int det = getDeterminant(matrix);
        int detInv = multiplicativeInverse(det, 26);
        
        int[][] adjMatrix = getAdjugateMatrix(matrix);
        int[][] inverseMatrix = new int[dimension][dimension];
        
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                inverseMatrix[i][j] = ((adjMatrix[i][j] * detInv) % 26 + 26) % 26;
            }
        }
        return inverseMatrix;
    }
    
    // Method to get determinant
    private static int getDeterminant(int[][] matrix) {
        if(dimension == 2) {
            return ((matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) % 26 + 26) % 26;
        }
        // For 3x3 matrix
        int det = 0;
        for(int i = 0; i < 3; i++) {
            det = det + (matrix[0][i] * (matrix[1][(i+1)%3] * matrix[2][(i+2)%3] 
                  - matrix[1][(i+2)%3] * matrix[2][(i+1)%3]));
        }
        return ((det % 26) + 26) % 26;
    }
    
    // Method to get multiplicative inverse
    private static int multiplicativeInverse(int det, int mod) {
        for(int i = 1; i < mod; i++) {
            if(((det * i) % mod) == 1) {
                return i;
            }
        }
        return -1;
    }
    
    // Method to get adjugate matrix
    private static int[][] getAdjugateMatrix(int[][] matrix) {
        int[][] adj = new int[dimension][dimension];
        if(dimension == 2) {
            adj[0][0] = matrix[1][1];
            adj[1][1] = matrix[0][0];
            adj[0][1] = -matrix[0][1];
            adj[1][0] = -matrix[1][0];
            return adj;
        }
        // For 3x3 matrix implementation if needed
        return adj;
    }
    
    // Encryption method
    private static String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        int[][] messageVector = new int[dimension][1];
        
        for(int i = 0; i < text.length(); i += dimension) {
            for(int j = 0; j < dimension; j++) {
                if(i + j < text.length()) {
                    messageVector[j][0] = text.charAt(i + j) - 'A';
                } else {
                    messageVector[j][0] = 'X' - 'A';
                }
            }
            
            int[][] cipher = multiplyMatrices(keyMatrix, messageVector);
            for(int j = 0; j < dimension; j++) {
                result.append((char)((cipher[j][0] % 26) + 'A'));
            }
        }
        return result.toString();
    }
    
    // Decryption method
    private static String decrypt(String text) {
        StringBuilder result = new StringBuilder();
        int[][] messageVector = new int[dimension][1];
        
        for(int i = 0; i < text.length(); i += dimension) {
            for(int j = 0; j < dimension; j++) {
                messageVector[j][0] = text.charAt(i + j) - 'A';
            }
            
            int[][] plain = multiplyMatrices(inverseKeyMatrix, messageVector);
            for(int j = 0; j < dimension; j++) {
                result.append((char)((plain[j][0] % 26 + 26) % 26 + 'A'));
            }
        }
        return result.toString();
    }
    
    // Matrix multiplication
    private static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int[][] result = new int[dimension][1];
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < 1; j++) {
                result[i][j] = 0;
                for(int k = 0; k < dimension; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
                result[i][j] = ((result[i][j] % 26) + 26) % 26;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("Hill Cipher Implementation");
            System.out.print("Enter matrix dimension (2 for 2x2, 3 for 3x3): ");
            dimension = scanner.nextInt();
            
            if(dimension != 2 && dimension != 3) {
                System.out.println("Only 2x2 or 3x3 matrices are supported");
                return;
            }
            
            keyMatrix = new int[dimension][dimension];
            System.out.println("Enter the key matrix (" + dimension + "x" + dimension + "):");
            for(int i = 0; i < dimension; i++) {
                for(int j = 0; j < dimension; j++) {
                    keyMatrix[i][j] = scanner.nextInt();
                }
            }
            
            inverseKeyMatrix = getInverseMatrix(keyMatrix);
            
            scanner.nextLine(); // Consume newline
            while(true) {
                System.out.println("\nHill Cipher Menu:");
                System.out.println("1. Encrypt");
                System.out.println("2. Decrypt");
                System.out.println("3. Exit");
                System.out.print("Enter choice (1-3): ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                if(choice == 3) break;
                
                if(choice == 1 || choice == 2) {
                    System.out.print("Enter message (uppercase letters only): ");
                    String message = scanner.nextLine().toUpperCase();
                    
                    if(choice == 1) {
                        System.out.println("Encrypted message: " + encrypt(message));
                    } else {
                        System.out.println("Decrypted message: " + decrypt(message));
                    }
                }
            }
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
