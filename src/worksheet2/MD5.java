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
    /**
     * The md5_redux method.
     * @param input - 20bit bitstring in an int array
     * @return int[] bit array representing the new 20bit bitstring
     */
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

 
    public static void main(String[] args) throws NoSuchAlgorithmException {
        boolean[] covered = new boolean[(int) Math.pow (2.00,(double)keylength)]; //used to keep track true in the array = covered point
        int[] currentPoint;
        int coveredNumbers = 0;
         //2^16 = 65536 on iteration in the for-loop for each 2^16 chains.
        for(int i = 0; i < 65536; i++){
        currentPoint = randomStartPoint(); //finding the random start point
        int index = binaryToInt(currentPoint);
        if(!covered[index]) coveredNumbers++;
        covered[index] = true;
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
        int all = (int) Math.pow (2.00,(double)keylength); //Number of possible keys, calculated using the keylength.
        System.out.println("Numbers covered: " + coveredNumbers);
        System.out.println("Number of possibilites: " + all);
    }
}
