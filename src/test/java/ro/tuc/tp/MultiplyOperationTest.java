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

public class MultiplyOperationTest {


    private final Operations operations = new Operations();

    @ParameterizedTest
    @MethodSource("provideInput")
    public void multiplyTest(String pol1, String pol2, String polRes) throws PolyFormatException {
        Polynomial firstPoly = new Polynomial(pol1);
        Polynomial secondPoly = new Polynomial(pol2);
        Polynomial resultEx = new Polynomial(polRes);
        Polynomial result;
        result = operations.multiplyP(firstPoly, secondPoly);
        Assertions.assertEquals(resultEx, result, "Multiplication is bad");
    }

    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of("+0", "+5x-3x^2+2", "+0"));
        argumentsList.add(Arguments.of("+2x+1", "+x^2+3x+4", "+2x^3+7x^2+11x+4"));
        argumentsList.add(Arguments.of("+5x^3-2x+4x+3-4x^3", "+0", "+0"));
        argumentsList.add(Arguments.of("-5x^3-2x+4x+3-4x^2", "+4+2x^3-x", "-10x^6-8x^5+9x^4-10x^3-18x^2+5x+12"));
        argumentsList.add(Arguments.of("-5x^3-2x+4x+3-4x^2", "+4+2x^3-x", "-8x^5-5x^6+9x^4-10x^3-5x^6-18x^2+2x+12+3x"));

        return argumentsList;
    }

}
