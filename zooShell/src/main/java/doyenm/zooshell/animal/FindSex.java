package doyenm.zooshell.animal;

import doyenm.zooshell.animal.FindingSexContext;
import doyenm.zooshell.animal.FindingSexFunction;
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
