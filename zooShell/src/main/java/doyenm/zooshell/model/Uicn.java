package doyenm.zooshell.model;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
@Getter
public enum Uicn {

    UNKNOWN(0, 1.0),
    LEAST_CONCERNED(1, 1.1),
    NEAR_THREATENED(2, 1.2),
    VULNERABLE(3, 1.3),
    ENDANGERED(4, 1.4),
    CRITICALLY_ENDANGERED(5, 1.5),
    EXTINCT_IN_WILD(6, 1.6),
    EXTINCT(7, 1.7);

    int id;
    double coefficient;
    
    Uicn(int id, double coefficient){
        this.id = id;
        this.coefficient = coefficient;
    }
}
