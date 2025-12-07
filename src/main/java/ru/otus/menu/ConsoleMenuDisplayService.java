package ru.otus.menu;

import ru.otus.console.IOService;

public class ConsoleMenuDisplayService implements MenuDisplayService {

    private final Menu menu;
    private final IOService ioService;

    public ConsoleMenuDisplayService(Menu menu, IOService ioService) {
        this.menu = menu;
        this.ioService = ioService;
    }

    @Override
    public void displayMenu() {
        menu.menuItems().forEach(item -> {
            ioService.printLineFormatted("%d. %s%n", item.id(), item.name());
        });
    }
}
