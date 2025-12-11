package ru.otus.csvparser;

import java.util.List;
import ru.otus.model.Work;

public interface CsvParser {
    List<Work> parse();
}
