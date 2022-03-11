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

public class IntegrateOperationTest {
    private final Operations operations = new Operations();

    @ParameterizedTest
    @MethodSource("provideInput")
    public void integrateTestInt(String pol, String polRes) throws PolyFormatException {
        Polynomial firstPoly = new Polynomial(pol);
        Polynomial resultEx = new Polynomial(polRes);
        Polynomial result;
        result = operations.integrateP(firstPoly);
        Assertions.assertEquals(resultEx, result, "Integration is bad");
    }

    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of("+0", "+0"));
        argumentsList.add(Arguments.of("+2x+1", "+x^2+x"));
        argumentsList.add(Arguments.of("+15x^4-2x+4x+3-6x^2", "+3x^5-2x^3+x^2+3x"));
        argumentsList.add(Arguments.of("+10x^4-2x+4x+3-3x^2-3x^2+5x^4", "+3x^5-2x^3+x^2+3x"));

        return argumentsList;
    }

    @Test
    public void integrateTestFloat() throws PolyFormatException {
        Polynomial poly = new Polynomial("+x^3-12x^2+22x-17");
        Polynomial result = operations.integrateP(poly);
        Polynomial resEx = new Polynomial();
        resEx.addMonomial(new Monomial(1.f / 4, 4));
        resEx.addMonomial(new Monomial(-4.f, 3));
        resEx.addMonomial(new Monomial(+11.f, 2));
        resEx.addMonomial(new Monomial(-17.f, 1));
        Assertions.assertEquals(result, resEx);
    }

}
