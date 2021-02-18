package ModelMVC;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

public class Monomial implements Cloneable {
    private Double coefficient;
    private Integer power;

    public Monomial(Double coefficient, Integer power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public Monomial(Monomial polynomialCopy) {
        this(polynomialCopy.getCoefficient(), polynomialCopy.getPower());
    }

    public Monomial(Integer power) {
        this(0.0, power);
    }

    public Monomial(String[] coefficientPower) {
        this(Double.parseDouble(coefficientPower[0]), Integer.parseInt(coefficientPower[1]));
    }


    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public void addMonomials(Double coefficient) {
        this.coefficient += coefficient;
    }

    public void subtractMonomials(Double coefficient) {
        this.coefficient -= coefficient;
    }

    public void multiplyMonomials(Monomial inputMonomial) {
        this.coefficient *= inputMonomial.getCoefficient();
        this.power += inputMonomial.getPower();
    }

    public void divideMonomials(Double coefficient, Integer power) {
        this.coefficient /= coefficient;
        this.power -= power;
    }

    public static Monomial divideMonomials(Monomial m1, Monomial m2) throws CloneNotSupportedException {
        return new Monomial(m1.getCoefficient()/m2.getCoefficient(), m1.getPower()-m2.getPower());
    }

    public void deriveMonomial() {
        this.coefficient *= this.power;
        this.power=(0==this.power?0:this.power-1);
    }

    public void integrateMonomial() {
        if(0==this.coefficient)
            return;
        this.coefficient /= this.power+1;
        this.power++;
    }

    public Boolean isNull() {
        return 0==this.coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monomial monomial = (Monomial) o;
        return Objects.equals(coefficient, monomial.coefficient) &&
                Objects.equals(power, monomial.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coefficient, power);
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat();
        formatter.setMaximumFractionDigits(2);
        StringBuilder monomialString = new StringBuilder();

        if(coefficient<0) monomialString.append(formatter.format(coefficient));
        else monomialString.append("+").append(formatter.format(coefficient));

        if(power!=0) monomialString.append("x^").append(power);

        return new String(monomialString);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
