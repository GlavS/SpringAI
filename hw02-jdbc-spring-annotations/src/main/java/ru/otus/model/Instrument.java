package ru.otus.model;

public class Instrument {
    private long id;
    private String name;

    public Instrument() {}

    public Instrument(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Instrument that = (Instrument) o;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Instrument{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
