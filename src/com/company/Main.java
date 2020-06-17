package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        ATM atm = new ATM();

        while (true) {                                                             //main while: "new withdrawal?"

            int totalBills = atm.countBills();                                     //show the quantity of all bills
            System.out.println("Total bills: " + totalBills);

            for (Bill bill : atm.getBills()) {                                     //show the quantity of evry kind bills
                System.out.println(bill.getName() + ": " + bill.getQuantity());
            }

            int totalAmount = atm.getTotalAvailable();                             //show the amount of money available in the ATM
            System.out.println("Total Amount: " + totalAmount);

            boolean checkInput = false;
            int withdrawal = 0;
            while (checkInput == false) {                                           //while loop: to avoid unavailable input format
                checkInput = true;                                                  //(string or double for exemple)
                String input = JOptionPane.showInputDialog("withdrawal?");          //out from the loop only if the input is correct

                if (input == null){                                                 //cancel, x --> System.exit
                    System.out.println("bye bye");
                    System.exit(0);
                }
                try {
                    withdrawal = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Input not allowed");
                checkInput = false;                                                 //exception --> false --> another loop
                }
            }
            Response checkAvalaibility = atm.checkAvailability(withdrawal);         //withdrawal is not allowed:
            if (!checkAvalaibility.getStatus()) {                                   //a different message for every different case
                System.out.println(checkAvalaibility.getMessage());
                System.out.println();
            }
            else {
                atm.withdraw(withdrawal);                                           //show message and the new total amount
                System.out.println(checkAvalaibility.getMessage());
                System.out.println("New Total Amount: " + atm.getTotalAvailable());
                System.out.println();
            }

            int exitCheck = JOptionPane.showConfirmDialog(null, "Another withdrawal?");
            if (exitCheck!=0) {                                                     //yes --> another loop
                System.out.println("bye bye");                                      //no, cancel, X --> system.exit
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