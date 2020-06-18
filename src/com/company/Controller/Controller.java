package com.company.Controller;

import com.company.Model.Repository;
import com.company.Model.Response;

public class Controller {
    private Repository repository;


    public Controller() {
        this.repository = new Repository();
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public int getTotalAmountAvailable(){
        return repository.getTotalAmountAvailable();
    }

    public int getTotalBillsQuantity(){
        return repository.countBills();
    }

    public int getThousandBillsQuantity(){
        return repository.getThousandBillsQuantity();
    }


    public int getFiveHundredBillsQuantity() {
        return repository.getFiveHundredBillsQuantity();
    }

    public int getHundredBillsQuantity() {
        return repository.getHundredBillsQuantity();
    }



    public Response checkAvailability(int withdrawal){
        Response response= repository.checkAvailability(withdrawal);
        return response;
    }

    public void withdraw(int withdrawal){
        repository.withdraw(withdrawal);
    }
}
