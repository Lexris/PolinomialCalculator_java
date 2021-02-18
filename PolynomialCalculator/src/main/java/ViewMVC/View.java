package ViewMVC;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View {
    public JFrame window;
    public JLabel inputLabel1, inputLabel2, outputLabel;
    public JTextField inputField1, inputField2, outputField;
    public ArrayList<JRadioButton> radioButtons;
    public JButton calculateButton;

    public View() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        window = new JFrame("Polynomial Calculator");
        window.setSize(screenSize.width/2, screenSize.height/2);
        window.setLocation((screenSize.width-window.getWidth())/2, (screenSize.height-window.getHeight())/2);

        inputLabel1 = new JLabel("Insert the first polynomial:");
        inputLabel1.setForeground(Color.WHITE);
        inputField1 = new JTextField();
        inputLabel2 = new JLabel("Insert the second polynomial:");
        inputLabel2.setForeground(Color.WHITE);
        inputField2 = new JTextField();
        outputLabel = new JLabel("Here's your result:");
        outputLabel.setForeground(Color.WHITE);
        outputField = new JTextField();

        radioButtons = new ArrayList<JRadioButton>();
        radioButtons.add(new JRadioButton("Add"));
        radioButtons.add(new JRadioButton("Subtract"));
        radioButtons.add(new JRadioButton("Multiply"));
        radioButtons.add(new JRadioButton("Divide"));
        radioButtons.add(new JRadioButton("Derive"));
        radioButtons.add(new JRadioButton("Integrate"));

        ButtonGroup radioButtonsGroup = new ButtonGroup();
        JPanel radioButtonsPanel = new JPanel();
        radioButtonsPanel.setBackground(Color.DARK_GRAY);
        radioButtonsPanel.setLayout(new GridLayout(1, radioButtons.size()));
        //am schimbat ceva aici din for simplu in for each ca a zis intellij si n-am chef sa verific dar pare bine la test
        for (JRadioButton radioButton : radioButtons) {
            radioButtonsGroup.add(radioButton);
            radioButtonsPanel.add(radioButton);
            radioButton.setForeground(Color.WHITE);
        }
        radioButtons.get(0).setSelected(true);
        calculateButton = new JButton("Calculate");

        JPanel inputPanel1 = new JPanel();
        inputPanel1.setLayout(new GridLayout(1, 2));
        inputPanel1.add(inputLabel1);
        inputPanel1.add(inputField1);
        inputPanel1.setBackground(Color.darkGray);

        JPanel inputPanel2 = new JPanel();
        inputPanel2.setLayout(new GridLayout(1, 2));
        inputPanel2.add(inputLabel2);
        inputPanel2.add(inputField2);
        inputPanel2.setBackground(Color.darkGray);

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(1, 2));
        outputPanel.add(outputLabel);
        outputPanel.add(outputField);
        outputPanel.setBackground(Color.darkGray);

        window.setLayout(new GridLayout(5,1));
        window.add(inputPanel1);
        window.add(inputPanel2);
        window.add(outputPanel);
        window.add(radioButtonsPanel);
        window.add(calculateButton);
        window.getContentPane().setBackground(Color.DARK_GRAY);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
