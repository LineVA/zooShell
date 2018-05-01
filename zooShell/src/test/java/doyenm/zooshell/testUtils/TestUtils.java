package doyenm.zooshell.testUtils;

import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Biome;
import doyenm.zooshell.model.PaddockArrangement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestUtils {

   private final static Random random = new Random();

    public static PaddockArrangement getPaddockArrangementExcluding(PaddockArrangement... arrangements) {
        List<PaddockArrangement> excludings = Arrays.asList(arrangements);
        List<PaddockArrangement> includings = new ArrayList<>();
        for(PaddockArrangement input : PaddockArrangement.values()){
            if(!excludings.contains(input)){
                includings.add(input);
            }
        }
        return includings.get(random.nextInt(includings.size()));
    }

    public static PaddockArrangement getPaddockArrangement() {
        return PaddockArrangement.values()[random.nextInt(PaddockArrangement.values().length)];
    }

    public static Biome getBiomeExcluding(Biome... biomes) {
        List<Biome> excludings = Arrays.asList(biomes);
        List<Biome> includings = new ArrayList<>();
        for(Biome input : Biome.values()){
            if(!excludings.contains(input)){
                includings.add(input);
            }
        }
        return includings.get(random.nextInt(includings.size()));
    }

    public static Biome getBiome() {
        return Biome.values()[random.nextInt(Biome.values().length)];
    }

    public static ErrorType getErrorTypeExcluding(ErrorType... excluded) {
        List<ErrorType> excludings = Arrays.asList(excluded);
        List<ErrorType> includings = new ArrayList<>();
        for(ErrorType input : ErrorType.values()){
            if(!excludings.contains(input)){
                includings.add(input);
            }
        }
        return includings.get(random.nextInt(includings.size()));
    }

    public static ErrorType getErrorType() {
        return ErrorType.values()[random.nextInt(ErrorType.values().length)];
    }
}
