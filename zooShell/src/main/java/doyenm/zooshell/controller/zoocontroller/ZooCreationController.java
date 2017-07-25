package doyenm.zooshell.controller.zoocontroller;

import java.util.function.Function;
import doyenm.zooshell.context.ZooCreationContext;

/**
 *
 * @author doyenm
 */
public class ZooCreationController implements Function<ZooCreationContext, ZooCreationContext> {

    @Override
    public ZooCreationContext apply(ZooCreationContext t) {
        ZooCreationContext context = t;
        context.getZoo().setWidth(context.getConvertedWidth());
        context.getZoo().setHeight(context.getConvertedHeight());
        context.getZoo().setName(context.getName());
        context.getZoo().setHorizon(context.getConvertedHorizon());
        context.getZoo().setMonthsPerEvaluation(context.getConvertedSpeed());
        context.getZoo().setAge(context.getAge());
        context.getZoo().setSpecies(context.getSpecies());
        return context;

    }

}
