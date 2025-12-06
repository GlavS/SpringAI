package ru.otus.menu.commands;

import java.util.List;
import ru.otus.console.IOService;
import ru.otus.menu.Command;
import ru.otus.model.Work;
import ru.otus.repository.WorkRepository;

public class ShowRepertoireCommand implements Command {

    private final WorkRepository workRepository;
    private final IOService ioService;

    public ShowRepertoireCommand(WorkRepository workRepository, IOService ioService) {
        this.workRepository = workRepository;
        this.ioService = ioService;
    }

    @Override
    public void execute() {
        List<Work> workList = workRepository.getAll();
        ioService.printLine("All repertoire:");
        for (Work work : workList) {
            ioService.printLine(work.toString());
        }
    }
}
