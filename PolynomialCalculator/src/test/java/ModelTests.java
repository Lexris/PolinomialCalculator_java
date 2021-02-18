import static org.junit.jupiter.api.Assertions.assertEquals;
import ModelMVC.InvalidInputException;
import ModelMVC.Model;
import ModelMVC.Monomial;
import ModelMVC.Polynomial;
import org.junit.jupiter.api.Test;

public class ModelTests {
    @Test
    public void testGetPolynomial() {
        String polynomialString1 = "8x + 6x^2 + x^3 + 3x^6";
        Polynomial polynomialFromString1 = new Polynomial();
        polynomialFromString1.getTerms().put(1, new Monomial(8.0, 1));
        polynomialFromString1.getTerms().put(2, new Monomial(6.0, 2));
        polynomialFromString1.getTerms().put(3, new Monomial(1.0, 3));
        polynomialFromString1.getTerms().put(6, new Monomial(3.0, 6));

        String polynomialString2 = "x + 4x^2 + 2x^3 + 4x^5";
        Polynomial polynomialFromString2 = new Polynomial();
        polynomialFromString2.getTerms().put(1, new Monomial(1.0, 1));
        polynomialFromString2.getTerms().put(2, new Monomial(4.0, 2));
        polynomialFromString2.getTerms().put(3, new Monomial(2.0, 3));
        polynomialFromString2.getTerms().put(5, new Monomial(4.0, 5));

        Model model = new Model();

        try {
            model.getPolynomial(polynomialString1, 1);
            model.getPolynomial(polynomialString2, 2);
            assertEquals(polynomialFromString1, model.polynomialIn1);
            assertEquals(polynomialFromString2, model.polynomialIn2);

        } catch (InvalidInputException e) {
            e.printStackTrace();
        }


    }
}
