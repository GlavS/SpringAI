package ru.otus.menu;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public record MenuItem(int id, String name, Command command) {}
