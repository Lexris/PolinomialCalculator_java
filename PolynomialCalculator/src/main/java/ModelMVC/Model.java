package ModelMVC;

import java.lang.reflect.InvocationTargetException;

public class Model {
    public Polynomial polynomialIn1;
    public Polynomial polynomialIn2;

    public Model(Polynomial polynomialIn1, Polynomial polynomialIN2) {
        this.polynomialIn1 = polynomialIn1;
        this.polynomialIn2 = polynomialIN2;
    }

    public Model() {
        this(null, null);
    }

    /**
     * Get the polynomial from the GUI as a String and convert it into a Polynomial.
     * @param polynomialString stores the user input for one of the two polynomials
     * @param rang tells us from which of the two input TextField we got our @polynomialString
     */
    public void getPolynomial(String polynomialString, int rang) throws InvalidInputException{
        //check input validity
        System.out.println(polynomialString);
        if(polynomialString.isEmpty() || polynomialString.matches(".*[^A-Za-z0-9+\\-^ ].*") ||
                polynomialString.matches(".*[a-zA-Z][a-zA-Z].*") || polynomialString.matches(".*[+\\-^][+\\-^].*")) {
            throw new InvalidInputException("Invalid input!");
        }

        //remove the legal junk(aka spaces, ^)
        polynomialString = polynomialString.replaceAll("[^^+\\-=a-zA-Z0-9]", "");

        //add sign at the beginning if not exists
        if ("+-".indexOf(polynomialString.charAt(0)) == -1) {
            polynomialString = "+" + polynomialString;
        }

        //add optional coefficient 1
        polynomialString = polynomialString.replaceAll("(?<sign>[+\\-])(?<variable>[a-zA-Z])", "${sign}" + "1" + "${variable}");
        System.out.println("\"" + polynomialString + "\"");   //pt verificare
        //add optional power x^0
        polynomialString = polynomialString.replaceAll("([+\\-]\\d+)([^a-zA-Z\\d]|$)", "$1" + "x^0" + "$2");
        System.out.println("\"" + polynomialString + "\"");   //pt verificare
        //add optional power ^1 when x is inserted w/o power
        polynomialString = polynomialString.replaceAll("([0-9]+[a-zA-Z])([+\\-]|$)", "$1" + "^1" + "$2");
        System.out.println("\"" + polynomialString + "\"");   //pt verificare
        //add space between every monomial
        polynomialString = polynomialString.replaceAll("(?<sign>[+\\-])", " ${sign}");
        System.out.println("\"" + polynomialString + "\"");   //pt verificare
        //remove first space
        polynomialString = polynomialString.trim();
        System.out.println("\"" + polynomialString + "\"");   //pt verificare

        //build array of Monomials as String based on the inserted " "
        String[] monomialsString;
        if (polynomialString.contains(" ")) {
            monomialsString = polynomialString.split(" ");
        } else {
            monomialsString = new String[]{polynomialString};
        }

        //create result variable and add each new Monomial constructed from String[] monomialsString
        Polynomial polynomialPoly = new Polynomial();
        for (String s : monomialsString) {
            //split each String into coefficientPower[0]->coefficient and coefficientPower[1]->power
            String[] coefficientPower = s.split("[a-zA-Z]\\^");
            polynomialPoly.getTerms().put(Integer.parseInt(coefficientPower[1]), new Monomial(coefficientPower));
        }

        //link each object to its source(TextField)
        if (rang == 1) polynomialIn1 = polynomialPoly;
        else polynomialIn2 = polynomialPoly;
    }

    public Polynomial addPolynomials() throws CloneNotSupportedException {
        return Polynomial.addPolynomials(polynomialIn1, polynomialIn2);
    }

    public Polynomial subtractPolynomials() throws CloneNotSupportedException {
        return Polynomial.subtractPolynomials(polynomialIn1, polynomialIn2);
    }

    public Polynomial multiplyPolynomials() throws CloneNotSupportedException {
        return Polynomial.multiplyPolynomials(polynomialIn1, polynomialIn2);
    }

    public Pair<Polynomial, Polynomial> dividePolynomials() throws CloneNotSupportedException, NoSuchMethodException, NullPolynomialException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        return Polynomial.dividePolynomials(polynomialIn1, polynomialIn2);
    }

    public Polynomial derivePolynomials() throws CloneNotSupportedException {
        return Polynomial.derivePolynomials(polynomialIn1);
    }

    public Polynomial integratePolynomials() throws CloneNotSupportedException {
        return Polynomial.integratePolynomials(polynomialIn1);
    }
}
