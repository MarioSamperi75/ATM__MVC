package com.company;

import java.util.ArrayList;
import java.util.List;

public class ATM {
    int totalAvailable;
    Bill thousand;
    Bill fiveHundred;
    Bill hundred;
    List<Bill> bills;

//--------------------------------------


    public ATM() {
        charging();
        calculateTotalAmount();
    }

//--------------------------------------

    public int getTotalAvailable() {
        return totalAvailable;
    }

    public void setTotalAvailable(int totalAvailable) {
        this.totalAvailable = totalAvailable;
    }

    public Bill getThousand() {
        return thousand;
    }

    public void setThousand(Bill thousand) {
        this.thousand = thousand;
    }

    public Bill getFiveHundred() {
        return fiveHundred;
    }

    public void setFiveHundred(Bill fiveHundred) {
        this.fiveHundred = fiveHundred;
    }

    public Bill getHundred() {
        return hundred;
    }

    public void setHundred(Bill hundred) {
        this.hundred = hundred;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }


//--------------------------------------Service/Repository methods
    //this is just hard coding: the method should let an admin charge the machine depends on an input (or many)
    public void charging() {
        this.thousand = new Bill("Thousand", 1000, 2);
        this.fiveHundred = new Bill("Five hundred", 500, 3);
        this.hundred = new Bill("Hundred", 100, 5);
        this.bills = new ArrayList<>();
        bills.add(thousand);
        bills.add(fiveHundred);
        bills.add(hundred);
    }

    //the method goes through the list of bills and calculate the total Amount
    public void calculateTotalAmount(){
        this.setTotalAvailable(0);
        for (Bill bill: bills)
        {this.totalAvailable += bill.getValue() * bill.getQuantity();}
    }

    //the metod call checkcheckAvailability and eliminateBills
    public void withdraw(int withdrawal) {
        Response response = checkAvailability(withdrawal);
        if (response.getStatus())
            eliminateBills(withdrawal);
            //this.totalAvailable = totalAvailable - withdrawal;
    }

    public Response checkAvailability(int withdrawal){

        //preliminary checks
        if (withdrawal<0)
            return new Response("no negative input, please", false);    //ex. input: -300
        if (withdrawal> totalAvailable)
            return new Response("amount not available", false);         //ex. input: 4500 (beginning)
        if ((withdrawal % 100) != 0)
            return new Response("amount not payable", false);           //ex input: 2033

        //check over the availability of the bills
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

    public void eliminateBills(int withdrawal){                         //to improve: try a recursive function
        int left = withdrawal;

        while (left>=1000) {                                            //is the remaining part of withdrawal > 1000?
            if (thousand.getQuantity() > 0) {                           //is there any 1000 bill?
                thousand.setQuantity(thousand.getQuantity() - 1);       //"eliminate" a 1000 bill
                left -= 1000;                                           // subtract 1000 from the remaining part
            } else if (fiveHundred.getQuantity() > 0) {
                fiveHundred.setQuantity(fiveHundred.getQuantity() - 1);
                left -= 500;
            } else if (hundred.getQuantity() > 0) {
                hundred.setQuantity(hundred.getQuantity() - 1);
                left -= 100;
            }
        }

        while (left>=500) {                                             //same logic, but not check on 1000 bills anymore
            if (fiveHundred.getQuantity() > 0) {
                fiveHundred.setQuantity(fiveHundred.getQuantity() - 1);
                left -= 500;
            } else if (hundred.getQuantity() > 0) {
                hundred.setQuantity(hundred.getQuantity() - 1);
                left -= 100;
            }
        }

        while (left>=100) {                                             //same logic, but not check on 1000 bills anymore
            if (hundred.getQuantity() > 0) {
                hundred.setQuantity(hundred.getQuantity() - 1);
                left -= 100;
            }
        }
        calculateTotalAmount();
    }


    public int getLastThreeDigits(int withdrawal) {                    //from int to string, substring, again to int
        String withdrawalAsString =  Integer.toString(withdrawal);
        String lastTreeDigitsAsString = withdrawalAsString.substring(withdrawalAsString.length() - 3);
        int lastTreeDigitsAsInt = Integer.parseInt(lastTreeDigitsAsString);
        return lastTreeDigitsAsInt;
    }

    //the method goes through the list of bills and calculate the total quantity of bills
    public int countBills(){
        int totalBills = 0;
        for (Bill bill : getBills()) {
            totalBills += bill.getQuantity();
        }
        return totalBills;

    }

}