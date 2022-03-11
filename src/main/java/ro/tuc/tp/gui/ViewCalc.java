package ro.tuc.tp.gui;

import javax.swing.*;
import java.awt.*;

public class ViewCalc extends JFrame {
    private JPanel resultPanel;
    private JLabel resultLabel;
    private JLabel resultValueLabel;
    private JPanel contentPanel;
    private JPanel polyPanel;
    private JLabel firstPolyLabel;
    private JTextField firstPolyTextField;
    private JLabel secondPolyLabel;
    private JTextField secondPolyTextField;
    private JButton multiplication;
    private JButton subtraction;
    private JButton division;
    private JButton derive;
    private JButton addition;
    private JButton integrate;

    ControllerCalc controller = new ControllerCalc(this);

    public ViewCalc(String name) {
        super(name);
        this.prepareGUI();
    }

    public void prepareGUI() {
        this.setSize(500, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.contentPanel = new JPanel(new GridLayout(2, 1));
        this.preparePolyPanel();
        this.prepareResultPanel();
        this.setContentPane(this.contentPanel);
    }

    private void prepareResultPanel() {
        this.resultPanel = new JPanel();
        this.resultPanel.setLayout(new GridLayout(1, 2));
        this.resultLabel = new JLabel("Result", JLabel.CENTER);
        this.resultValueLabel = new JLabel("", JLabel.CENTER);
        this.resultValueLabel.setOpaque(true);
        this.resultValueLabel.setBackground(Color.CYAN);
        this.resultValueLabel.setForeground(Color.RED);
        this.resultPanel.add(this.resultLabel);
        this.resultPanel.add(this.resultValueLabel);
        this.contentPanel.add(resultPanel);
    }

    private void preparePolyPanel() {
        this.polyPanel = new JPanel();
        this.polyPanel.setLayout(new GridLayout(6, 2));
        this.firstPolyLabel = new JLabel("First Polynomial = ", JLabel.CENTER);
        this.polyPanel.add(this.firstPolyLabel);
        this.firstPolyTextField = new JTextField();
        this.firstPolyTextField.setText("Please give a sign to each monomial, even the first one");
        this.polyPanel.add(this.firstPolyTextField);
        this.secondPolyLabel = new JLabel("Second Polynomial = ", JLabel.CENTER);
        this.polyPanel.add(this.secondPolyLabel);
        this.secondPolyTextField = new JTextField();
        this.secondPolyTextField.setText("Example: +5x^2+3x-1");
        this.polyPanel.add(this.secondPolyTextField);
        this.addition = new JButton("Add(+)");
        this.addition.setActionCommand("ADD");
        this.addition.addActionListener(this.controller);
        this.polyPanel.add(this.addition);
        this.subtraction = new JButton("Subtract(-)");
        this.subtraction.setActionCommand("SUBTRACT");
        this.subtraction.addActionListener(this.controller);
        this.polyPanel.add(this.subtraction);
        this.multiplication = new JButton("Multiply(*)");
        this.multiplication.setActionCommand("MULTIPLY");
        this.multiplication.addActionListener(this.controller);
        this.polyPanel.add(this.multiplication);
        this.division = new JButton("Divide(/)");
        this.division.setActionCommand("DIVIDE");
        this.division.addActionListener(this.controller);
        this.polyPanel.add(this.division);
        this.derive = new JButton("Derive first polynomial");
        this.derive.setActionCommand("DERIVE");
        this.derive.addActionListener(this.controller);
        this.polyPanel.add(this.derive);
        this.contentPanel.add(this.polyPanel);
        this.integrate = new JButton("Integrate first polynomial");
        this.integrate.setActionCommand("INTEGRATE");
        this.integrate.addActionListener(this.controller);
        this.polyPanel.add(this.integrate);
    }

    public JTextField getFirstPolyTextField() {
        return firstPolyTextField;
    }

    public JTextField getSecondPolyTextField() {
        return secondPolyTextField;
    }

    public JLabel getResultValueLabel() {
        return resultValueLabel;
    }
}
