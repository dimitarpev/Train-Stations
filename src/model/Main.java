package model;

import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        TrainManager trainManager = new TrainManager();

        String help = "Commands:\n" +
                "\n" +
                "1: Search station linear\n" +
                "2: Search station binary\n" +
                "3: Sort connections with insertion sort\n" +
                "4: Sort connections with merge sort\n" +
                "5: Search shortest path between 2 stations\n" +
                "6: Determine MCST by specifying 2 stations\n" +
                "9: This menu\n" +
                "0: Quit\n";
        System.out.println(help);
        Scanner scanner = new Scanner(System.in);
        int input = Integer.parseInt(scanner.nextLine());
        while (input != 0) {
            switch (input) {
                case 9:
                    System.out.println(help);
                    break;
                case 1:
                    System.out.print("Please enter the full name of the station you want to search for: ");
                    String searchNameLinear = scanner.nextLine();
                    Station foundStationLinear = trainManager.linearSearch(searchNameLinear);
                    if (foundStationLinear == null){
                        System.out.println("No station with that name found!");
                    } else {
                        System.out.println(foundStationLinear);
                    }
                    break;

                case 2:
                    System.out.print("Please enter the full name of the station you want to search for: ");
                    String searchNameBinary = scanner.nextLine();
                    Station foundStationBinary = trainManager.binarySearch(searchNameBinary);
                    if (foundStationBinary == null) {
                        System.out.println("No station with that name found!");
                    } else {
                        System.out.println(foundStationBinary);
                    }
                    break;
                case 3:
                    trainManager.insertionSort();
                    System.out.println("Stations array is now sorted using insertion sort");
                    break;
                case 4:
                    trainManager.mergeSort();
                    System.out.println("Stations array is now sorted with merge sort");
                    break;
                case 5:
                    System.out.print("Enter the code for the starting station: ");
                    String code1 = scanner.nextLine();
                    System.out.print("Enter the code for the ending station: ");
                    String code2 = scanner.nextLine();
                    code1 = code1.trim();
                    code2 = code2.trim();
                    trainManager.shortestPath(code1, code2);
                    break;
                case 6:
                    System.out.print("Enter the code for the starting station: ");
                    String code1MCST = scanner.nextLine();
                    System.out.print("Enter the code for the ending station: ");
                    String code2MCST = scanner.nextLine();
                    trainManager.determineMCST(code1MCST, code2MCST);
                    break;
            }
            input = Integer.parseInt(scanner.nextLine());
        }
        scanner.close();

    }
}
