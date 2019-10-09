package isr;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        printWelcomeInfo();
        loop();

        System.out.println("Hello world!");
    }

    private static void printWelcomeInfo() {
        System.out.println("Available operators:");
        Calculator.getAvalableOperators().stream().forEach(a -> System.out.println(a));
        System.out.println("Enter math expression or enter blank line to exit");
    }

    private static void loop() {
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.print(">>> ");
            String expression = s.nextLine();
            if (expression.isBlank()) {
                break;
            }
            try {
                BigDecimal answer = Calculator.process(expression);
                System.out.println(answer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        s.close();
    }
}
