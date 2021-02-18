package ControllerMVC;

import ModelMVC.*;
import ViewMVC.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        for(int i=0; i<view.radioButtons.size(); i++) {
            int finalI = i;
            view.radioButtons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(view.radioButtons.get(finalI).isSelected()) {
                        switch(finalI) {
                            case 4:
                            case 5: {
                                view.inputLabel2.setText("Reserved for other operations such as:");
                                view.inputField2.setText("Add, Subtract, Multiply and Divide.");
                                view.inputField2.setEditable(false);
                                break;
                            }
                            default: {
                                view.inputLabel2.setText("Insert the second polynomial:");
                                if(view.inputField2.getText().equals("Add, Subtract, Multiply and Divide."))
                                    view.inputField2.setText("");
                                view.inputField2.setEditable(true);
                                break;
                            }
                        }
                    }
                }
            });
        }

        //listens to the RadioButtons, calls required Model execution and updates View
        view.calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<view.radioButtons.size(); i++) {
                    //convert inputs to respective objects
                    if(view.radioButtons.get(i).isSelected()) {
                        try {
                            model.getPolynomial(view.inputField1.getText(), 1);
                        } catch (InvalidInputException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(new JFrame(), "Please insert a valid polynomial.", "Invalid input!",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if(i<4) {
                            try {
                                model.getPolynomial(view.inputField2.getText(), 2);
                            } catch (InvalidInputException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(new JFrame(), "Please insert a valid polynomial.", "Invalid input!",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }

                        //check which operation is to be executed and execute
                        switch (i) {
                            //add
                            case 0: {
                                try {
                                    view.outputField.setText(model.addPolynomials().toString());
                                } catch (CloneNotSupportedException ex) {
                                    ex.printStackTrace();
                                }
                                break;
                            }
                            //subtract
                            case 1: {
                                try {
                                    view.outputField.setText(model.subtractPolynomials().toString());
                                } catch (CloneNotSupportedException ex) {
                                    ex.printStackTrace();
                                }
                                break;
                            }
                            //multiply
                            case 2: {
                                try{
                                    view.outputField.setText(model.multiplyPolynomials().toString());
                                } catch(CloneNotSupportedException ex) {
                                    ex.printStackTrace();
                                }
                                break;
                            }
                            //divide
                            case 3: {
                                try {
                                    Pair<Polynomial, Polynomial> result = model.dividePolynomials();
                                    view.outputField.setText("Q: " + result.left.toString() + " R: " + result.right.toString());
                                } catch (NullPolynomialException | IllegalAccessException | ClassNotFoundException | InstantiationException | NoSuchMethodException | InvocationTargetException | CloneNotSupportedException ex) {
                                    ex.printStackTrace();
                                }
                                break;
                            }
                            //derive
                            case 4: {
                                try {
                                    view.outputField.setText(model.derivePolynomials().toString());
                                } catch (CloneNotSupportedException ex) {
                                    ex.printStackTrace();
                                }
                                break;
                            }
                            //integrate
                            case 5: {
                                try {
                                    view.outputField.setText(model.integratePolynomials().toString());
                                } catch (CloneNotSupportedException ex) {
                                    ex.printStackTrace();
                                }
                                break;
                            }
                            //cant get here but well
                            default: {
                                break;
                            }
                        }

                    }
                }
            }
        });
    }
}
