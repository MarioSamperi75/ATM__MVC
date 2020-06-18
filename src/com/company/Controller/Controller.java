package com.company.Controller;

import com.company.Model.ATM;
import com.company.Model.Response;

public class Controller {
    private ATM atm;

    public Controller() {
        this.atm = new ATM();
    }

    public ATM getAtm() {
        return atm;
    }

    public void setAtm(ATM atm) {
        this.atm = atm;
    }

    public int getTotalAmountAvailable(){
        int totalAmountAvailable = atm.getTotalAmountAvailable();
        return totalAmountAvailable;
    }

    public int getTotalBillsQuantity(){
        int billsAmount = atm.countBills();
        return billsAmount;
    }

    public int getThousandBillsQuantity(){
        int thousandBillsQuantity = atm.getThousand().getQuantity();
        return thousandBillsQuantity;
    }


    public int getFiveHundredBillsQuantity() {
        int fiveHundredBillsQuantity = atm.getFiveHundred().getQuantity();
        return fiveHundredBillsQuantity;
    }

    public int getHundredBillsQuantity() {
        int hundredBillsQuantity = atm.getHundred().getQuantity();
        return hundredBillsQuantity;
    }

    public Response checkAvailability(int withdrawal){
        Response response= atm.checkAvailability(withdrawal);
        return response;
    }

    public void withdraw(int withdrawal){
        atm.withdraw(withdrawal);
    }
}
