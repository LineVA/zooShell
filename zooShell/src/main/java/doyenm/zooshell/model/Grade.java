package doyenm.zooshell.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class Grade {

    private double animalsGrade = 0.0;
    private double paddocksGrade = 0.0;
    private double zooGrade = 0.0;

    public double computeGrade() {
        return getAnimalsGrade()
                + getPaddocksGrade()
                + getZooGrade();
    }

    @Override
    public String toString() {
        return "Grade{" + "animalsGrade=" + animalsGrade 
                + ", paddocksGrade=" + paddocksGrade 
                + ", zooGrade=" + zooGrade + '}';
    }

   
}
