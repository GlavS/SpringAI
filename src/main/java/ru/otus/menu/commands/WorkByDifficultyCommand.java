package ru.otus.menu.commands;

import ru.otus.console.IOService;
import ru.otus.menu.Command;
import ru.otus.model.Difficulty;
import ru.otus.repository.WorkRepository;

public class WorkByDifficultyCommand implements Command {

    private final WorkRepository workRepository;
    private final IOService ioService;

    public WorkByDifficultyCommand(WorkRepository workRepository, IOService ioService) {
        this.workRepository = workRepository;
        this.ioService = ioService;
    }

    @Override
    public void execute() {
        String difficultyString = ioService
                .readString("Enter difficulty (EASY, MEDIUM, HARD, VERY_HARD):")
                .toUpperCase();
        Difficulty difficulty;
        try {
            difficulty = Difficulty.valueOf(difficultyString);
        } catch (IllegalArgumentException _) {
            ioService.printLine("Invalid difficulty: " + difficultyString);
            ioService.printLine("Next time enter difficulty (EASY, MEDIUM, HARD, VERY_HARD)");
            return;
        }
        workRepository.getByDifficulty(difficulty).forEach(work -> ioService.printLine(work.toString()));
    }
}
