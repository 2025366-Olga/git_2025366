package ca1_foodstorage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Main - Entry point of the Food Storage application.
 * @author olgar
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        printBanner();
        FoodStorage storage = chooseStorageType();
        System.out.println("\n  Storage ready: " + storage.getStorageType());
        System.out.println("  Max capacity : " + storage.getCapacity() + " trays\n");
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> addFoodItem(storage);
                case 2 -> storage.removeItem();
                case 3 -> storage.peek();
                case 4 -> storage.displayAll();
                case 5 -> searchItem(storage);
                case 6 -> showTimeComplexity(storage);
                case 7 -> { System.out.println("\n  Goodbye!"); running = false; }
                default -> System.out.println("  [ERROR] Please enter 1 to 7.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printBanner() {
        System.out.println("=======================================================");
        System.out.println("   Fast Food Restaurant - Food Storage System");
        System.out.println("   Algorithms and Constructs - CA1");
        System.out.println("=======================================================\n");
    }

    private static void printMenu() {
        System.out.println("-------------------------------------------------------");
        System.out.println("  1. Add food item");
        System.out.println("  2. Remove food item");
        System.out.println("  3. Peek at top/front item");
        System.out.println("  4. Display all items");
        System.out.println("  5. Search for item by name");
        System.out.println("  6. Show time complexity");
        System.out.println("  7. Exit");
        System.out.println("-------------------------------------------------------");
    }

    private static FoodStorage chooseStorageType() {
        System.out.println("  Choose storage mode:");
        System.out.println("  1. Stack (LIFO) - add AND remove from SAME front side");
        System.out.println("  2. Queue (FIFO) - add from front, remove from OPPOSITE side\n");
        while (true) {
            int choice = readInt("  Enter 1 or 2: ");
            if (choice == 1) return new StackStorage(FoodStorage.MAX_CAPACITY);
            if (choice == 2) return new QueueStorage(FoodStorage.MAX_CAPACITY);
            System.out.println("  [ERROR] Please enter 1 or 2.");
        }
    }

    private static void addFoodItem(FoodStorage storage) {
        System.out.println("\n  --- ADD FOOD ITEM ---");
        System.out.println("  Valid types: Burger, Pizza, Fries, Sandwich, Hotdog");
        String name = readString("  Food name: ");
        if (!FoodItem.isValidFoodType(name)) {
            System.out.println("  [ERROR] '" + name + "' is not valid.");
            return;
        }
        double weight = readDouble("  Weight (grams): ");
        if (weight <= 0) {
            System.out.println("  [ERROR] Weight must be greater than 0.");
            return;
        }
        LocalDate today   = LocalDate.now();
        LocalDate maxDate = today.plusWeeks(2);
        System.out.println("  Best-before (yyyy-MM-dd) between " + today + " and " + maxDate);
        LocalDate bestBefore = readDate("  Best-before date: ");
        if (bestBefore == null) return;
        try {
            FoodItem item = new FoodItem(name, weight, bestBefore);
            storage.addItem(item);
        } catch (IllegalArgumentException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    private static void searchItem(FoodStorage storage) {
        String name = readString("  Enter food name to search: ");
        storage.search(name);
    }

    private static void showTimeComplexity(FoodStorage storage) {
        System.out.println("\n  === TIME COMPLEXITY ===");
        System.out.println("  add    → O(1)");
        System.out.println("  remove → O(1)");
        System.out.println("  peek   → O(1)");
        System.out.println("  display→ O(n)");
        System.out.println("  search → O(n)");
        System.out.println("  Storage: " + storage.getStorageType());
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("  [ERROR] Enter a whole number."); }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Double.parseDouble(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("  [ERROR] Enter a number like 150.5"); }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static LocalDate readDate(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        try {
            return LocalDate.parse(input, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            System.out.println("  [ERROR] Use format yyyy-MM-dd. Returning to menu.");
            return null;
        }
    }
}
