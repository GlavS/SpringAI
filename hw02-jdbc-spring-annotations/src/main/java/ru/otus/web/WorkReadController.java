package ru.otus.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dao.WorkDao;
import ru.otus.model.Work;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/works")
public class WorkReadController {

    private final WorkDao dao;

    public WorkReadController(WorkDao dao) {
        this.dao = dao;
    }

    @GetMapping()
    public List<Work> findAllWorks() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return dao.findAll();
    }

    @GetMapping("/{workId}")
    public Work findWorkById(@PathVariable(name = "workId") long id) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return dao.findById(id).orElse(new Work());
    }
}
