package ru.otus.model;

import java.util.List;

public class Work {
    private long id;
    private String title;
    private Composer composer;
    private Instrument instrument;
    private List<Genre> genres;
    private List<Recording> recordings;
    private Difficulty difficulty;

    public Work() {}

    public Work(
            long id,
            String title,
            Composer composer,
            Instrument instrument,
            List<Genre> genres,
            List<Recording> recordings,
            Difficulty difficulty) {
        this.id = id;
        this.title = title;
        this.composer = composer;
        this.instrument = instrument;
        this.genres = genres;
        this.recordings = recordings;
        this.difficulty = difficulty;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Composer getComposer() {
        return composer;
    }

    public void setComposer(Composer composer) {
        this.composer = composer;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Recording> getRecordings() {
        return recordings;
    }

    public void setRecordings(List<Recording> recordings) {
        this.recordings = recordings;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Work work = (Work) o;
        return id == work.id
                && title.equals(work.title)
                && composer.equals(work.composer)
                && instrument.equals(work.instrument)
                && genres.equals(work.genres)
                && recordings.equals(work.recordings)
                && difficulty.equals(work.difficulty);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + title.hashCode();
        result = 31 * result + composer.hashCode();
        result = 31 * result + instrument.hashCode();
        result = 31 * result + genres.hashCode();
        result = 31 * result + recordings.hashCode();
        result = 31 * result + difficulty.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Work{" + "id="
                + id + ", title='"
                + title + '\'' + ", composer="
                + composer + ", instrument="
                + instrument + ", genres="
                + genres + ", recordings="
                + recordings + ", difficulty="
                + difficulty + '}';
    }
}
