package ru.otus.console;

public interface IOService {
    void printLine(String msg);

    void printLineF(String format, Object... args);

    int readChoice();
}
