package org.example;

import java.util.Scanner;

public class ConsoleUI {
    Scanner sc = new Scanner(System.in);
    Account account = null;
    int change;

    private void create() {
        if (account == null) {
            System.out.println("Открытие счета.\nВведите начальный баланс:");
            float initialBalance = Float.parseFloat(sc.nextLine());
            try {
                account = Account.create(initialBalance);
                System.out.println("Счёт открыт!");
                viewBalance();
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } finally {
                menu();
            }
        }
        else {
            System.out.println("Счёт уже открыт!");
            menu();
        }
    }

    private void deposit() {
        if (account != null) {
            System.out.println("Пополнение счета.\nВведите сумму:");
            float depositAmount = Float.parseFloat(sc.nextLine());
            try {
                account.deposit(depositAmount);
                System.out.printf("Счёт пополнен (+%.2f)\n", depositAmount);
                viewBalance();
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } finally {
                menu();
            }
        }
        else {
            System.out.println("Необходимо открыть счёт!");
            menu();
        }
    }

    private void withdraw() {
        if (account != null) {
            System.out.println("Снятие средств со счета.\nВведите сумму:");
            float withdrawalAmount = Float.parseFloat(sc.nextLine());
            try {
                account.withdraw(withdrawalAmount);
                System.out.printf("Сняты средства (-%.2f)\n", withdrawalAmount);
                viewBalance();
            } catch (InsufficientFundsException ex) {
                System.out.println(ex.getMessage());
            } finally {
                menu();
            }
        }
        else {
            System.out.println("Необходимо открыть счёт!");
            menu();
        }
    }

    private void viewBalance() {
        if (account != null) {
            System.out.printf("Текущий баланс: %.2f\n", account.getBalance());
        }
        else {
            System.out.println("Необходимо открыть счёт!");
            menu();
        }
    }

    private void exit() {
        System.out.println("Работа завершена.");
        sc.close();
        System.exit(0);
    }
    public void menu() {
        System.out.println("\nВыберите действие:");
        System.out.println("0 - Открыть счёт");
        System.out.println("1 - Внести депозит");
        System.out.println("2 - Снять средства");
        System.out.println("3 - Запрос баланса");
        System.out.println("4 - Завершение работы");
        change = Integer.parseInt(sc.nextLine());
        switch (change) {
            case 0:
                create();
                break;
            case 1:
                deposit();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                viewBalance();
                menu();
                break;
            case 4:
                exit();
                break;
            default:
                menu();
        }
    }
}
