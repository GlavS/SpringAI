package ru.otus.console;

public interface IOService {
    void printLine(String msg);

    void printLineFormatted(String format, Object... args);

    int readMenuChoice();

    String readString(String prompt);
}
