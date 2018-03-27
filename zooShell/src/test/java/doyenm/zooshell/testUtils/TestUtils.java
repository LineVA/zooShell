package doyenm.zooshell.testUtils;

import doyenm.zooshell.model.PaddockArrangement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestUtils {

    public static PaddockArrangement getPaddockArrangementExcluding(PaddockArrangement... arrangements) {
        List<PaddockArrangement> excludings = Arrays.asList(arrangements);
        List<PaddockArrangement> includings = new ArrayList<>();
        for(PaddockArrangement input : PaddockArrangement.values()){
            if(!excludings.contains(input)){
                includings.add(input);
            }
        }
        Random random = new Random();
        return includings.get(random.nextInt(includings.size()));
    }

    public static PaddockArrangement getPaddockArrangement() {
        Random random = new Random();
        return PaddockArrangement.values()[random.nextInt(PaddockArrangement.values().length)];
    }
}
