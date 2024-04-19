package prog2.samcis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberConverterBoard extends JFrame {
    private JPanel appInfoPanel, numbersPanel, buttonsPanel;
    private JLabel appInfoLabel, decimalLabel, binaryLabel, octalLabel, hexadecimalLabel;
    private JTextField decimalTF, binaryTF, octalTF, hexadecimalTF;
    private JButton convertButton, clearButton, exitButton;
    private ButtonsHandler buttonsHandler;

    public NumberConverterBoard() {
        setLayout(new BorderLayout());

        appInfoPanel = new JPanel(new BorderLayout());
        appInfoPanel.setPreferredSize(new Dimension(485, 80));
        appInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        appInfoLabel = new JLabel("<html><center><p>This application allows you to convert a number from any number " +
                "system to decimal, binary, octal, or hexadecimal. Please input the number into any of the provided " +
                "fields, then press the <b>'Convert'</b> button.</p></center></html>");
        appInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appInfoPanel.add(appInfoLabel);

        numbersPanel = new JPanel(new GridLayout(4, 2));
        numbersPanel.setPreferredSize(new Dimension(485, 150));
        numbersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        decimalLabel = new JLabel("Decimal Number: ");
        decimalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        binaryLabel = new JLabel("Binary Number: ");
        binaryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        octalLabel = new JLabel("Octal Number: ");
        octalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        hexadecimalLabel = new JLabel("Hexadecimal Number: ");
        hexadecimalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        decimalTF = new JTextField(30);
        binaryTF = new JTextField(25);
        octalTF = new JTextField(25);
        hexadecimalTF = new JTextField(25);

        numbersPanel.add(decimalLabel);
        numbersPanel.add(decimalTF);
        numbersPanel.add(binaryLabel);
        numbersPanel.add(binaryTF);
        numbersPanel.add(octalLabel);
        numbersPanel.add(octalTF);
        numbersPanel.add(hexadecimalLabel);
        numbersPanel.add(hexadecimalTF);

        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setPreferredSize(new Dimension(485, 50));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonsHandler = new ButtonsHandler();
        convertButton = new JButton("Convert");
        convertButton.addActionListener(buttonsHandler);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(buttonsHandler);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(buttonsHandler);

        buttonsPanel.add(convertButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(exitButton);

        add(appInfoPanel, BorderLayout.NORTH);
        add(numbersPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        setSize(485, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class ButtonsHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == exitButton) {
                System.exit(0);
            } else if (e.getSource() == clearButton) {
                decimalTF.setText("");
                binaryTF.setText("");
                octalTF.setText("");
                hexadecimalTF.setText("");
                makeTextFieldsEditableAtPanel(numbersPanel, true);
            } else if (e.getSource() == convertButton) {
                try {
                    EquivalentNumbers number = new EquivalentNumbers();
                    convertNumber(number);
                    makeTextFieldsEditableAtPanel(numbersPanel, false);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(NumberConverterBoard.this, "One of the numbers does not follow the " +
                            "expected format for its respective number system.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(NumberConverterBoard.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        public void convertNumber(EquivalentNumbers number) throws NumberFormatException, Exception {
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
        }

        public void makeTextFieldsEditableAtPanel(JPanel panel, boolean isEditable) {
            for (Component comp : panel.getComponents()) {
                if (comp instanceof JTextField) {
                    ((JTextField) comp).setEditable(isEditable);
                } if (comp instanceof JPanel) {
                    makeTextFieldsEditableAtPanel((JPanel) comp, isEditable);
                }
            }
        }
    }

    public static void main(String[] args) {
        new NumberConverterBoard();
    }
}