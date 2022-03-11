package ro.tuc.tp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ro.tuc.tp.logic.Operations;
import ro.tuc.tp.model.Polynomial;
import ro.tuc.tp.utils.PolyFormatException;

import java.util.ArrayList;
import java.util.List;

public class DeriveOperationTest {
    private final Operations operations = new Operations();

    @ParameterizedTest
    @MethodSource("provideInput")
    public void deriveTest(String pol, String polRes) throws PolyFormatException {
        Polynomial firstPoly = new Polynomial(pol);
        Polynomial resultEx = new Polynomial(polRes);
        Polynomial result;
        result = operations.deriveP(firstPoly);
        Assertions.assertEquals(resultEx, result, "Multiplication is bad");
    }

    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of("+0", "+0"));
        argumentsList.add(Arguments.of("+2x+1", "+2"));
        argumentsList.add(Arguments.of("+5x^3-2x+4x+3-4x^2", "+15x^2+2-8x"));
        argumentsList.add(Arguments.of("-5x^3+2x^2+4x-3", "-15x^2+4x+4"));
        argumentsList.add(Arguments.of("+5x^3-2x+4x+3-4x^2", "+10x^2+5x^2+2-4x-4x"));

        return argumentsList;
    }
}
