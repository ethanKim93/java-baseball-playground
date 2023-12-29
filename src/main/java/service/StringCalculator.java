package service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StringCalculator {
    private static final Logger logger = Logger.getLogger(StringCalculator.class.getName());

    public static Queue<String> parseString(String inputStr) {

        String[] values = inputStr.split(" ");
        return new LinkedList<>(Arrays.asList(values));

    }

    public static double add(double val1, double val2) {
        return val1 + val2;
    }

    public static double subtract(double val1, double val2) {
        return val1 - val2;
    }

    public static double multiply(double val1, double val2) {
        return val1 * val2;
    }

    public static double divide(double val1, double val2) {
        return val1 / val2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<String> calData = parseString(scanner.nextLine());

        double result = calculate(calData);
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.valueOf(result));
        }
    }

    public static double calculate(Queue<String> calData) {
        double result = Double.parseDouble(calData.poll());

        while (!calData.isEmpty()) {
            String operator = calData.poll();
            double val2 = Double.parseDouble(calData.poll());

            if (operator.equals("+")) {
                result = add(result, val2);
            }
            if (operator.equals("-")) {
                result = subtract(result, val2);
            }
            if (operator.equals("*")) {
                result = multiply(result, val2);
            }
            if (operator.equals("/")) {
                result = divide(result, val2);
            }
        }
        return result;
    }
}
