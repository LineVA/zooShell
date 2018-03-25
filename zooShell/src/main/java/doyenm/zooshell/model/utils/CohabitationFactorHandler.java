package doyenm.zooshell.model.utils;

import doyenm.zooshell.model.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
@Slf4j
public class CohabitationFactorHandler {

    public double compute(double agressivity, Specie specie, SizeAttributes sizeAttributes, Sex sex) {
        double cohabitation = 0.0;
        double weight = sizeAttributes.getWeigthGivenSex(sex);

        // Cohabitation = (
        //    weight/ 1 tonne
        //    + 1 if carnivore
        //    + agressivity
        //        + specie_agresivity)
        //    / 4
        Optional<Double> optional = Stream.of(cohabitation)
                .map(t -> {
                    t += weight >= 1000.0 ? 1.0 : weight / 1000.0;
                    return t;
                })
                .map(t -> {
                    if (specie.getDiets().getDiets().contains(Diet.CARNIVOROUS)) {
                        t += 1.0;
                    }
                    return t;
                })
                .map(t -> add(t, agressivity))
                .map(t -> add(t, specie.getCharacterAttributes().getAgressivity()))
                .map(t -> t / 4.0)
                .findFirst();
        if(optional.isPresent()){
            return optional.get();
        } else {
            log.info("No result when computing the cohabitation factor of the animal");
            return cohabitation;
        }
    }

    private double add(double a, double b){
        return a + b;
    }

    public double compute(Animal animal) {
        return compute(
                animal.getCharacterAttributes().getAgressivity(),
                animal.getSpecie(),
                animal.getSizeAttributes(),
                animal.getSex());
    }
}
