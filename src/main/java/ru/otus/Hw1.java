package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.console.ConsoleIOService;
import ru.otus.console.IOService;

public class Hw1 {
    private static final Logger log = LoggerFactory.getLogger(Hw1.class);

    static void main() {
        log.info("HW1 started");
        IOService service = new ConsoleIOService(System.in, System.out);
        service.printLineF("Your choice is -> %d%n", service.readChoice());
    }
}
