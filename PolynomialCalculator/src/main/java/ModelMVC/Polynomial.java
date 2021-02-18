package ModelMVC;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Objects;

public class Polynomial implements Cloneable {
    private HashMap<Integer, Monomial> terms;

    public Polynomial(HashMap<Integer, Monomial> terms) {
        this.terms = terms;
    }

    public Polynomial(Integer degree) {
        terms = new HashMap<>();
        for(int i=0; i<degree; i++)
            this.terms.put(i, new Monomial(i));
    }

    public Polynomial(Monomial monomial) {
        terms = new HashMap<>();
        try {
            terms.put(monomial.getPower(), (Monomial) monomial.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public Polynomial() {
        this.terms = new HashMap<Integer, Monomial>();
    }

    public HashMap<Integer, Monomial> getTerms() {
        return terms;
    }

    public void setTerms(HashMap<Integer, Monomial> terms) {
        this.terms = terms;
    }

    /**
     * Copy polynomialIn1 in polynomialOUT and then either creates or adds those of polynomialIn2.
     * @param polynomialIn1 first polynomial to be added
     * @param polynomialIn2 second polynomial to be added
     * @return polynomialOUT sum of polynomialIn1 and polynomialIn2
     */
    public static Polynomial addPolynomials(Polynomial polynomialIn1, Polynomial polynomialIn2) throws CloneNotSupportedException {
        //copy polynomialIn1 in polynomialOUT
        Polynomial polynomialOUT = (Polynomial) polynomialIn1.clone();

        //add all elements of polynomialIn2
        for(Integer key : polynomialIn2.getTerms().keySet()) {
            if(polynomialOUT.getTerms().containsKey(key)) {
                polynomialOUT.getTerms().get(key).addMonomials(polynomialIn2.getTerms().get(key).getCoefficient());
            } else {
                polynomialOUT.getTerms().put(key, new Monomial(polynomialIn2.getTerms().get(key)));
            }
        }

        return polynomialOUT;
    }

    /**
     * Copy polynomialIn1 in polynomialOUT and then either creates or subtracts those of polynomialIn2.
     * @param polynomialIn1 first polynomial to be added
     * @param polynomialIn2 second polynomial to be added
     * @return polynomialOUT sum of polynomialIn1 and polynomialIn2
     */
    public static Polynomial subtractPolynomials(Polynomial polynomialIn1, Polynomial polynomialIn2) throws CloneNotSupportedException {
        //copy polynomialIn1 in polynomialOUT
        Polynomial polynomialOUT = (Polynomial) polynomialIn1.clone();

        //subtract all elements of polynomialIn2
        for(Integer key : polynomialIn2.getTerms().keySet()) {
            if(polynomialOUT.getTerms().containsKey(key)) {
                polynomialOUT.getTerms().get(key).subtractMonomials(polynomialIn2.getTerms().get(key).getCoefficient());
            } else {
                Monomial monomialAtKey = polynomialIn2.getTerms().get(key);
                polynomialOUT.getTerms().put(key, new Monomial(-monomialAtKey.getCoefficient(), monomialAtKey.getPower()));
            }
        }

        return polynomialOUT;
    }

    /**
     * Creates new Polynomial for each Monomial resulting from the cross products and adds it
     * to the result variable. Always works with a copy of polynomialIn2 so that we don't lose
     * the data in case the user wants to perform another operation on it.
     * @return polynomialOUT product of polynomials
     */
    public static Polynomial multiplyPolynomials(Polynomial polynomialIn1, Polynomial polynomialIn2) throws CloneNotSupportedException {
        //create output object
        Polynomial polynomialOUT = new Polynomial();

        //perform cross product and add each Monomial to result
        for(Integer key1 : polynomialIn1.getTerms().keySet()) {
            Polynomial polynomialBackUp = (Polynomial) polynomialIn2.clone();
            for(Integer key2 : polynomialBackUp.getTerms().keySet()) {
                polynomialBackUp.getTerms().get(key2).multiplyMonomials(polynomialIn1.getTerms().get(key1));
                polynomialOUT = addPolynomials(polynomialOUT, new Polynomial(polynomialBackUp.getTerms().get(key2)));
            }
            addPolynomials(polynomialOUT, polynomialBackUp);
        }

        return polynomialOUT;
    }

    /**
     * Divides the polynomials using the Polynomial Long Division and stores the result
     * using a Pair object.
     * @return polynomialOUT quotient(left) and remainder(right) of the division
     */
    public static Pair<Polynomial, Polynomial> dividePolynomials(Polynomial polynomialIn1, Polynomial polynomialIn2) throws NullPolynomialException, CloneNotSupportedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //creates output object Pair to store both quotient and remainder
        Pair<Polynomial, Polynomial> polynomialOUT = new Pair<>(Polynomial.class, Polynomial.class);

        //checks if denominator is NULL
        if(polynomialIn2.isNull()) {
            throw new NullPolynomialException("Denominator is INVALID.");
        }

        //perform Long Polynomial Division(source: https://en.wikipedia.org/wiki/Polynomial_long_division#Pseudocode)
        polynomialOUT.right = (Polynomial) polynomialIn1.clone();
        while(!polynomialOUT.right.isNull() && polynomialOUT.right.degree()>=polynomialIn2.degree()) {
            Polynomial temp = new Polynomial(Monomial.divideMonomials(polynomialOUT.right.getDominantTerm(), polynomialIn2.getDominantTerm()));
            polynomialOUT.left = addPolynomials(polynomialOUT.left, temp);
            polynomialOUT.right = subtractPolynomials(polynomialOUT.right, multiplyPolynomials(temp, polynomialIn2));//ramas aici cred ca mai trb s fac un static la usbtract pt alg de division
        }

        return polynomialOUT;
    }

    /**
     * Derives each Monomial within the Polynomial
     * @return polynomialOUT derived polynomial
     */
    public static Polynomial derivePolynomials(Polynomial polynomialIn1) throws CloneNotSupportedException {
        //copy polynomialIn1 in polynomialOUT
        Polynomial polynomialAUX = (Polynomial) polynomialIn1.clone();
        Polynomial polynomialOUT = new Polynomial();

        //integrate each Monomial
        for(Integer key : polynomialAUX.getTerms().keySet()) {
            polynomialAUX.getTerms().get(key).deriveMonomial();
            if(key != 0)
                polynomialOUT.getTerms().put(key-1, new Monomial(polynomialAUX.getTerms().get(key).getCoefficient(), key-1));
        }

        return polynomialOUT;
    }

    /**
     * Integrates each Monomial within the Polynomial
     * @return polynomialOUT integrated polynomial
     */
    public static Polynomial integratePolynomials(Polynomial polynomialIn1) throws CloneNotSupportedException {
        //copy polynomialIn1 in polynomialOUT
        Polynomial polynomialAUX = (Polynomial) polynomialIn1.clone();
        Polynomial polynomialOUT = new Polynomial();

        //integrate each Monomial
        for(Integer key : polynomialAUX.getTerms().keySet()) {
            polynomialAUX.getTerms().get(key).integrateMonomial();
            if(key != 0)
                polynomialOUT.getTerms().put(key+1, new Monomial(polynomialAUX.getTerms().get(key).getCoefficient(), key+1));
        }

        return polynomialOUT;
    }

    /**
     * Calculate degree of Polynomial
     * @return degree
     */
    public Integer degree() {
        int degree=0;
        for(Integer key : terms.keySet()) {
            if(!terms.get(key).isNull() && degree<key) {
                degree = key;
            }
        }
        return degree;
    }

    /**
     * Gets the Monomial with highest power within the Polynomial
     * @return Monomial with highest power
     * @throws NullPolynomialException
     */
    public Monomial getDominantTerm() throws NullPolynomialException {
        if(this.isNull())
            throw new NullPolynomialException("Null Polynomial in getDominantTerm()");
        else return this.terms.get(this.degree());
    }

    /**
     * Checks if there is any Monomial that is not NULL.
     * @return status of Polynomial
     */
    public Boolean isNull() {
        for(Integer key : terms.keySet()) {
            if(!terms.get(key).isNull())
                return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynomial that = (Polynomial) o;
        boolean isEqual = true;
        for(Integer key : this.terms.keySet()) {
            if(!this.terms.get(key).isNull()) {
                isEqual = isEqual && this.terms.get(key).equals(that.terms.get(key));
            }
        }
        for(Integer key : that.terms.keySet()) {
            if(!that.terms.get(key).isNull()) {
                isEqual = isEqual && that.terms.get(key).equals(this.terms.get(key));
            }
        }

        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(terms);
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        for(Integer key : terms.keySet()) {
            if(terms.get(key).getCoefficient()!=0)
                resultString.insert(0, terms.get(key).toString());
        }
        if(resultString.length()==0)
            resultString.insert(0, "0");

        return resultString.toString().toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Polynomial clone = (Polynomial) super.clone();
        clone.terms = new HashMap<Integer, Monomial>();
        for(Integer key : this.terms.keySet()) {
            clone.terms.put(key, (Monomial) this.terms.get(key).clone());
        }
        return clone;
    }
}
