package ru.otus.model;

public class Work {
    private long id;
    private String title;
    private Composer composer;
    private Instrument instrument;

    public Work() {}

    public Work(long id, String title, Composer composer, Instrument instrument) {
        this.id = id;
        this.title = title;
        this.composer = composer;
        this.instrument = instrument;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Work work = (Work) o;
        return id == work.id
                && title.equals(work.title)
                && composer.equals(work.composer)
                && instrument.equals(work.instrument);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + title.hashCode();
        result = 31 * result + composer.hashCode();
        result = 31 * result + instrument.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Work{" + "id="
                + id + ", title='"
                + title + '\'' + ", composer="
                + composer + ", instrument="
                + instrument + '}';
    }
}
