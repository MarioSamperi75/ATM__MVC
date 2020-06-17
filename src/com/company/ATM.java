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
    public void charging() {
        this.thousand = new Bill("Thousand", 1000, 2);
        this.fiveHundred = new Bill("Five hundred", 500, 3);
        this.hundred = new Bill("Hundred", 100, 5);
        this.bills = new ArrayList<>();
        bills.add(thousand);
        bills.add(fiveHundred);
        bills.add(hundred);
    }

    public void calculateTotalAmount(){
        this.setTotalAvailable(0);
        for (Bill bill: bills)
        {this.totalAvailable += bill.getValue() * bill.getQuantity();}
    }

    public void withdraw(int withdrawal) {
        Response response = checkAvailability(withdrawal);
        if (response.getStatus())
            eliminateBills(withdrawal);
            //this.totalAvailable = totalAvailable - withdrawal;
    }

    public Response checkAvailability(int withdrawal){
        if (withdrawal<0)
            return new Response("no negative input, please", false);
        if (withdrawal> totalAvailable)
            return new Response("amount not available", false);
        if ((withdrawal % 100) != 0)
            return new Response("amount not payable", false);


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

    public void eliminateBills(int withdrawal){
        int left = withdrawal;

        while (left>=1000) {
            if (thousand.getQuantity() > 0) {
                thousand.setQuantity(thousand.getQuantity() - 1);
                left -= 1000;
            } else if (fiveHundred.getQuantity() > 0) {
                fiveHundred.setQuantity(fiveHundred.getQuantity() - 1);
                left -= 500;
            } else if (hundred.getQuantity() > 0) {
                hundred.setQuantity(hundred.getQuantity() - 1);
                left -= 100;
            }
        }

        while (left>=500) {
            if (fiveHundred.getQuantity() > 0) {
                fiveHundred.setQuantity(fiveHundred.getQuantity() - 1);
                left -= 500;
            } else if (hundred.getQuantity() > 0) {
                hundred.setQuantity(hundred.getQuantity() - 1);
                left -= 100;
            }
        }

        while (left>=100) {
            if (hundred.getQuantity() > 0) {
                hundred.setQuantity(hundred.getQuantity() - 1);
                left -= 100;
            }
        }
        calculateTotalAmount();
    }


    public int getLastThreeDigits(int withdrawal) {
        String withdrawalAsString =  Integer.toString(withdrawal);
        String lastTreeDigitsAsString = withdrawalAsString.substring(withdrawalAsString.length() - 3);
        int lastTreeDigitsAsInt = Integer.parseInt(lastTreeDigitsAsString);
        return lastTreeDigitsAsInt;
    }

    public int countBills(){
        int totalBills = 0;
        for (Bill bill : getBills()) {
            totalBills += bill.getQuantity();
        }
        return totalBills;

    }

}