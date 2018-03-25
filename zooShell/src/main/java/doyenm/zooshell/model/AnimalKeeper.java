package doyenm.zooshell.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AnimalKeeper {

    private String name;
    private int age;
    @XmlElementWrapper
    private List<TimedOccupation> occupations;
    private Map<TaskType, Double> taskEvaluations;
    private Map<Family, Double> familyEvaluations;
    private Training training;
}
