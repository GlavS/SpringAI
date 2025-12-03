package ru.otus.console;

import java.io.PrintStream;

public interface IOService {
    void printLine(String msg);
    void printLineF(String format, Object ... args);
    int readChoice();
}
