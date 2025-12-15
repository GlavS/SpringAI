package ru.otus.model;

import java.util.Objects;

public class Composer {
    private long id;
    private String name;
    private String surname;
    private String country;

    public Composer() {}

    public Composer(long id, String name, String surname, String country) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Composer composer = (Composer) o;
        return id == composer.id
                && name.equals(composer.name)
                && surname.equals(composer.surname)
                && Objects.equals(country, composer.country);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + Objects.hashCode(country);
        return result;
    }

    @Override
    public String toString() {
        return "Composer{" + "id="
                + id + ", name='"
                + name + '\'' + ", surname='"
                + surname + '\'' + ", country='"
                + country + '\'' + '}';
    }
}
