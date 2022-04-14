package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {

    enum Coffee {

        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPUCCINO(200, 100, 12, 6);

        private final int WATER_PER_CUP;
        private final int MILK_PER_CUP;
        private final int COFFEE_BEANS_PER_CUP;
        private final int COST;

        Coffee(int waterAmount, int milkAmount, int coffeeBeansAmount, int cost) {
            this.WATER_PER_CUP = waterAmount;
            this.MILK_PER_CUP = milkAmount;
            this.COFFEE_BEANS_PER_CUP = coffeeBeansAmount;
            this.COST = cost;
        }

    }

    Scanner scanner = new Scanner(System.in);
    int waterAmount;
    int milkAmount;
    int coffeBeansAmount;
    int disposableCups;
    int balance;

    CoffeeMachine() {
        waterAmount = 400;
        milkAmount = 540;
        coffeBeansAmount = 120;
        disposableCups = 9;
        balance = 550;
    }

    public static void main(String... args) {
        new CoffeeMachine().run();
    }

    private void run() {
        String action;
        String prompt = "Write action (buy, fill, take, remaining, exit):";
        System.out.println(prompt);
        while (!(action = scanner.next()).equals("exit")) {
            switch (action) {
                case "buy"  ->  buy();
                case "fill" ->  fill();
                case "take" ->  take();
                default     ->  displayCoffeMachineStatus();
            }
            System.out.println(prompt);
        }

    }

    private void take() {
        System.out.println("I gave you $" + this.balance);
        this.balance = 0;
    }

    private void fill() {
        System.out.println("Write how many ml of water you want to add: ");
        this.waterAmount += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        this.milkAmount += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        this.coffeBeansAmount += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        this.disposableCups += scanner.nextInt();
    }

    private void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
        Coffee coffee;
        switch (scanner.next()) {
            case "1" -> coffee = Coffee.ESPRESSO;
            case "2" -> coffee = Coffee.LATTE;
            case "3" -> coffee = Coffee.CAPUCCINO;
            default  -> { return; }
        }
        if (!canTakeOrders(coffee).equals("true")) {
            System.out.println(canTakeOrders(coffee));
            return;
        }
        System.out.println("I have enough resources, making you a coffee!");
        this.waterAmount -= coffee.WATER_PER_CUP;
        this.milkAmount -= coffee.MILK_PER_CUP;
        this.coffeBeansAmount -= coffee.COFFEE_BEANS_PER_CUP;
        this.disposableCups--;
        this.balance += coffee.COST;
    }

    private void displayCoffeMachineStatus() {
        System.out.printf("""
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money
                """,
                this.waterAmount,
                this.milkAmount,
                this.coffeBeansAmount,
                this.disposableCups,
                this.balance
        );
    }

    private String canTakeOrders(Coffee coffee) {

        if (this.disposableCups == 0) {
            return "Sorry, not enough cups!";
        } else if (this.waterAmount < coffee.WATER_PER_CUP) {
            return "Sorry, not enough water!";
        } else if (this.milkAmount < coffee.MILK_PER_CUP) {
            return "Sorry, not enough milk!";
        } else if (this.coffeBeansAmount < coffee.COFFEE_BEANS_PER_CUP) {
            return "Sorry, not enough coffee beans";
        } else {
            return "true";
        }
    }

}
