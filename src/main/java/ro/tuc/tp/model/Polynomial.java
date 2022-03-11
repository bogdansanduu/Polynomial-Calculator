package ro.tuc.tp.model;

import ro.tuc.tp.utils.PolyFormatException;
import ro.tuc.tp.utils.PolyUtil;

import java.util.ArrayList;
import java.util.Collections;

public class Polynomial {
    private ArrayList<Monomial> monomials;

    public void addMonomial(Monomial monomial) {
        this.monomials.add(monomial);
        Collections.sort(this.monomials);
        Collections.reverse(this.monomials);
    }

    public void addMonomials(ArrayList<Monomial> monomials) {
        this.monomials.addAll(monomials);
        Collections.sort(this.monomials);
        Collections.reverse(this.monomials);
    }

    public void removeMonomial(Monomial monomial) {
        this.monomials.remove(monomial);
    }

    public void removeMonomials(ArrayList<Monomial> monomials) {
        this.monomials.removeAll(monomials);
    }

    public Monomial getMonomial(int power) {
        for (Monomial monomial : this.monomials) {
            if (monomial.getPower() == power)
                return monomial;
        }
        return null;
    }

    public Polynomial(String inputPoly) throws PolyFormatException {

        this.monomials = PolyUtil.polyExtractor(inputPoly);
        PolyUtil.polyCombine(this);
        Collections.sort(this.monomials);
        Collections.reverse(this.monomials);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        for (Monomial mono1 : this.monomials) {
            int i = 0;
            for (Monomial mono2 : ((Polynomial) o).getMonomials()) {
                if (mono2.equals(mono1))
                    i++;
            }
            if (i != 1)
                return false;
        }
        return true;
    }

    public Polynomial() {
        this.monomials = new ArrayList<>();
    }

    public ArrayList<Monomial> getMonomials() {
        return monomials;
    }

}
