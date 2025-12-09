package ru.otus.console;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class ConsoleIOService implements IOService {
    private final PrintStream out;
    private final Scanner in;

    public ConsoleIOService(InputStream in, PrintStream out) {
        this.in = new Scanner(in, StandardCharsets.UTF_8);
        this.out = out;
    }

    @Override
    public void printLine(String msg) {
        out.println(msg);
    }

    @Override
    public int readMenuChoice() {
        out.println("Enter menu choice: ");
        String choice = in.nextLine();
        int choiceInt;
        try {
            choiceInt = Integer.parseInt(choice.trim());
        } catch (NumberFormatException _) {
            printLine("Please enter a number, corresponding to menu choice");
            return 0;
        }
        return choiceInt;
    }

    @Override
    public void printLineFormatted(String format, Object... args) {
        out.printf(format, args);
    }

    @Override
    public String readString(String prompt) {
        printLine(prompt);
        return in.nextLine();
    }
}
