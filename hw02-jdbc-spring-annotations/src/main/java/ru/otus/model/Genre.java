package ru.otus.model;

public class Genre {
    private long id;
    private String name;

    public Genre() {}

    public Genre(long id, String name) {
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

        Genre genre = (Genre) o;
        return id == genre.id && name.equals(genre.name);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Genre{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
