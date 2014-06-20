package worksheet2;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * The MD5 Algorithm found on: http://www.asjava.com/core-java/java-md5-example/
 * Example one
 */
public class MD5 {
    
    public static int keylength = 20;
    public static int[] getMD5(int[] input) {
        try {
            int[] ret = new int[20];
            int length = keylength;
            if (input.length < keylength){
                length = input.length;
            }
            //adding the input value to a string.
            String bitString = ""+ binaryToInt(input);

            //this is the MD5 hashing on the string
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(bitString.getBytes());;
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(2);
  
            //converting the bitstring from a String to int array trough a char array
            String cutOff = hashtext.substring(hashtext.length() - 20);
            char[] theBits = cutOff.toCharArray();
            
            for(int i = 0; i < theBits.length; i++){
                if(theBits[i] == '0'){
                    ret[i] = 0;
                }else {
                    ret[i] = 1;
                }
            }
            
            return ret;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    //creating a random starting point
    public static int[] randomStartPoint() {
         int[] ret = new int[keylength];
         for(int i = 0; i < keylength; i++){
             if(Math.random() < 0.5){
                 ret[i] = 0;
             } else {
                 ret[i] = 1;
             }
         }
        return ret;
    }
    
    //converting the binary array to an int
    public static int binaryToInt(int[] input){
        int ret = 0;
        double power = 0.0;
        for(int i = input.length - 1; i >= 0; i--){
            if(input[i] == 1){
               ret += (int) Math.pow(2.00, power); 
            }
            power++;
        }
        return ret;
    }
    
    //returning the number of trues in a boolean array
    public static int numberOfTrues(boolean[] input) {
        int ret = 0;
        for(int i = 0; i < input.length; i++){
            if(input[i]) ret++;
        }
        return ret;
    }
    
    public static int[] intToBinary(int input){
        int[] ret = new int[20];
        char[] bitStringChar;
        String bitString = Integer.toString(input, 2);
        String cutOff;//converting to binary string
        if(bitString.length() > 20){
            cutOff = bitString.substring(bitString.length() - 20);
            bitStringChar = cutOff.toCharArray();
        } else {
            bitStringChar = bitString.toCharArray();
        }
        //adding it into the int array.
        int index = ret.length;
        for (int i = bitStringChar.length; i >= 0 ; i--){
            if(bitStringChar[i] == '1'){
                ret[index] = 1;
                index--;
            }else{
                ret[index] = 0;
                index--;
            }
        }
        return ret;
    }
 
    public static void main(String[] args) throws NoSuchAlgorithmException {
        boolean[] covered = new boolean[(int) Math.pow (2.00,(double)keylength)]; //used to keep track true in the array = covered point
        int[] currentPoint = new int[20];
        int[] tempPoint = new int[20];
        int coveredNumbers = 0;
        int coveredNumbers2 = 0;
         //2^16 = 65536 on iteration in the for-loop for each 2^16 chains.
        for(int i = 0; i < 65536; i++){
        currentPoint = randomStartPoint();
        int index = binaryToInt(currentPoint);
        if(!covered[index]) coveredNumbers++;
        covered[index] = true;
        if(i % 100 == 0){
        System.out.println("Chain nr: " + i + " Covered: "+ coveredNumbers);
        }
            //For-loop representing the chain
            //In each iteration it is MD5'ed and then setting it to covered.
            for(int j = 0; j < 256; j++){
                currentPoint = getMD5(currentPoint);
                index = binaryToInt(currentPoint);
                if(!covered[index]){
                    coveredNumbers++;
                }
                covered[index] = true;
            }
        }
        int trues = 0;
        int all = (int) Math.pow (2.00,(double)keylength); //Number of possible keys, calculated using the keylength.
        for(int i = 0; i < covered.length; i++){
            if(covered[i]){
                trues++;
            }
        }
    
        System.out.println("Numbers covered: " + trues);
        System.out.println("Number of possibilites: " + all);
    }
}
