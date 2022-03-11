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

public class AddOperationTest {

    private final Operations operations = new Operations();

    @ParameterizedTest
    @MethodSource("provideInput")
    public void addTest(String pol1, String pol2, String polRes) throws PolyFormatException {
        Polynomial firstPoly = new Polynomial(pol1);
        Polynomial secondPoly = new Polynomial(pol2);
        Polynomial resultEx = new Polynomial(polRes);
        Polynomial result;
        result = operations.addP(firstPoly, secondPoly);
        Assertions.assertEquals(resultEx, result, "Addition is bad");
    }

    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of("+x^3-2x^2+6x-5", "+2x^2-1", "+x^3+6x-6"));
        argumentsList.add(Arguments.of("+6x^3+10x^2+5", "+4x^2+2x+1", "+6x^3+14x^2+6"));
        argumentsList.add(Arguments.of("-5x", "+5x", "+0"));
        argumentsList.add(Arguments.of("+5x+3x^2-2", "+4+2x^3-x", "+2x^3+3x^2+4x+2"));
        argumentsList.add(Arguments.of("", "", ""));
        argumentsList.add(Arguments.of("", "+5x", "+5x"));

        return argumentsList;
    }

}
