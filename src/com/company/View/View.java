package com.company.View;

import com.company.Controller.Controller;
import com.company.Model.Response;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame{

    Controller controller;
    JPanel panelOutput, panelInput;
    Label labelTotalAmount, valueTotalAmount;
    Label labelTotalBills, valueTotalBills;
    Label labelThousand, valueThousand;
    Label labelFiveHundred, valueFiveHundred;
    Label labelHundred, valueHundred;
    JLabel outputEmptySpace;
    Label message;
    JTextField input;
    JButton submit;
    JLabel inputEmptySpace;
    JButton quit;


    public View() {
        controller = new Controller();
        createGUI();
        createListeners();
    }

    public void createGUI(){

        panelOutput = new JPanel();
        panelInput = new JPanel();
        labelTotalAmount = new Label("Total Amount:");
        valueTotalAmount = new Label(Integer.toString(controller.getTotalAmountAvailable()));
        labelTotalBills = new Label("Total Bills:");
        valueTotalBills = new Label(Integer.toString(controller.getTotalBillsQuantity()));
        labelThousand = new Label("Thousand:");
        valueThousand = new Label(Integer.toString(controller.getThousandBillsQuantity()));
        labelFiveHundred = new Label("Five Hundred:");
        valueFiveHundred = new Label(Integer.toString(controller.getFiveHundredBillsQuantity()));
        labelHundred = new Label("Hundred: ");
        valueHundred = new Label(Integer.toString(controller.getHundredBillsQuantity()));
        outputEmptySpace = new JLabel();
        message = new Label();
        input = new JTextField();
        submit = new JButton("WITHDRAW");
        inputEmptySpace = new JLabel();
        quit = new JButton("QUIT");

//---------- OUTPUT PANEL --------------
        panelOutput.setBackground(Color.gray);

        labelTotalAmount.setPreferredSize(new Dimension(200, 28));
        labelTotalAmount.setBackground(Color.lightGray);
        panelOutput.add(labelTotalAmount);

        valueTotalAmount.setPreferredSize(new Dimension(80, 28));
        valueTotalAmount.setBackground(Color.lightGray);
        panelOutput.add(valueTotalAmount);

        labelTotalBills.setPreferredSize(new Dimension(200, 28));
        labelTotalBills.setBackground(Color.lightGray);
        panelOutput.add(labelTotalBills);

        labelTotalBills.setPreferredSize(new Dimension(200, 28));
        labelTotalBills.setBackground(Color.lightGray);
        panelOutput.add(labelTotalBills);

        valueTotalBills.setPreferredSize(new Dimension(80, 28));
        valueTotalBills.setBackground(Color.lightGray);
        panelOutput.add(valueTotalBills);

        labelThousand.setPreferredSize(new Dimension(200, 28));
        labelThousand.setBackground(Color.lightGray);
        panelOutput.add(labelThousand);

        valueThousand.setPreferredSize(new Dimension(80, 28));
        valueThousand.setBackground(Color.lightGray);
        panelOutput.add(valueThousand);

        labelFiveHundred.setPreferredSize(new Dimension(200, 28));
        labelFiveHundred.setBackground(Color.lightGray);
        panelOutput.add(labelFiveHundred);

        valueFiveHundred.setPreferredSize(new Dimension(80, 28));
        valueFiveHundred.setBackground(Color.lightGray);
        panelOutput.add(valueFiveHundred);

        labelHundred.setPreferredSize(new Dimension(200, 28));
        labelHundred.setBackground(Color.lightGray);
        panelOutput.add(labelHundred);

        valueHundred.setPreferredSize(new Dimension(80, 28));
        valueHundred.setBackground(Color.lightGray);
        panelOutput.add(valueHundred);

        outputEmptySpace.setPreferredSize(new Dimension(300, 50));
        panelOutput.add(outputEmptySpace);

        message.setPreferredSize(new Dimension(280, 28));
        message.setBackground(Color.lightGray);
        panelOutput.add(message);

//------------ INPUT PANEL -------------
        input.setPreferredSize(new Dimension(100,28));
        panelInput.add(input);
        submit.setPreferredSize(new Dimension(100,28));
        panelInput.add(submit);
        inputEmptySpace.setPreferredSize(new Dimension(176,28));
        panelInput.add(inputEmptySpace);
        quit.setPreferredSize(new Dimension(70,28));
        panelInput.add(quit);

//-------------- FRAME -------------
        this.setTitle("ATM");
        this.setLayout(new BorderLayout());
        this.add(panelOutput, BorderLayout.CENTER);
        this.add(panelInput, BorderLayout.SOUTH);
        this.setSize(500, 350);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public void createListeners() {

        quit.addActionListener(e -> {
            System.exit(0);
        });

        submit.addActionListener(e -> {
            int withdrawal = 0;
            boolean InputformatIsOk = true;
            try {
                withdrawal = Integer.parseInt(input.getText());
            } catch (NumberFormatException numberFormatException) {
                message.setText("invalid input");
                InputformatIsOk = false;
            }
            if (InputformatIsOk) {
                Response checkAvalaibility = controller.checkAvailability(withdrawal);
                if (checkAvalaibility.getStatus())                                   //a different message for every different case
                    controller.withdraw(withdrawal);
                    valueTotalAmount.setText(Integer.toString(controller.getTotalAmountAvailable()));
                    valueTotalBills.setText(Integer.toString(controller.getTotalBillsQuantity()));
                    valueThousand.setText(Integer.toString(controller.getThousandBillsQuantity()));
                    valueFiveHundred.setText(Integer.toString(controller.getFiveHundredBillsQuantity()));
                    valueHundred.setText(Integer.toString(controller.getHundredBillsQuantity()));
                    input.setText("");
                    message.setText(checkAvalaibility.getMessage());
            }

        });
    }

}
