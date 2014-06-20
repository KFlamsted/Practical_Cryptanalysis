/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practical_cryptanalysis;

/**
 *
 * @author Novaz
 */
public class CBC {
    
    public static void main(String args[]){
        int[] cipher = {13, 4, 3, 12, 1, 0, 8, 10, 14, 6, 9, 15, 11, 2, 5, 7};
        int xorVector = 3;
        int[] ciphertext = new int[16];
        
        for(int j = 0; j < 16; j++){
            
            int xorAfter = j ^ xorVector;
            for(int i = 0; i < 16; i++){
                ciphertext[i] = cipher[xorAfter];
                xorAfter = xorVector ^ cipher[xorAfter];
            }
            System.out.print("IV " + j +": ");
            for(int i = 0; i < 16; i++){
                System.out.print(ciphertext[i]+ " ");
            }
            System.out.println();
            
        }
        
        
        
    }
    
    
    public static int[] encrypt(int[] key, int[] plaintext){
        int[] ciphertext = new int[plaintext.length];
        for(int i = 0; i < plaintext.length; i++){
            ciphertext[i] = key[i] ^ plaintext[i];
        }
        return ciphertext;
    }
    
}
