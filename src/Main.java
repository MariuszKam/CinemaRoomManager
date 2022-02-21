import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seat = scanner.nextInt();

        char[][] cinema = new char[row + 1][seat + 1];

        for (int i = 0; i < row + 1; i++) {
            for (int j = 0; j < seat + 1; j++) {
                if (i == 0 && j == 0) {
                    cinema[i][j] = ' ';
                } else if (i == 0) {
                    cinema[i][j] = (char) (j + '0');
                } else if (j == 0) {
                    cinema[i][j] = (char) (i + '0');
                } else {
                    cinema[i][j] = 'S';
                }
            }
        }
        boolean on = true;
        int currentIncome = 0;

        while (on) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Cinema: ");
                    for (int i = 0; i < row + 1; i++) {
                        for (int j = 0; j < seat + 1; j++) {
                            System.out.print(cinema[i][j] + " ");
                        }
                        System.out.println();
                    }
                    break;

                case 2:
                    boolean rightTicket = false;

                    while (!rightTicket) {
                        System.out.println("Enter a row number:");
                        int seatRow = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int seatColum = scanner.nextInt();

                        if (seatRow > row || seatColum > seat) {
                            System.out.println("Wrong input!");
                        } else if (cinema[seatRow][seatColum] == 'B') {
                            System.out.println("That ticket has already been purchased!");
                        } else {
                            cinema[seatRow][seatColum] = 'B';
                            rightTicket = true;
                            if (row * seat <= 60) {
                                System.out.println("Ticket price: $10");
                                currentIncome += 10;
                            } else {
                                if (seatRow <= row / 2) {
                                    System.out.println("Ticket price: $10");
                                    currentIncome += 10;
                                } else {
                                    System.out.println("Ticket price: $8");
                                    currentIncome += 8;
                                }
                            }
                        }
                    }

                    break;

                case 3:
                    int countTicket = 0;
                    for (int i = 0; i < row + 1; i++ ) {
                        for (int j = 0; j < seat + 1; j++) {
                            if (cinema[i][j] == 'B') {
                                countTicket++;
                            }
                        }
                    }
                    System.out.println("Number of purchased tickets: " + countTicket);
                    float ticketPercentage = (float) countTicket / (row * seat) * 100;
                    System.out.printf("Percentage: %.2f", ticketPercentage);
                    System.out.println("%");
                    System.out.println("Current income: $" + currentIncome);
                    int totalIncome;
                    if (row * seat <= 60) {
                        totalIncome = row * seat * 10;
                    } else if (row % 2 == 0) {
                        totalIncome = row / 2 * seat * 10 + row / 2 * seat * 8;
                    } else {
                        totalIncome = (row / 2) * seat * 10 + (row / 2 + 1) * seat * 8;
                    }
                    System.out.println("Total income: $" + totalIncome);

                    break;


                case 0:
                    on = false;
                    break;
            }
        }

    }
}
