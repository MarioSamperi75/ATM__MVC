package com.company.Model;

import java.util.List;

public class Repository {
// I create the same variables as class ATM and I assign the right atm.getter.
// this assignment happens just one time, maybe that's less work for the computer.
// for sure the code becomes more readable.
    private ATM atm;
    private int totalAmountAvailable;
    private Bill thousand;
    private Bill fiveHundred;
    private Bill hundred;
    private List<Bill> bills;


    public Repository() {
        this.atm = new ATM();

        this.thousand =  atm.getThousand();
        this.fiveHundred = atm.getFiveHundred();
        this.hundred = atm.getHundred();
        this.bills = atm.getBills();
        calculateTotalAmount();
        this.totalAmountAvailable= getTotalAmountAvailable();
    }

    public int getTotalAmountAvailable() {
        return totalAmountAvailable;
    }

    public void setTotalAmountAvailable(int totalAmountAvailable) {
        this.totalAmountAvailable = totalAmountAvailable;
    }

    public int countBills(){
        int totalBills = 0;
        for (Bill bill : bills) {
            totalBills += bill.getQuantity();
        }
        return totalBills;
    }

    public int getThousandBillsQuantity() {
        return thousand.getQuantity();
    }

    public int getFiveHundredBillsQuantity() {
        return fiveHundred.getQuantity();
    }

    public int getHundredBillsQuantity() {
        return hundred.getQuantity();
    }

    public Response checkAvailability(int withdrawal){

        //preliminary controls
        if (withdrawal<0)
            return new Response("no negative input, please", false);    //ex. input: -300
        if (withdrawal> totalAmountAvailable)
            return new Response("amount not available", false);         //ex. input: 4500 (beginning)
        if ((withdrawal % 100) != 0)
            return new Response("amount not payable", false);           //ex input: 2033

        //control of the availability of the bills
        int withdrawalHundreds = getLastThreeDigits(withdrawal);
        int ATMTotalHundred = hundred.getValue() * hundred.getQuantity();
        int ATMTotalFiveHundreds =fiveHundred.getValue() * fiveHundred.getQuantity();

        if (withdrawalHundreds<500 && ATMTotalHundred < withdrawalHundreds)
            return new Response("amount not payable", false);

        if (withdrawalHundreds>=500)
            if  (ATMTotalFiveHundreds == 0 && ATMTotalHundred <  withdrawalHundreds      ||
                    ATMTotalFiveHundreds  > 0 && ATMTotalHundred < (withdrawalHundreds - 500))
                return new Response("amount not payable", false);

        return new Response("amount available", true);
    }

    //the metod calls checkAvailability and eliminateBills
    public void withdraw(int withdrawal) {
        Response response = checkAvailability(withdrawal);
        if (response.getStatus())
            eliminateBills(withdrawal);
    }

    public void eliminateBills(int withdrawal){                         //to improve: try a recursive function
        int left = withdrawal;

        while (left>=1000) {                                            //is the remaining part of withdrawal > 1000?
            if (thousand.getQuantity() > 0) {                           //is there any thousand-bill?
                thousand.setQuantity(thousand.getQuantity() - 1);       //"eliminate" a thousand-bill
                left -= 1000;                                           // subtracts 1000 from the remaining part
            } else if (fiveHundred.getQuantity() > 0) {
                fiveHundred.setQuantity(fiveHundred.getQuantity() - 1);
                left -= 500;
            } else if (hundred.getQuantity() > 0) {
                hundred.setQuantity(hundred.getQuantity() - 1);
                left -= 100;
            }
        }

        while (left>=500) {                                             //same logic, but not control of thousand-bills anymore
            if (fiveHundred.getQuantity() > 0) {
                fiveHundred.setQuantity(fiveHundred.getQuantity() - 1);
                left -= 500;
            } else if (hundred.getQuantity() > 0) {
                hundred.setQuantity(hundred.getQuantity() - 1);
                left -= 100;
            }
        }

        while (left>=100) {                                             //same logic, but not control of thousand-bills anymore
            if (hundred.getQuantity() > 0) {
                hundred.setQuantity(hundred.getQuantity() - 1);
                left -= 100;
            }
        }
        calculateTotalAmount();
    }

    //the method goes through the list of bills and calculate the total Amount
    public void calculateTotalAmount(){
        this.totalAmountAvailable = 0;
        for (Bill bill: bills) {
            totalAmountAvailable += bill.getValue() * bill.getQuantity();
        }
    }

    public int getLastThreeDigits(int withdrawal) {                    //from int to string --> substring --> again to int
        String withdrawalAsString =  Integer.toString(withdrawal);
        String lastTreeDigitsAsString = withdrawalAsString.substring(withdrawalAsString.length() - 3);
        int lastTreeDigitsAsInt = Integer.parseInt(lastTreeDigitsAsString);
        return lastTreeDigitsAsInt;
    }
}
