package doyenm.zooshell.controller.animalcontroller.criteria;

import doyenm.zooshell.context.AnimalsWithCriteriaContext;

/**
 *
 * @author doyenm
 */
public enum AnimalsCriteriaPredicate {

    HAS_DIET{
         @Override
        public boolean test(AnimalsWithCriteriaContext context) {
            return context.getAnimal().getDiets().contains(context.getCurrentDiet());
        }
    };
    
    public abstract boolean test(AnimalsWithCriteriaContext context);
}
