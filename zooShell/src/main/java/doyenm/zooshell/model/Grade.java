package doyenm.zooshell.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

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
@ToString
public class Grade {

    private double animalsGrade = 0.0;
    private double paddocksGrade = 0.0;
    private double zooGrade = 0.0;
    private double penaltiesGrade = 0.0;

    public double computeGrade() {
        return getAnimalsGrade()
                + getPaddocksGrade()
                + getZooGrade()
                - getPenaltiesGrade();
    }

}
