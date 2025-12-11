package ru.otus.menu.commands;

import ru.otus.console.IOService;
import ru.otus.menu.Command;
import ru.otus.model.Difficulty;
import ru.otus.model.Work;
import ru.otus.repository.WorkRepository;

public class AddWorkCommand implements Command {

    private final WorkRepository workRepository;
    private final IOService ioService;

    public AddWorkCommand(WorkRepository workRepository, IOService ioService) {
        this.workRepository = workRepository;
        this.ioService = ioService;
    }

    @Override
    public void execute() {
        Work work = readWorkFromConsole();
        if (work == null) {
            ioService.printLine("Error adding work to database");
            return;
        }
        workRepository.addWork(work);
        ioService.printLine("Added work to database: " + work);
    }

    private Work readWorkFromConsole() {
        Work work = new Work();
        String composerName = ioService.readString("Please enter composer name:");
        String title = ioService.readString("Please enter work title:");
        String instrument = ioService.readString("Please enter instrument:");
        String difficultyString = ioService.readString("Please enter difficulty(EASY, MEDIUM, HARD, VERY_HARD):");
        Difficulty difficulty;
        try {
            difficulty = Difficulty.valueOf(difficultyString.toUpperCase());
        } catch (IllegalArgumentException _) {
            ioService.printLine("Invalid difficulty: " + difficultyString);
            ioService.printLine("Next time enter difficulty (EASY, MEDIUM, HARD, VERY_HARD)");
            return null;
        }
        work.setComposer(composerName);
        work.setTitle(title);
        work.setInstrument(instrument);
        work.setDifficulty(difficulty);

        return work;
    }
}
