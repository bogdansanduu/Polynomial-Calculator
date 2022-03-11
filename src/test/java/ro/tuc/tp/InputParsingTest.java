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

public class InputParsingTest {

    private final Operations operations = new Operations();


    @ParameterizedTest
    @MethodSource("provideInputFailure")
    public void parsingTestFail(String pol1, String pol2) {
        try {
            Polynomial firstPoly = new Polynomial(pol1);
            Polynomial secondPoly = new Polynomial(pol2);
            Assertions.fail("Expected exception was not thrown");
        } catch (PolyFormatException e) {
            Assertions.assertNotNull(e);
        }
    }

    @ParameterizedTest
    @MethodSource("provideInput")
    public void parsingTest(String pol1, String pol2) throws PolyFormatException {

        Polynomial firstPoly = new Polynomial(pol1);
        Polynomial secondPoly = new Polynomial(pol2);
        Assertions.assertEquals(secondPoly, firstPoly, "Parsing failure");

    }


    private static List<Arguments> provideInputFailure() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of("x^3-2x^2+6x-5", "+2x^2-1"));
        argumentsList.add(Arguments.of("+6x^3d+10x^2+5c", "+4x^2+2x+1"));
        argumentsList.add(Arguments.of("WrongFormat", "+kda"));
        argumentsList.add(Arguments.of("++++", "+4+2x^3-x"));

        return argumentsList;
    }

    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of("+x^3-2x^2+6x-5", "+x^3-2x^2+6x-5"));
        argumentsList.add(Arguments.of("+6x^3+10x^2+5+10x^2", "+6x^3+20x^2+5"));
        argumentsList.add(Arguments.of("+5x+3x^2-2", "+3x^2+5x-2"));
        argumentsList.add(Arguments.of("+10x^2+20x-5+5x^2-3+x^3", "+x^3+15x^2+20x-8"));
        argumentsList.add(Arguments.of("", ""));

        return argumentsList;
    }

}
