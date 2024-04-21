package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

    private final JTextField textField;

    private double firstNumber = 0;
    private String operator = "";

    public Calculator() {
        super("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String buttonText = ((JButton) e.getSource()).getText();

            if (isDigitButton(buttonText)) {
                handleDigitButton(buttonText);
            } else if (isOperatorButton(buttonText)) {
                handleOperatorButton(buttonText);
            } else if (isDecimalButton(buttonText)) {
                handleDecimalButton(buttonText);
            }
        }

        private boolean isDigitButton(String buttonText) {
            return buttonText.matches("[0-9]");
        }

        private boolean isOperatorButton(String buttonText) {
            return buttonText.matches("[+\\-*/=]");
        }

        private boolean isDecimalButton(String buttonText) {
            return buttonText.equals(".");
        }

        private void handleDigitButton(String buttonText) {
            textField.setText(textField.getText() + buttonText);
        }

        private void handleOperatorButton(String buttonText) {
            if (!operator.isEmpty()) {
                double secondNumber = Double.parseDouble(textField.getText());
                double result = performOperation(firstNumber, secondNumber, operator);
                textField.setText(String.valueOf(result));
                firstNumber = result;
            } else {
                firstNumber = Double.parseDouble(textField.getText());
                textField.setText("");
            }
            operator = buttonText.equals("=") ? "" : buttonText;
        }

        private void handleDecimalButton(String buttonText) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText() + buttonText);
            }
        }
    }

    static double performOperation(double firstNumber, double secondNumber, String operator) {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if (secondNumber != 0)
                    return firstNumber / secondNumber;
                else
                    return Double.NaN; // Indicate division by zero
            default:
                return 0;
        }
    }
}