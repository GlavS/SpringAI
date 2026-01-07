package ru.otus.web;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dao.WorkDao;
import ru.otus.model.Work;

@RestController
@RequestMapping("/api/works")
public class WorkReadController {

    private final WorkDao dao;

    public WorkReadController(WorkDao dao) {
        this.dao = dao;
    }

    @GetMapping()
    public List<Work> findAllWorks() {
        return dao.findAll();
    }

    @GetMapping("/{workId}")
    public Work findWorkById(@PathVariable(name = "workId") long id) {
        return dao.findById(id).orElse(new Work());
    }
}
