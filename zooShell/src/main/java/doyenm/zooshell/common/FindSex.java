package doyenm.zooshell.common;

import doyenm.zooshell.common.context.FindingSexContext;
import doyenm.zooshell.common.function.FindingSexFunction;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.model.Zoo;

import java.util.stream.Stream;

public class FindSex {

    FindingSexFunction findingSexFunction = new FindingSexFunction();

    public Sex find(Zoo zoo, String sexName) {
        FindingSexContext context = new FindingSexContext(sexName.toUpperCase());
        return Stream.of(context)
                .map(findingSexFunction)
                .findFirst()
                .get()
                .getSex();
    }
}
