package ru.otus.menu.commands;

import java.util.List;
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
        List<Work> workList = workRepository.getAll();
        workList.add(work);
        ioService.printLine("Added work to database: " + work);
        workList.forEach(w -> ioService.printLine(w.toString()));
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
        } catch (IllegalArgumentException e) {
            ioService.printLine("Invalid difficulty: " + difficultyString);
            ioService.printLine("Next time enter difficulty (EASY, MEDIUM, HARD, VERY_HARD)");
            return null;
        }
        long id = workRepository.getAll().size() + 1;
        work.setId(id);
        work.setComposer(composerName);
        work.setTitle(title);
        work.setInstrument(instrument);
        work.setDifficulty(difficulty);

        return work;
    }
}
