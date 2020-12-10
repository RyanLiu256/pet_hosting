package edu.guat.po;

import java.util.List;

public class DepositAndService {

    private Deposit deposit;
    private List<Service> services;

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "DepositAndService{" +
                "deposit=" + deposit +
                ", services=" + services +
                '}';
    }
}
