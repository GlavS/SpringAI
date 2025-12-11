package ru.otus.model;

import com.opencsv.bean.CsvBindByName;
import java.util.Objects;

public class Work {
    @CsvBindByName
    Long id;

    @CsvBindByName
    String composer;

    @CsvBindByName
    String title;

    @CsvBindByName
    String instrument;

    @CsvBindByName
    Difficulty difficulty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Work(Long id, String composer, String title, String instrument, Difficulty difficulty) {
        this.id = id;
        this.composer = composer;
        this.title = title;
        this.instrument = instrument;
        this.difficulty = difficulty;
    }

    public Work() {}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Work work = (Work) o;
        return Objects.equals(id, work.id)
                && Objects.equals(composer, work.composer)
                && Objects.equals(title, work.title)
                && Objects.equals(instrument, work.instrument)
                && difficulty == work.difficulty;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(composer);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(instrument);
        result = 31 * result + Objects.hashCode(difficulty);
        return result;
    }

    @Override
    public String toString() {
        return "Work{" + "id="
                + id + ", composer='"
                + composer + '\'' + ", title='"
                + title + '\'' + ", instrument='"
                + instrument + '\'' + ", difficulty="
                + difficulty + '}';
    }
}
