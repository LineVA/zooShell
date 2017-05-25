package doyenm.zooshell.controller.zoocontroller;

import doyenm.zooshell.context.ZooDetailsContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class ZooDetailsController implements Function<ZooDetailsContext, ZooDetailsContext> {

    @Override
    public ZooDetailsContext apply(ZooDetailsContext t) {
        ZooDetailsContext context = t;
        context.addCouple("Name", t.getZoo().getName());
        context.addCouple("Age", t.getZoo().getAge());
        context.addCouple("Width", t.getZoo().getWidth());
        context.addCouple("Height", t.getZoo().getHeight());
        context.addCouple("Horizon", t.getZoo().getHorizon());
        context.addCouple("Months per turn", t.getZoo().getMonthsPerEvaluation());
        context.addCouple("Number of paddocks", t.getZoo().getPaddocks().size());
        context.addCouple("Number of animals", t.getZoo().getAnimals().size());
        return context;
    }
}
