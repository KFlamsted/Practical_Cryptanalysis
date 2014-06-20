package practical_cryptanalysis;

public class Practical_Cryptanalysis {
    
    private static int keylength = 128;
    
    public static void main(String[] args) {
        int[] text = randomKey(keylength);
        System.out.println("PlainText:");
        for(int i = 0; i < text.length; i++){
            System.out.print(text[i]);
        }
        System.out.println();
        System.out.println("Key: ");
        int[] key = randomKey(keylength);
        for(int i = 0; i < key.length; i++){
            System.out.print(key[i]);
        }
        System.out.println();
        int[] ciphertext = encrypt(key, text);
        System.out.println("Ciphertext generated.. Bruteforcing Key");
        int[] bruteKey = bruteForce(ciphertext, text);
        System.out.println("Key found");
        for(int i = 0; i < bruteKey.length; i++){
            System.out.print(bruteKey[i]);
        }    
    }

    public static int[] encrypt(int[] key, int[] plaintext){
        int[] ciphertext = new int[keylength];
        for(int i = 0; i < keylength; i++){
            ciphertext[i] = key[i] ^ plaintext[i];
        }
        return ciphertext;
    }
    //using this to generate a random key of a given length
    public static int[] randomKey(int length){
        int[] key = new int[length];
        for(int i = 0; i < length; i++){
            if(Math.random() > 0.5){
                key[i] = 1;
            } else {
                key[i] = 0;
            }
        }
        return key;
    }
    //the bruteforce
    public static int[] bruteForce(int[] ciphertext, int[] plaintext){
        long startTime = System.currentTimeMillis();
        int KeysGenerated = 0;
        int[] bruteKey = new int[keylength];
        int start = 0;
        int max = 1;
        for (int i = 0; i < keylength; i++) {
            bruteKey[i] = start;
        }
        int index = keylength - 1;
        while(true){
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            KeysGenerated++;
            if(equalsArray(plaintext,encrypt(bruteKey, ciphertext)) || totalTime > 3600000){
                System.out.println("Number of keys generated: " + KeysGenerated);
                return bruteKey;
            }
            index = keylength - 1;
            bruteKey[index]++;
            while (index >= 0 && bruteKey[index] > max) {
                bruteKey[index] = start;
                index--;
                if (index < 0) { 
                    break;
                }
                bruteKey[index]++;
            }
        }
    }
    
    public static boolean equalsArray(int[] arr1, int[] arr2 ){
        for(int i = 0; i < arr1.length; i ++){
            if(arr1[i] != arr2[i]) return false;
        }
        return true;
    }    
}
