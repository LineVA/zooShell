package doyenm.zooshell.controller.eventhandling.animal;

import doyenm.zooshell.controller.eventhandling.EventCategory;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum AnimalEventType {

    DEATH_OF_AGE(EventCategory.UNARY, AnimalEventCategory.DEAD, "The animal {0} is dead of old age"),
    DEATH_OF_DROWN(EventCategory.UNARY, AnimalEventCategory.DEAD, "The animal {0} is dead of drown"),
    DEATH_OF_HUNGER(EventCategory.UNARY, AnimalEventCategory.DEAD, "The animal {0} is dead of starvation"),
    DEATH_OF_PREDATION(EventCategory.BINARY, AnimalEventCategory.DEAD, "The animal {0} is dead, eaten by {1}"),
    DIYING_OF_HUNGER(EventCategory.UNARY, AnimalEventCategory.DYING, "The animal {0} is dying of starvation"),
    DIYING_OF_DROWN(EventCategory.UNARY, AnimalEventCategory.DYING, "The animal {0} is diying of drowning"),
    BIRTH(EventCategory.UNARY, AnimalEventCategory.BIRTH, "The animal {0} is born ; its mother is {1}"),
    NEW_PREGNANCY(EventCategory.UNARY, AnimalEventCategory.PREGNANCY, "The animal {0} is now pregnant"),
    PREGNANCY_PURSUIT(EventCategory.UNARY, AnimalEventCategory.PREGNANCY,"The animal {0} is still pregnant");

    @Getter
    private EventCategory category;
    
    @Getter
    private AnimalEventCategory animalEventCategory;

    @Getter
    private final String message;

    private AnimalEventType(EventCategory category, AnimalEventCategory animalEventCategory, String message) {
        this.category = category;
        this.message = message;
        this.animalEventCategory  = animalEventCategory;
    }
    
    public List<AnimalEventType> getAvoidableDeathsEventTypes(){
        return Arrays.asList(DEATH_OF_DROWN, DEATH_OF_HUNGER, DEATH_OF_PREDATION);
    }
}
