package ru.otus.application;

import java.util.List;
import ru.otus.console.IOService;
import ru.otus.menu.Command;
import ru.otus.menu.Menu;
import ru.otus.menu.MenuItem;

public class ApplicationRunner {
    private final Menu menu;
    private final IOService ioService;

    public ApplicationRunner(Menu menu, IOService ioService) {
        this.menu = menu;
        this.ioService = ioService;
    }

    public void run() {
        boolean programIsRunning = true;
        List<MenuItem> menuItems = menu.menuItems();
        final int exitCommandNumber = menuItems.size() + 1;
        ioService.printLine("Repertoire library v. 0.1\n\n");
        while (programIsRunning) {
            menuItems.forEach(item -> ioService.printLineFormatted("%s. %s%n", item.id(), item.name()));
            ioService.printLine("5. Exit program");
            int choice = ioService.readMenuChoice();
            if (choice > exitCommandNumber || choice < 1) {
                ioService.printLine("Invalid choice, please try again");
            } else if (choice == exitCommandNumber) {
                programIsRunning = false;
            } else {
                MenuItem menuItem = menuItems.stream()
                        .filter(item -> item.id() == choice)
                        .findFirst()
                        .orElseThrow(() -> new MenuItemNotFoundException("Menu item not found, error"));
                Command command = menuItem.command();
                command.execute();
            }
        }
    }
}
