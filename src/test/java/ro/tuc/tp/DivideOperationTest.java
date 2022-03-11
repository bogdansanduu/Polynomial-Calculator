package ro.tuc.tp;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ro.tuc.tp.logic.Operations;
import ro.tuc.tp.model.Monomial;
import ro.tuc.tp.model.Polynomial;
import ro.tuc.tp.utils.PolyFormatException;

import java.util.ArrayList;
import java.util.List;

public class DivideOperationTest {
    private final Operations operations = new Operations();

    @ParameterizedTest
    @MethodSource("provideInput")
    public void divideTestInt(String pol1, String pol2, String polResQ, String polResR) throws PolyFormatException {
        Polynomial firstPoly = new Polynomial(pol1);
        Polynomial secondPoly = new Polynomial(pol2);
        Polynomial resultEx1 = new Polynomial(polResQ);
        Polynomial resultEx2 = new Polynomial(polResR);
        Polynomial[] result = new Polynomial[2];
        result = operations.divideP(firstPoly, secondPoly);
        Assertions.assertEquals(resultEx1, result[0], "Division is bad");
        Assertions.assertEquals(resultEx2, result[1], "Division is bad");
    }

    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of("+x^3-2x^2+6x-5", "+x^2-1", "+x-2", "+7x-7"));
        argumentsList.add(Arguments.of("+x^3-2x-4", "+x-3", "+x^2+3x+7", "+17"));
        argumentsList.add(Arguments.of("+x^2-9x-10", "+x+1", "+x-10", "+0"));

        return argumentsList;
    }

    @Test
    public void divideTestFloat() throws PolyFormatException {
        Polynomial firstPoly = new Polynomial("+x^3-12x^2+22x-17");
        Polynomial secondPoly = new Polynomial("+3x-7");
        Polynomial[] result = operations.divideP(firstPoly, secondPoly);
        Polynomial resPolyQ = new Polynomial();
        Polynomial resPolyR = new Polynomial();
        resPolyQ.addMonomial(new Monomial(1.f / 3.f, 2));
        resPolyQ.addMonomial(new Monomial(-29.f / 9.f, 1));
        resPolyQ.addMonomial(new Monomial(-5.f / 27.f, 0));
        resPolyR.addMonomial(new Monomial(-494.f / 27.f, 0));
        Assertions.assertEquals(resPolyQ, result[0]);
        Assertions.assertEquals(resPolyR, result[1]);
        //nu merge din couza floting point round errors si nu stiu cum sa rezolv
    }

}
