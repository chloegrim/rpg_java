package com.grimaldi;

import java.util.Scanner;

public class InputParser {
    public static String questionWithStringOutput(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question);

        return scanner.nextLine();
    }

    public static String questionWithStringOutput(String question, boolean newLine) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question + (newLine ? "\n" : ""));

        return scanner.nextLine();
    }

    public static int questionWithIntOutput(String question) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(question);
        String input = scanner.nextLine();

        return Integer.parseInt(input);
    }

    public static int questionWithIntOutput(String question, boolean newLine) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(question + (newLine ? "\n" : ""));
        String input = scanner.nextLine();

        return Integer.parseInt(input);
    }
}
