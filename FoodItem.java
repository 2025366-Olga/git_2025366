package ca1_foodstorage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author olgar
 */

    public class FoodItem {

    public static final String[] VALID_TYPES = {"Burger", "Pizza", "Fries", "Sandwich", "Hotdog"};

    private String name;
    private double weightGrams;
    private LocalDate bestBeforeDate;
    private LocalDateTime timeAdded;

    public FoodItem(String name, double weightGrams, LocalDate bestBeforeDate) {
        if (!isValidFoodType(name)) {
            throw new IllegalArgumentException("Invalid food type: " + name +
                ". Must be one of: Burger, Pizza, Fries, Sandwich, Hotdog.");
        }
        if (weightGrams <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0 grams.");
        }
        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusWeeks(2);
        if (bestBeforeDate.isBefore(today)) {
            throw new IllegalArgumentException("Best-before date cannot be in the past.");
        }
        if (bestBeforeDate.isAfter(maxDate)) {
            throw new IllegalArgumentException("Best-before date cannot exceed 2 weeks from today (" + maxDate + ").");
        }

        this.name           = name;
        this.weightGrams    = weightGrams;
        this.bestBeforeDate = bestBeforeDate;
        this.timeAdded      = LocalDateTime.now();
    }

    public String getName()              { return name; }
    public double getWeightGrams()       { return weightGrams; }
    public LocalDate getBestBeforeDate() { return bestBeforeDate; }
    public LocalDateTime getTimeAdded()  { return timeAdded; }

    public static boolean isValidFoodType(String name) {
        for (String type : VALID_TYPES) {
            if (type.equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s | %.1fg | Best Before: %s | Added: %s]",
            name, weightGrams, bestBeforeDate, timeAdded.format(dtf));
    }
}

