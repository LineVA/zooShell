package doyenm.zooshell.testUtils;

import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Biome;
import doyenm.zooshell.model.PaddockFacility;
import doyenm.zooshell.model.PaddockType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestUtils {

   private final static Random random = new Random();

    public static PaddockFacility getPaddockFacilityExcluding(PaddockFacility... facilities) {
        List<PaddockFacility> excludings = Arrays.asList(facilities);
        List<PaddockFacility> includings = new ArrayList<>();
        for(PaddockFacility input : PaddockFacility.values()){
            if(!excludings.contains(input)){
                includings.add(input);
            }
        }
        return includings.get(random.nextInt(includings.size()));
    }

    public static PaddockFacility getPaddockFacility() {
        return PaddockFacility.values()[random.nextInt(PaddockFacility.values().length)];
    }

    public static PaddockType getPaddockTypeExcluding(PaddockType... types) {
        List<PaddockType> excludings = Arrays.asList(types);
        List<PaddockType> includings = new ArrayList<>();
        for(PaddockType input : PaddockType.values()){
            if(!excludings.contains(input)){
                includings.add(input);
            }
        }
        return includings.get(random.nextInt(includings.size()));
    }

    public static PaddockType getPaddockType() {
        return PaddockType.values()[random.nextInt(PaddockType.values().length)];
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
