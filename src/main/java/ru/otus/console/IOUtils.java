package ru.otus.console;

public class IOUtils {
    public static void printLine(String msg) {
        System.out.println(msg);
    }

    public static void prNum(int num) {
        System.out.print(num + ". ");
    }

    public static int readChoice() {
        String choice = IO.readln("Введите пункт меню: ");
        int choiceInt;
        try {
            choiceInt = Integer.parseInt(choice.trim());
        } catch (NumberFormatException e) {
            printLine("Введите число");
            return 0;
        }
        return choiceInt;
    }
}
