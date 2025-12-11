package ru.otus.application;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException(String s) {
        super(s);
    }
}
