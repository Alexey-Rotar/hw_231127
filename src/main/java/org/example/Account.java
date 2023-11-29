package org.example;

public class Account {
    private final float initialBalance;
    private float balance;

    private Account(float initialBalance){
        this.initialBalance = initialBalance;
        this.balance = initialBalance;
    }

    public static Account create(float initialBalance){
        if (initialBalance < 0)
            throw new IllegalArgumentException("Невозможно открыть счет с отрицательным начальным балансом!");
        return new Account(initialBalance);
    }

    public boolean deposit(float depositAmount) throws IllegalArgumentException{
        if (depositAmount < 0)
            throw new IllegalArgumentException("Невозможно внести депозит с отрицательной суммой!");
        if (depositAmount == 0)
            throw new IllegalArgumentException("Невозможно внести депозит с нулевой суммой!");
        this.balance += depositAmount;
        return true;
    }

    public boolean withdraw(float withdrawalAmount) throws InsufficientFundsException{
        if (withdrawalAmount < 0)
            throw new InsufficientFundsException("Невозможно снять отрицательную сумму!");
        if (withdrawalAmount == 0)
            throw new InsufficientFundsException("Невозможно снять нулевую сумму!");
        if (withdrawalAmount > this.balance)
            throw new InsufficientFundsException("Недостаточно средств!\nТекущий баланс: " + this.balance);
        this.balance -= withdrawalAmount;
        return true;
    }

    public float getBalance() {
        return balance;
    }

}
