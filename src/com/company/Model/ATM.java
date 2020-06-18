package com.company.Model;

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
    }

//--------------------------------------

    public int getTotalAmountAvailable() {
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


//--------------------------------------
    //this is just hard coding: the method should let an admin charge the machine depending on an input (or many)
    public void charging() {
        this.thousand = new Bill("Thousand", 1000, 2);
        this.fiveHundred = new Bill("Five hundred", 500, 3);
        this.hundred = new Bill("Hundred", 100, 5);
        this.bills = new ArrayList<>();
        bills.add(thousand);
        bills.add(fiveHundred);
        bills.add(hundred);
    }


}