package doyenm.zooshell.utils;

import java.util.Random;

/**
 *
 * @author doyenm
 */
public class GaussianStatistics extends Random{
    
    public double gaussianDouble(double mean, double standardDeviation){
        double gaussian = super.nextGaussian()*standardDeviation + mean;
        if(gaussian > 0.0){
            return gaussian;
        } else {
           return 0.01;
        }
    }
    
    public int gaussianInt(int mean, double standardDeviation){
        int gaussian = (int) gaussianDouble(mean, standardDeviation);
        if(gaussian == 0){
            return 1;
        }
        return gaussian;
    }
}
