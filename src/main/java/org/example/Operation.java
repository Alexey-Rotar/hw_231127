package org.example;

import java.util.Scanner;

public class Operation {
    Scanner sc = new Scanner(System.in);
    Account account = null;
    int change;

    private void create() {
        if (account == null) {
            System.out.print("Открытие счета...\nВведите начальный баланс: ");
            try {
                float initialBalance = Float.parseFloat(sc.nextLine());
                account = Account.create(initialBalance);
                System.out.println("Счёт открыт!");
                viewBalance();
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Ошибка!");
            } finally {
                menu();
            }
        } else {
            System.out.println("Счёт уже открыт!");
            menu();
        }
    }

    private void deposit() {
        if (account != null) {
            System.out.print("Пополнение счета...\nВведите сумму: ");
            try {
                float depositAmount = Float.parseFloat(sc.nextLine());
                account.deposit(depositAmount);
                System.out.printf("Счёт пополнен (+%.2f)\n", depositAmount);
                viewBalance();
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Ошибка!");
            } finally {
                menu();
            }
        } else {
            System.out.println("Необходимо открыть счёт!");
            menu();
        }
    }

    private void withdraw() {
        if (account != null) {
            System.out.print("Снятие средств со счета...\nВведите сумму: ");
            try {
                float withdrawalAmount = Float.parseFloat(sc.nextLine());
                account.withdraw(withdrawalAmount);
                System.out.printf("Сняты средства (-%.2f)\n", withdrawalAmount);
                viewBalance();
            } catch (InsufficientFundsException | IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Ошибка!");
            } finally {
                menu();
            }
        } else {
            System.out.println("Необходимо открыть счёт!");
            menu();
        }
    }

    private void viewBalance() {
        if (account != null) {
            System.out.printf("Текущий баланс: %.2f\n", account.getBalance());
        } else {
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
                System.out.println("Неверный пункт меню!");
                menu();
        }
    }
}
