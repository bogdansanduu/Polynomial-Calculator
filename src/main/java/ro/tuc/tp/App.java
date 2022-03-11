package ro.tuc.tp;

import ro.tuc.tp.gui.ControllerCalc;
import ro.tuc.tp.gui.ViewCalc;

import javax.swing.*;

public class App {
    public static void main(String[] args) {

        //View frame = new View("Simple calculator using MVC");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        //frame.setVisible(true);
        //Controller controller = new Controller(frame);
        ViewCalc frame1 = new ViewCalc("Poly Calculator");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);
        ControllerCalc controllerP = new ControllerCalc(frame1);
    }
}
