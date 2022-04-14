package CinemaRoomManager;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int columns = scanner.nextInt();
        Character[][] cinema = buildCinemas(rows, columns);
        showMenu();
        for (int i = scanner.nextInt(); i != 0 ; i = scanner.nextInt()) {
            switch (i) {
                case 1 -> displayRoom(cinema);
                case 2 -> buyTicket(scanner, cinema, rows, columns);
                case 3 -> generateStatistics(cinema, rows, columns);
            }
            showMenu();
        }
    }

    private static void generateStatistics(Character[][] cinema, int rows, int columns) {
        int totalSeat = rows * columns;
        int seatBooked = 0;
        int currentIncome = 0;
        int maxProfit = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                if (cinema[i][j] == 'B') {
                    seatBooked++;
                    currentIncome += calculatePrice(rows, columns, i);
                }
                maxProfit += calculatePrice(rows, columns, i);
            }
        }
        System.out.printf(
                """
                        Number of purchased tickets: %d
                        Percentage: %.2f%%
                        Current income: $%d
                        Total income: $%d
                        """,
                seatBooked,
                100.0*seatBooked/totalSeat,
                currentIncome,
                maxProfit
        );
    }

    private static void buyTicket(Scanner scanner, Character[][] cinema, int rows, int columns) {
        System.out.println("Enter a row number: ");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        int column = scanner.nextInt();
        if (row < 1 || row > rows || column < 1 || column > columns) {
            System.out.println("Wrong input!");
            buyTicket(scanner, cinema, rows, columns);
            return;
        } else if (cinema[row][column] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buyTicket(scanner, cinema, rows, columns);
            return;
        }
        int ticketPrice = calculatePrice(rows, columns, row);
        System.out.println("Ticket price: $" + ticketPrice);
        bookSeat(row, column, cinema);
    }

    private static int calculatePrice(int rows, int columns, int row) {
        int ticketPrice;
        if (rows * columns <= 60) {
            ticketPrice = 10;
        } else {
            ticketPrice = row <= (rows / 2) ? 10 : 8;
        }
        return ticketPrice;
    }

    private static void showMenu() {
        System.out.println(
                """
                        1. Show the seats
                        2. Buy a ticket
                        3. Statistics
                        0. Exit"""
        );
    }

    private static void bookSeat(int row, int column, Character[][] cinema) {
        cinema[row][column] = 'B';
    }

    private static Character[][] buildCinemas(int rows, int columns) {
        Character[][] cinema = new Character[rows + 1][columns + 1];
        Arrays.stream(cinema).forEach((Character[] row) -> {
            Arrays.fill(row, 'S');
            row[0] = ' ';
        });
        Arrays.parallelSetAll(cinema[0], (int i) -> cinema[0][i] = (char)(i + 48));
        cinema[0][0] = ' ';
        return cinema;
    }

    private static void displayRoom(Character[][] cinema) {
        System.out.println("Cinema:");
        int columns = cinema[0].length;
        AtomicInteger rowCount = new AtomicInteger(0);
        Arrays.stream(cinema).forEach((Character[] row) -> {
            System.out.print(rowCount.getAndIncrement() == 0 ? " " : rowCount.get() - 1);
            IntStream.range(1, columns).forEach((int i) -> System.out.print(" " + row[i]));
            System.out.println();
        });
    }

}