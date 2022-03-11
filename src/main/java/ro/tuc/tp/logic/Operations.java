package ro.tuc.tp.logic;

import ro.tuc.tp.model.Monomial;
import ro.tuc.tp.model.Polynomial;
import ro.tuc.tp.utils.PolyUtil;


public class Operations {

    public Polynomial addP(Polynomial firstPoly, Polynomial secondPoly) {
        Polynomial resultingPoly = new Polynomial();

        for (Monomial p1Mono : firstPoly.getMonomials()) {
            resultingPoly.addMonomial(p1Mono);
        }

        for (Monomial p2Mono : secondPoly.getMonomials()) {
            int OK = 1;
            for (Monomial p1Mono : firstPoly.getMonomials()) {
                if (p2Mono.getPower() == p1Mono.getPower()) {
                    float coeff = p1Mono.getCoefficient() + p2Mono.getCoefficient();
                    if (coeff == 0)
                        resultingPoly.removeMonomial(p1Mono);
                    else
                        resultingPoly.getMonomial(p2Mono.getPower()).setCoefficient(coeff);
                    OK = 0;
                }
            }
            if (OK == 1)
                resultingPoly.addMonomial(p2Mono);
        }

        return resultingPoly;
    }

    public Polynomial substractP(Polynomial firstPoly, Polynomial secondPoly) {
        Polynomial resultingPoly = new Polynomial();

        for (Monomial p1Mono : firstPoly.getMonomials()) {
            resultingPoly.addMonomial(p1Mono);
        }

        for (Monomial p2Mono : secondPoly.getMonomials()) {
            int OK = 1;
            for (Monomial p1Mono : firstPoly.getMonomials()) {
                if (p2Mono.getPower() == p1Mono.getPower()) {
                    float coeff = p1Mono.getCoefficient() - p2Mono.getCoefficient();
                    if (coeff == 0)
                        resultingPoly.removeMonomial(p1Mono);
                    else
                        resultingPoly.getMonomial(p2Mono.getPower()).setCoefficient(coeff);
                    OK = 0;
                }
            }
            if (OK == 1) {
                resultingPoly.addMonomial(p2Mono);
                resultingPoly.getMonomial(p2Mono.getPower()).setCoefficient(-p2Mono.getCoefficient());
            }
        }

        return resultingPoly;
    }

    public Polynomial multiplyP(Polynomial firstPoly, Polynomial secondPoly) {
        Polynomial resultingPoly = new Polynomial();

        for (Monomial p1Mono : firstPoly.getMonomials()) {
            for (Monomial p2Mono : secondPoly.getMonomials()) {
                float coeff = p1Mono.getCoefficient() * p2Mono.getCoefficient();
                int power = p1Mono.getPower() + p2Mono.getPower();
                Monomial monomial = new Monomial(coeff, power);
                resultingPoly.addMonomial(monomial);
            }
        }

        return PolyUtil.polyCombine(resultingPoly);
    }

    public Polynomial[] divideP(Polynomial firstPoly, Polynomial secondPoly) {
        Polynomial[] result = new Polynomial[2];
        Polynomial quotient = new Polynomial();

        if (firstPoly.getMonomials().size() == 0 || secondPoly.getMonomials().size() == 0)
            return null;
        if (firstPoly.getMonomials().get(0).getPower() < secondPoly.getMonomials().get(0).getPower())
            return null;

        while (firstPoly.getMonomials().get(0).getPower() >= secondPoly.getMonomials().get(0).getPower()) {
            Monomial monoFirst, monoSecond, resMono;
            Polynomial multiplyPol = new Polynomial();
            monoFirst = firstPoly.getMonomials().get(0);
            monoSecond = secondPoly.getMonomials().get(0);
            resMono = PolyUtil.divideMono(monoFirst, monoSecond);
            quotient.addMonomial(resMono);
            multiplyPol.addMonomial(resMono);
            firstPoly = substractP(firstPoly, multiplyP(multiplyPol, secondPoly));
            if (firstPoly.getMonomials().size() == 0)
                break;
        }

        result[0] = quotient;
        result[1] = firstPoly;
        return result;
    }

    public Polynomial deriveP(Polynomial polynomial) {
        Polynomial resultingPoly = new Polynomial();

        for (Monomial monomial : polynomial.getMonomials()) {
            Monomial monoDer = PolyUtil.deriveMono(monomial);
            if (monoDer.getCoefficient() != 0)
                resultingPoly.addMonomial(monoDer);
        }

        return resultingPoly;
    }

    public Polynomial integrateP(Polynomial polynomial) {
        Polynomial resultingPoly = new Polynomial();

        for (Monomial monomial : polynomial.getMonomials()) {
            Monomial monoIntgr = PolyUtil.integrateMono(monomial);
            resultingPoly.addMonomial(monoIntgr);
        }

        return resultingPoly;
    }

}
