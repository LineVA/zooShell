package doyenm.zooshell.controller.handymancontroller;

import doyenm.zooshell.context.HandymanContext;
import doyenm.zooshell.model.Handyman;
import doyenm.zooshell.utils.Utils;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class HandymanDetailsController implements Function<HandymanContext, HandymanContext> {

    private final Utils utils;

    @Override
    public HandymanContext apply(HandymanContext t) {
        HandymanContext context = t;
        Handyman handyman = context.getHandyman();
        context.addCouple("Name", handyman.getName());
        int age = context.getHandyman().getAge();
        context.addCouple("Age", utils.getNumbersOfYearsFromAge(age) + " year(s), " + utils.getNumbersOfMonthsFromAge(age) + " month(s)");
//        context.addCouple("Affectations", handyman.getAffectations().toString());
        return context;
    }
}
