package doyenm.zooshell.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class Grade {

    private double animalsGrade = 0.0;
    private double paddocksGrade = 0.0;

    public double computeGrade() {
        return getAnimalsGrade()
                + getPaddocksGrade();
    }

    @Override
    public String toString() {
        return "Grade{" + "animalsGrade=" + animalsGrade + ", paddocksGrade=" + paddocksGrade + '}';
    }
}
