package ru.otus.menu.commands;

import ru.otus.console.IOService;
import ru.otus.menu.Command;
import ru.otus.model.Work;
import ru.otus.repository.WorkRepository;

import java.util.List;

public class WorkByComposerCommand implements Command {

    private final WorkRepository workRepository;
    private final IOService ioService;

    public WorkByComposerCommand(WorkRepository workRepository, IOService ioService) {
        this.workRepository = workRepository;
        this.ioService = ioService;
    }

    @Override
    public void execute() {
        String surname = ioService.readString("Enter composer surname:");
        List<Work> workList = workRepository.getByComposer(surname);
        workList.forEach(work -> ioService.printLine(work.toString()));
    }
}
