/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package worksheet3;

/**
 *
 * @author Novaz
 * 
 * The file is used to Exercise 19(P) in the worksheet3 of the course practical cryptanalysis at DTU
 * Its not needed to program anything, but thought it could be fun to do.
 */
public class Exercise19 {
    private static double[] frequencies = {.082, .015, .028, .043, .127, .022, .020, .061, .070, .002, .008, .040, .024, .067, .075, .019, .001, .060,
                                            .063, .091, .028, .010, .023, .001, .020, .001};
    
    public static void main(String[]args0) {
        double sumOfFrequencies = 0.0;
        for (int i = 0; i < frequencies.length; i++){
            sumOfFrequencies+= frequencies[i] * log2((1.0 / frequencies[i]));
        }
        System.out.println("The Entropy is: " + sumOfFrequencies);
    }
    /**
     * Log base 2, does not exists in Java this have to be implemented
     * @param a
     * @return the log base 2 of a double
     */
    public static double log2(double a){
        return Math.log(a) / Math.log(2);
    }
    
}
