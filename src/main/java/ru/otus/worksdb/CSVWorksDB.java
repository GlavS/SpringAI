package ru.otus.worksdb;

import org.springframework.core.io.ClassPathResource;
import ru.otus.model.Work;

import java.nio.charset.Charset;
import java.util.List;

public class CSVWorksDB implements WorksDB {

    private final String resourseName;

    public CSVWorksDB(String resourseName) {
        this.resourseName = resourseName;
    }


    @Override
    public List<Work> getDB() {
        ClassPathResource resource = new ClassPathResource(resourseName);

        return List.of();
    }
}
