package ro.tuc.tp.model;

import java.util.Objects;

public class Monomial implements Comparable<Monomial> {
    private Number coefficient;
    private int power;

    public Monomial(Number coefficient, int power, char sign) {
        if (power == -1)
            this.power = 0;
        else
            this.power = power;
        if (coefficient.floatValue() == -1) {
            if (sign == '+')
                this.coefficient = 1;
            else
                this.coefficient = -1;
        } else {
            if (sign == '+')
                this.coefficient = coefficient.floatValue();
            else
                this.coefficient = -coefficient.floatValue();
        }
    }

    public Monomial(Number coefficient, int power) {
        this.power = power;
        this.coefficient = coefficient;
    }

    public float getCoefficient() {
        return coefficient.floatValue();
    }

    public void setCoefficient(Number coefficient) {
        this.coefficient = coefficient;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public int compareTo(Monomial o) {
        if (this.power >= o.getPower())
            return 1;
        else if (this.getPower() < o.getPower())
            return -1;
        else return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Monomial monomial = (Monomial) o;
        return Objects.equals(this.coefficient, monomial.getCoefficient()) && this.power == monomial.getPower();
    }

    @Override
    public int hashCode() {
        String hashCode = this.coefficient + String.valueOf(this.power);
        return hashCode.hashCode();
    }

}
