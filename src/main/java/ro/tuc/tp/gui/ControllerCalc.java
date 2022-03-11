package ro.tuc.tp.gui;

import ro.tuc.tp.logic.Operations;
import ro.tuc.tp.utils.PolyFormatException;
import ro.tuc.tp.model.Polynomial;
import ro.tuc.tp.utils.PolyUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControllerCalc implements ActionListener {
    private ViewCalc view;
    private Operations operations = new Operations();

    public ControllerCalc(ViewCalc v) {
        this.view = v;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String firstPoly = view.getFirstPolyTextField().getText();
        String secondPoly = view.getSecondPolyTextField().getText();
        Polynomial pol1 = null;
        Polynomial pol2 = null;
        try {
            pol1 = new Polynomial(firstPoly);
            pol2 = new Polynomial(secondPoly);
            Polynomial resultingP = new Polynomial();
            Polynomial[] resultingPDivide = new Polynomial[2];
            String result = "";
            switch (command) {
                case "ADD":
                    resultingP = operations.addP(pol1, pol2);
                    break;
                case "SUBTRACT":
                    resultingP = operations.substractP(pol1, pol2);
                    break;
                case "MULTIPLY":
                    resultingP = operations.multiplyP(pol1, pol2);
                    break;
                case "DERIVE":
                    resultingP = operations.deriveP(pol1);
                    break;
                case "INTEGRATE":
                    resultingP = operations.integrateP(pol1);
                    break;
                case "DIVIDE":
                    resultingPDivide = operations.divideP(pol1, pol2);
                    break;
            }
            if (command.equals("DIVIDE")) {
                if (resultingPDivide != null) {
                    result = "Q=" + PolyUtil.polyFormatter(resultingPDivide[0])
                            + "; R=" + PolyUtil.polyFormatter(resultingPDivide[1]);
                } else
                    result = "First polynomial must have bigger degree than second or empty field or one is 0";
            } else
                result = PolyUtil.polyFormatter(resultingP);
            if (result.length() == 0)
                view.getResultValueLabel().setText("0");
            else
                view.getResultValueLabel().setText(result);
        } catch (PolyFormatException ex) {
            view.getResultValueLabel().setText("wrong input");
        }
    }
}
