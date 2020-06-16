package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        ATM atm = new ATM();

        while (true) {

            int totalBills = 0;
            for (Bill bill : atm.getBills()) {
                totalBills += bill.getQuantity();
            }
            System.out.println("Total bills: " + totalBills);

            for (Bill bill : atm.getBills()) {
                System.out.println(bill.getName() + ": " + bill.getQuantity());
            }

            int totalAmount = 0;
            for (Bill bill : atm.getBills()) {
                totalAmount += bill.getValue() * bill.getQuantity();
            }
            System.out.println("Total Amount: " + totalAmount);
            boolean checkInput = false;
            int withdrawal = 0;
            while (checkInput == false) {
                checkInput = true;
                String input = JOptionPane.showInputDialog("withdrawal?");

                if (input == null){
                    System.out.println("bye bye");
                    System.exit(0);
                }
                try {
                    withdrawal = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Input not allowed");
                    checkInput = false;
                }
            }
            Response checkAvalaibility = atm.checkAvailability(withdrawal);
            if (!checkAvalaibility.getStatus()) {
                System.out.println(checkAvalaibility.getMessage());
                System.out.println();
            }
            else {
                atm.withdraw(withdrawal);
                System.out.println("New Total Amount: " + atm.getTotalAvailable());
                System.out.println();
            }

            int exitCheck = JOptionPane.showConfirmDialog(null, "Would you like to continue?");
            if (exitCheck!=0) {
                System.out.println("bye bye");
                System.exit(0);
            }



        }
    }
}

/* Description
Your assignment is to build an ATM in your programming language of choice.
The ATM should operate under the given rules:
● The bills used for withdrawal is removed from the ATM
● The ATM should always return the smallest amount of bills possible
○ Example: a withdrawal of 500 should return 1 x 500 bill rather than 5 x 100 bills if possible
● The ATM can't give out more money than it contains
● The ATM can't give out amounts that the remaining bills don’t add up to
________________________________________
The assignment
The ATM starts with a predetermined amount of each bill:
● 2 x 1000
● 3 x 500
● 5 x 100
You will make seven withdrawals the specified order. Note that based on the rules listed earlier some of the withdrawals should be declined by the ATM:
1.	1500
2.	 700
3.	400
4.	1100
5.	1000
6.	700
7.	300
When all of these withdrawals are completed the ATM is expected to be completely empty of bills and will not have given out more money than it contained at the start.
*/