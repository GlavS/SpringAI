package ru.otus.csvparser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import org.springframework.core.io.Resource;
import ru.otus.db.ResourceReaderCreationException;
import ru.otus.model.Work;

public class CsvParserImpl implements CsvParser {
    private final Resource resource;

    public CsvParserImpl(Resource resource) {
        this.resource = resource;
    }

    @Override
    public List<Work> parse() {
        List<Work> works;
        try (Reader reader = new InputStreamReader(resource.getInputStream())) {
            CsvToBean<Work> csvToBean = new CsvToBeanBuilder<Work>(reader)
                    .withType(Work.class)
                    .withSeparator(';')
                    .build();
            works = csvToBean.parse();
        } catch (IOException e) {
            throw new ResourceReaderCreationException(e.getMessage(), e);
        }
        return List.copyOf(works);
    }
}
