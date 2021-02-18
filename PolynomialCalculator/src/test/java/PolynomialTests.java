import ModelMVC.Monomial;
import ModelMVC.NullPolynomialException;
import ModelMVC.Pair;
import ModelMVC.Polynomial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class PolynomialTests {

    @Test
    public void testPolynomialSummation() {
        //8x + 6x^2 + x^3 + 3x^6
        Polynomial polynomial1 = new Polynomial(new HashMap<Integer, Monomial>());
        polynomial1.getTerms().put(1, new Monomial(8.0, 1));
        polynomial1.getTerms().put(2, new Monomial(6.0, 2));
        polynomial1.getTerms().put(3, new Monomial(1.0, 3));
        polynomial1.getTerms().put(6, new Monomial(3.0, 6));
        System.out.println(polynomial1);

        //x + 4x^2 + 2x^3 + 4x^5
        Polynomial polynomial2 = new Polynomial(new HashMap<Integer, Monomial>());
        polynomial2.getTerms().put(1, new Monomial(1.0, 1));
        polynomial2.getTerms().put(2, new Monomial(4.0, 2));
        polynomial2.getTerms().put(3, new Monomial(2.0, 3));
        polynomial2.getTerms().put(5, new Monomial(4.0, 5));
        System.out.println(polynomial2);

        Polynomial polynomialSum = new Polynomial(new HashMap<Integer, Monomial>());
        polynomialSum.getTerms().put(1, new Monomial(9.0, 1));
        polynomialSum.getTerms().put(2, new Monomial(10.0, 2));
        polynomialSum.getTerms().put(3, new Monomial(3.0, 3));
        polynomialSum.getTerms().put(5, new Monomial(4.0, 5));
        polynomialSum.getTerms().put(6, new Monomial(3.0, 6));
        System.out.println(polynomialSum);

        try {
            assertEquals(polynomialSum, Polynomial.addPolynomials(polynomial1, polynomial2));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPolynomialSubtraction() {
        //8x + 6x^2 + x^3 + 3x^6
        Polynomial polynomial1 = new Polynomial(new HashMap<Integer, Monomial>());
        polynomial1.getTerms().put(1, new Monomial(8.0, 1));
        polynomial1.getTerms().put(2, new Monomial(6.0, 2));
        polynomial1.getTerms().put(3, new Monomial(1.0, 3));
        polynomial1.getTerms().put(6, new Monomial(3.0, 6));
        System.out.println(polynomial1);

        //x + 4x^2 + 2x^3 + 4x^5
        Polynomial polynomial2 = new Polynomial(new HashMap<Integer, Monomial>());
        polynomial2.getTerms().put(1, new Monomial(1.0, 1));
        polynomial2.getTerms().put(2, new Monomial(4.0, 2));
        polynomial2.getTerms().put(3, new Monomial(2.0, 3));
        polynomial2.getTerms().put(5, new Monomial(4.0, 5));
        System.out.println(polynomial2);

        Polynomial polynomialDiff = new Polynomial(new HashMap<Integer, Monomial>());
        polynomialDiff.getTerms().put(1, new Monomial(7.0, 1));
        polynomialDiff.getTerms().put(2, new Monomial(2.0, 2));
        polynomialDiff.getTerms().put(3, new Monomial(-1.0, 3));
        polynomialDiff.getTerms().put(5, new Monomial(-4.0, 5));
        polynomialDiff.getTerms().put(6, new Monomial(3.0, 6));
        System.out.println(polynomialDiff);

        try {
            assertEquals(polynomialDiff, Polynomial.subtractPolynomials(polynomial1, polynomial2));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPolynomialMultiplication() {
        //8x + 6x^2 + x^3 + 3x^6
        Polynomial polynomial1 = new Polynomial(new HashMap<Integer, Monomial>());
        polynomial1.getTerms().put(1, new Monomial(8.0, 1));
        polynomial1.getTerms().put(2, new Monomial(6.0, 2));
        polynomial1.getTerms().put(3, new Monomial(1.0, 3));
        polynomial1.getTerms().put(6, new Monomial(3.0, 6));
        System.out.println(polynomial1);

        //x + 4x^2 + 2x^3 + 4x^5
        Polynomial polynomial2 = new Polynomial(new HashMap<Integer, Monomial>());
        polynomial2.getTerms().put(1, new Monomial(1.0, 1));
        polynomial2.getTerms().put(2, new Monomial(4.0, 2));
        polynomial2.getTerms().put(3, new Monomial(2.0, 3));
        polynomial2.getTerms().put(5, new Monomial(4.0, 5));
        System.out.println(polynomial2);

        //12x^11 + 6x^9 + 16x^8 + 27x^7 + 34x^6 + 16x^5 + 41x^4 + 38x^3 + 8x^2
        Polynomial polynomialProduct = new Polynomial(new HashMap<Integer, Monomial>());
        polynomialProduct.getTerms().put(2, new Monomial(8.0, 2));
        polynomialProduct.getTerms().put(3, new Monomial(38.0, 3));
        polynomialProduct.getTerms().put(4, new Monomial(41.0, 4));
        polynomialProduct.getTerms().put(5, new Monomial(16.0, 5));
        polynomialProduct.getTerms().put(6, new Monomial(34.0, 6));
        polynomialProduct.getTerms().put(7, new Monomial(27.0, 7));
        polynomialProduct.getTerms().put(8, new Monomial(16.0, 8));
        polynomialProduct.getTerms().put(9, new Monomial(6.0, 9));
        polynomialProduct.getTerms().put(11, new Monomial(12.0, 11));
        System.out.println(polynomialProduct);

        try {
            assertEquals(polynomialProduct, Polynomial.multiplyPolynomials(polynomial1, polynomial2));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPolynomialDivision() {
        //8x + 6x^2 + x^3 + 3x^6
        Polynomial polynomial1 = new Polynomial(new HashMap<Integer, Monomial>());
        polynomial1.getTerms().put(1, new Monomial(8.0, 1));
        polynomial1.getTerms().put(2, new Monomial(6.0, 2));
        polynomial1.getTerms().put(3, new Monomial(1.0, 3));
        polynomial1.getTerms().put(6, new Monomial(3.0, 6));
        System.out.println(polynomial1);

        //x + 4x^2 + 2x^3 + 4x^5
        Polynomial polynomial2 = new Polynomial(new HashMap<Integer, Monomial>());
        polynomial2.getTerms().put(1, new Monomial(1.0, 1));
        polynomial2.getTerms().put(2, new Monomial(4.0, 2));
        polynomial2.getTerms().put(3, new Monomial(2.0, 3));
        polynomial2.getTerms().put(5, new Monomial(4.0, 5));
        System.out.println(polynomial2);

        try {
            Pair<Polynomial, Polynomial> polynomialDivision = new Pair<Polynomial, Polynomial>(Polynomial.class, Polynomial.class);
            polynomialDivision.left.getTerms().put(1, new Monomial(3.0 / 4.0, 1));
            polynomialDivision.right.getTerms().put(1, new Monomial(8.0, 1));
            polynomialDivision.right.getTerms().put(2, new Monomial(21.0 / 4.0, 2));
            polynomialDivision.right.getTerms().put(3, new Monomial(-2.0, 3));
            polynomialDivision.right.getTerms().put(4, new Monomial(-3.0 / 2.0, 4));
            System.out.println(polynomialDivision.left + "  " + polynomialDivision.right);

            assertEquals(polynomialDivision, Polynomial.dividePolynomials(polynomial1, polynomial2));
        } catch (CloneNotSupportedException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException | NullPolynomialException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPolynomialDerivation() {
        //8x + 6x^2 + x^3 + 3x^6
        Polynomial polynomial1 = new Polynomial(new HashMap<Integer, Monomial>());
        polynomial1.getTerms().put(1, new Monomial(8.0, 1));
        polynomial1.getTerms().put(2, new Monomial(6.0, 2));
        polynomial1.getTerms().put(3, new Monomial(1.0, 3));
        polynomial1.getTerms().put(6, new Monomial(3.0, 6));
        System.out.println(polynomial1);

        Polynomial polynomialDeriv = new Polynomial(new HashMap<Integer, Monomial>());
        polynomialDeriv.getTerms().put(0, new Monomial(8.0, 0));
        polynomialDeriv.getTerms().put(1, new Monomial(12.0, 1));
        polynomialDeriv.getTerms().put(2, new Monomial(3.0, 2));
        polynomialDeriv.getTerms().put(5, new Monomial(18.0, 5));
        System.out.println(polynomialDeriv);

        try {
            assertEquals(polynomialDeriv, Polynomial.derivePolynomials(polynomial1));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPolynomialIntegration() {
        //8x + 6x^2 + x^3 + 3x^6
        Polynomial polynomial1 = new Polynomial(new HashMap<Integer, Monomial>());
        polynomial1.getTerms().put(1, new Monomial(8.0, 1));
        polynomial1.getTerms().put(2, new Monomial(6.0, 2));
        polynomial1.getTerms().put(3, new Monomial(1.0, 3));
        polynomial1.getTerms().put(6, new Monomial(3.0, 6));
        System.out.println(polynomial1);

        Polynomial polynomialInteg = new Polynomial(new HashMap<Integer, Monomial>());
        polynomialInteg.getTerms().put(2, new Monomial(4.0, 2));
        polynomialInteg.getTerms().put(3, new Monomial(2.0, 3));
        polynomialInteg.getTerms().put(4, new Monomial(1.0/4.0, 4));
        polynomialInteg.getTerms().put(7, new Monomial(3.0/7.0, 7));
        System.out.println(polynomialInteg);

        try {
            assertEquals(polynomialInteg, Polynomial.integratePolynomials(polynomial1));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
