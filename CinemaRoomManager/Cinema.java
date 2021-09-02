package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

//    FALSE = S
//    TRUE = B
    private static boolean[][] places;
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        setupSeats();
        fillData();
        showMenu();
    }
    
    private static int getRows() {
        return places.length;
    }
    
    private static int getSeats() {
        return places[0].length;
    }

    private static void showMenu() {
        int input = -1;
        while (input != 0) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            input = scan.nextInt();

            switch (input) {
                case 1:
                    showSeats();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    statistics();
                    break;
            }
        }



    }

    private static void statistics() {
        int ticketPurchased = 0;

        for (int i = 0; i < getRows(); i++) {
            for (int y = 0; y < places[i].length; y++) {
                if (places[i][y]) {
                    ticketPurchased++;
                }
            }
        }

        double percentage = (double) ticketPurchased/(getRows()*getSeats())*100;
        System.out.println();
        System.out.println("Number of purchased tickets: " + ticketPurchased);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        currentIncome();
        totalIncome();


    }

    private static void setupSeats() {
        int rows;
        int seats;
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        rows = input.nextInt();

        System.out.println("Enter the number of seats in each row:");
        seats = input.nextInt();

        places = new boolean[rows][seats];
    }
    
    private static void buyTicket() {
        int row = 1;
        int seat = 1;

        boolean inputIncorrect = true;

        while (inputIncorrect) {
            System.out.println("Enter a row number:");
            row = scan.nextInt();

            System.out.println("Enter a seat number in that row:");
            seat = scan.nextInt();

            if (row > getRows() || row < 0 || seat > getSeats() || seat < 0) {
                System.out.println();
                System.out.println("Wrong input!");
            } else {
                if (places[row-1][seat-1]) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    inputIncorrect = false;
                }
            }
        }

        int price = getPrice(row);

        purchase(row, seat);

        System.out.println();
        System.out.println("Ticket price: $" + price);
    }

    private static void purchase(int row, int seat) {
        places[row-1][seat-1] = true;
    }

    private static void currentIncome() {
        int income = 0;

        for (int i = 1; i <= getRows(); i++) {
            for (int y = 1; y <= getSeats(); y++) {
                if (places[i-1][y-1]) {
                    income += getPrice(i);
                }
            }
        }

        System.out.println("Current income: $" + income);

    }

    private static int getPrice(int row) {
        int limit = (int) Math.floor((double)getRows()/2);
        if (getRows()*getSeats() <= 60) {
            return  10;
        } else {
            if (row <= limit) {
                return 10;
            } else {
                return 8;
            }
        }
    }

    private static void totalIncome() {
        int rows = getRows();
        int seats = getSeats();
        
        int income;

        if (rows * seats <= 60) {
            income = rows * seats * 10;
        } else {
            int limit = (int) Math.floor((double)rows/2);
            income = limit * seats * 10 + (rows - limit) * seats * 8;
        }

        System.out.println("Total Income: $" + income);
    }




    private static void fillData() {
        for (int i = 0; i < getRows(); i++) {
            Arrays.fill(places[i], false);
        }
    }

    private static void showSeats() {
        System.out.println("Cinema:");
        StringBuilder builder = new StringBuilder();
        builder.append(" ");
        for (int seat = 1; seat <= getSeats(); seat ++) {
            builder.append(" ").append(seat);
        }
        System.out.println(builder);
        for (int i = 0; i < getRows(); i++) {
            builder = new StringBuilder();
            builder.append(i+1);

            for (int y = 0; y < places[i].length; y++) {
                builder.append(" ")
                        .append(places[i][y] ? "B" : "S");
            }

            System.out.println(builder);

        }
    }
}