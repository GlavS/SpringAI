package ru.otus.db;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import ru.otus.model.Work;

public class CSVWorksDB implements WorksDB {

    private final Logger log = LoggerFactory.getLogger(CSVWorksDB.class);
    private final Resource resource;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public CSVWorksDB(Resource resource) {
        this.resource = resource;
    }

    @Override
    public List<Work> getDB() {
        List<Work> works;
        try (Reader reader = new InputStreamReader(resource.getInputStream())) {
            CsvToBean<Work> csvToBean = new CsvToBeanBuilder<Work>(reader)
                    .withType(Work.class)
                    .withSeparator(';')
                    .build();
            works = csvToBean.parse();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return works;
    }
}
