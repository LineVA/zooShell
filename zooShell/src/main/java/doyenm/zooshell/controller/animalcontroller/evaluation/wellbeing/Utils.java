package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

/**
 *
 * @author doyenm
 */
public class Utils {

    public static double computeDeviationBetweenCurrentAndOptimal(double current, double optimal) {
        if (current >= optimal) {
            return (current - optimal) / optimal;
        }
        return (optimal - current) / optimal;
    }
    
    public static boolean isBetweenAuthorizedValues(double currentDeviation, double maximalDeviation){
        return currentDeviation <= maximalDeviation;
    }
    
}