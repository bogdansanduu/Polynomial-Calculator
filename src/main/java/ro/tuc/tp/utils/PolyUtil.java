package ro.tuc.tp.utils;

import ro.tuc.tp.model.Monomial;
import ro.tuc.tp.model.Polynomial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolyUtil {

    public static ArrayList<Monomial> polyExtractor(String inputPoly) throws PolyFormatException {
        ArrayList<Monomial> monomials = new ArrayList<>();
        Pattern polyPattern = Pattern.compile("[+-]([0-9]+)?(x(\\^[0-9]+)?)?");
        Pattern monoPattern = Pattern.compile("([+-])([0-9]*)(x?)\\^?([0-9]*)");
        Matcher matcherP = polyPattern.matcher(inputPoly);
        int OK = 1;
        String polyString = "";
        while (!matcherP.hitEnd()) {
            String matchedMono;
            if (matcherP.find()) {
                matchedMono = matcherP.group();
                polyString = polyString.concat(matchedMono);
                if (matchedMono.length() == 1) {
                    OK = 0;
                    break;
                }
            } else {
                OK = 0;
                break;
            }

            Matcher matcherM = monoPattern.matcher(matchedMono);
            matcherM.find();
            char sign = matcherM.group(1).charAt(0);
            int coeff = -1;
            if (matcherM.group(2).length() != 0)
                coeff = Integer.parseInt(matcherM.group(2));
            int power = -1;
            if (matcherM.group(3).length() == 1 && matcherM.group(4).length() == 0)
                power = 1;
            else if (matcherM.group(3).length() == 1 && matcherM.group(4).length() != 0)
                power = Integer.parseInt(matcherM.group(4));
            Monomial newMono = new Monomial(coeff, power, sign);
            monomials.add(newMono);
        }
        if ((OK == 0 || polyString.length() != inputPoly.length()) && inputPoly.length() != 0)
            throw new PolyFormatException(); // error
        return monomials;
    }

    static String monoFormatter(Monomial monomial) {
        String resMono = "";
        String sign = "";
        if (Math.signum(monomial.getCoefficient()) == 1)
            sign = "+";
        else if (Math.signum(monomial.getCoefficient()) == -1)
            sign = "-";
        resMono = resMono.concat(sign);
        String coefficient = "";
        if (monomial.getCoefficient() == (int) monomial.getCoefficient())
            coefficient = String.valueOf(Math.abs((int) monomial.getCoefficient()));
        else
            coefficient = String.valueOf(Math.abs(monomial.getCoefficient()));
        if (monomial.getPower() == 0) {
            resMono = resMono.concat(coefficient);
        } else if (monomial.getPower() == 1) {
            if (Math.abs(monomial.getCoefficient()) != 1)
                resMono = resMono.concat(coefficient + "x");
            else
                resMono = resMono.concat("x");
        } else {
            if (Math.abs(monomial.getCoefficient()) != 1) {
                resMono = resMono.concat(coefficient + "x^" + monomial.getPower());
            } else {
                resMono = resMono.concat("x^" + monomial.getPower());
            }
        }
        return resMono;
    }

    public static String polyFormatter(Polynomial polynomial) {
        String resPoly = "";
        for (Monomial monomial : polynomial.getMonomials()) {
            String mono = monoFormatter(monomial);
            resPoly = resPoly.concat(mono);
        }

        return resPoly;
    }

    public static Polynomial polyCombine(Polynomial polynomial) {
        Set<Monomial> toRemoveS = new HashSet<>();
        Set<Monomial> toAddS = new HashSet<>();

        for (Monomial monomial1 : polynomial.getMonomials()) {
            float coeff = monomial1.getCoefficient();
            for (Monomial monomial2 : polynomial.getMonomials()) {
                if (monomial2.getPower() == monomial1.getPower() && monomial2 != monomial1) {
                    coeff += monomial2.getCoefficient();
                    toRemoveS.add(monomial2);
                }
            }
            if (coeff != 0) {
                Monomial monomialAdd = new Monomial(coeff, monomial1.getPower());
                toRemoveS.add(monomial1);
                toAddS.add(monomialAdd);
            } else {
                toRemoveS.add(monomial1);
            }
        }

        ArrayList<Monomial> toRemove = new ArrayList<>(toRemoveS);
        ArrayList<Monomial> toAdd = new ArrayList<>(toAddS);

        polynomial.removeMonomials(toRemove);
        polynomial.addMonomials(toAdd);

        return polynomial;
    }

    public static Monomial deriveMono(Monomial monomial) {
        float coeff;
        int power;
        if (monomial.getPower() == 0) {
            coeff = 0;
            power = 0;
        } else {
            coeff = monomial.getCoefficient() * monomial.getPower();
            power = monomial.getPower() - 1;
        }
        monomial.setCoefficient(coeff);
        monomial.setPower(power);

        return new Monomial(coeff, power, '+');
    }

    public static Monomial integrateMono(Monomial monomial) {
        int power;
        float coeff;
        power = monomial.getPower() + 1;
        coeff = monomial.getCoefficient() / power;

        return new Monomial(coeff, power, '+');
    }

    public static Monomial divideMono(Monomial firstMono, Monomial secondMono) {
        float coeff;
        int power;
        coeff = firstMono.getCoefficient() / secondMono.getCoefficient();
        power = firstMono.getPower() - secondMono.getPower();

        return new Monomial(coeff, power);
    }

}
