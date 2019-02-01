package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class View extends JFrame {

    private JButton evaluateButton;
    private JButton saveResultButton;
    private JButton showResultButton;
    private JTextField leftFormulaField;
    private JTextField rightFormulaField;
    private JTextPane instructionsPane;
    private JPanel mainPanel;
    private JLabel resultLabel;
    private Controller controller = new Controller();

    public View() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.height *= 0.6;
        screenSize.width *= 0.6;
        setSize(screenSize);

        instructionsPane.setText("Enter your formula in following text fields and click \"Evaluate\" button to get result\n" +
                "Syntax is following\n" +
                " - Formulas should be separated by semicolon(;)\n" +
                " - P[x, y, z] is an example of predicate\n" +
                " - ! stands for negation\n" +
                " - @ stands for \"For all\" quantifier\n" +
                " - # stands for \"Exists\" quantifier\n" +
                " - | stands for disjunction\n" +
                " - & stands for conjunction\n" +
                " - -> stands for implication\n" +
                " - to change priority of operations you can use ()\n");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);

        evaluateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                boolean result = false;
                try {
                    result = controller.evaluate(leftFormulaField.getText(), rightFormulaField.getText());
                    resultLabel.setText(result ? "True" : "False");
                } catch (Exception e1) {
                    e1.printStackTrace();
                    showErrorMessage(e1.toString());
                }
            }
        });

        showResultButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    showImage(controller.showResult());
                } catch (IOException e1) {
                    e1.printStackTrace();
                    showErrorMessage(e1.toString());
                }
            }
        });

        saveResultButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    showImage(controller.saveResult(getNameWindow("Enter name of file, where you want to save your result")));
                } catch (IOException e1) {
                    e1.printStackTrace();
                    showErrorMessage(e1.toString());
                }
            }
        });
    }

    public String getNameWindow(String msg){
        JFrame frame = new JFrame();
        Object result = JOptionPane.showInputDialog(frame, msg);
        return result.toString();
    }

    private void showImage(File imageFile) throws IOException {
        Desktop dt = Desktop.getDesktop();
        dt.open(imageFile);
    }

    private void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
