package prog2.samcis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberConverterBoard extends JFrame {
private JPanel numberPanel;
private JPanel buttonPanel;

private JButton convertButton, clearButton, exitButton;
private JTextField decimalTF;
private JTextField binaryTF, octalTF, hexadecimalTF;
private JLabel problemDisplayer;
private JPanel headPanel;
private ButtonsHandler buttonsHandler;

public static void main(String[] args) {
NumberConverterBoard board = new NumberConverterBoard();
}

public NumberConverterBoard() {
setTitle("Number Converter");
setSize(800, 400);
headPanel = new JPanel();
JLabel textArea = new JLabel();
textArea.setText("This application helps you convert a decimal number to binary, " +
"octal and hexadecimal. Please enter the decimal number in the field provided. Then, press the convert key.");
headPanel.add(textArea);
numberPanel = new JPanel();
setNumberPanel(numberPanel);
buttonPanel = new JPanel();
setButtonPanel(buttonPanel);
Container pane = getContentPane();
pane.setLayout(new GridLayout(4, 1));
pane.add(headPanel);
pane.add(numberPanel);
pane.add(buttonPanel);
this.setSize(500, 500);
setVisible(true);
setDefaultCloseOperation(EXIT_ON_CLOSE);
}

public void setButtonPanel(JPanel panel) {
convertButton = new JButton("Convert");
clearButton = new JButton("Clear");
exitButton = new JButton("Exit");
buttonsHandler = new ButtonsHandler();
convertButton.addActionListener(buttonsHandler);
clearButton.addActionListener(buttonsHandler);
exitButton.addActionListener(buttonsHandler);
panel.add(convertButton);
panel.add(clearButton);
panel.add(exitButton);
}

public void setNumberPanel(JPanel panel) {
JLabel decimalLabel = new JLabel("Decimal Number: ");
decimalLabel.setFont(new Font("Arial", Font.BOLD, 20));
decimalLabel.setHorizontalAlignment(JLabel.RIGHT);
decimalTF = new JTextField(20);
decimalTF.setFont(new Font("Arial", Font.BOLD, 20));

binaryTF = new JTextField(20);
binaryTF.setFont(new Font("Arial", Font.BOLD, 20));
binaryTF.setEditable(true);

octalTF = new JTextField(20);
octalTF.setFont(new Font("Arial", Font.BOLD, 20));
octalTF.setEditable(true);

hexadecimalTF = new JTextField(20);
hexadecimalTF.setFont(new Font("Arial", Font.BOLD, 20));
hexadecimalTF.setEditable(true);

JLabel binLabel = new JLabel("Binary Number: ");
binLabel.setFont(new Font("Arial", Font.BOLD, 20));
binLabel.setHorizontalAlignment(JLabel.RIGHT);
JLabel octLabel = new JLabel("Octal Number: ");
octLabel.setFont(new Font("Arial", Font.BOLD, 20));
octLabel.setHorizontalAlignment(JLabel.RIGHT);
JLabel hexLabel = new JLabel("Hexadecimal Number: ");
hexLabel.setFont(new Font("Arial", Font.BOLD, 20));
hexLabel.setHorizontalAlignment(JLabel.RIGHT);

panel.setLayout(new GridLayout(4, 2));
panel.add(decimalLabel);
panel.add(decimalTF);
panel.add(binLabel);
panel.add(binaryTF);
panel.add(octLabel);
panel.add(octalTF);
panel.add(hexLabel);
panel.add(hexadecimalTF);
}

private class ButtonsHandler implements ActionListener {
public void actionPerformed(ActionEvent e) {
if (e.getSource() == exitButton)
System.exit(0);
if (e.getSource() == clearButton) {
decimalTF.setText("");
binaryTF.setText("");
octalTF.setText("");
hexadecimalTF.setText("");

decimalTF.setEditable(true);
binaryTF.setEditable(true);
octalTF.setEditable(true);
hexadecimalTF.setEditable(true);
}
if (e.getSource() == convertButton) {
try {
EquivalentNumbers number = new EquivalentNumbers();

if (!decimalTF.getText().isEmpty()) {
number.setDecimalNumber(Double.parseDouble(decimalTF.getText()));
binaryTF.setText(number.getBinaryString());
octalTF.setText(number.getOctalString());
hexadecimalTF.setText(number.getHexadecimalString().toUpperCase());
} else if (!binaryTF.getText().isEmpty()) {
number.setBinaryString(binaryTF.getText());
decimalTF.setText(String.valueOf(number.getDecimalNumber()));
octalTF.setText(number.getOctalString());
hexadecimalTF.setText(number.getHexadecimalString().toUpperCase());
} else if (!octalTF.getText().isEmpty()) {
number.setOctalString(octalTF.getText());
decimalTF.setText(String.valueOf(number.getDecimalNumber()));
binaryTF.setText(number.getBinaryString());
hexadecimalTF.setText(number.getHexadecimalString().toUpperCase());
} else if (!hexadecimalTF.getText().isEmpty()) {
number.setHexadecimalString(hexadecimalTF.getText());
decimalTF.setText(String.valueOf(number.getDecimalNumber()));
binaryTF.setText(number.getBinaryString());
octalTF.setText(number.getOctalString());
}

decimalTF.setEditable(false);
binaryTF.setEditable(false);
octalTF.setEditable(false);
hexadecimalTF.setEditable(false);
} catch (NumberFormatException ex) {
JOptionPane.showMessageDialog(NumberConverterBoard.this, "One of the numbers does not follow the format of a number.", "Error", JOptionPane.ERROR_MESSAGE);
} catch (Exception ex) {
JOptionPane.showMessageDialog(NumberConverterBoard.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}
}
}
}
}//end of class
