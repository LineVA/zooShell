package doyenm.zooshell.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author doyenm
 */
@Getter
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public enum Uicn {

    UNKNOWN(0, 1.0, 0.5),
    LEAST_CONCERNED(1, 1.1, 0.2),
    NEAR_THREATENED(2, 1.2, 0.15),
    VULNERABLE(3, 1.3, 0.12),
    ENDANGERED(4, 1.4, 0.10),
    CRITICALLY_ENDANGERED(5, 1.5, 0.8),
    EXTINCT_IN_WILD(6, 1.6, 0.5),
    EXTINCT(7, 1.7, 0.0);

    int id;
    double coefficient;
    double standardDeviation;

    Uicn(int id, double coefficient, double deviation){
        this.id = id;
        this.coefficient = coefficient;
        this.standardDeviation = deviation;
    }
}
