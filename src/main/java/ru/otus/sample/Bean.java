package ru.otus.sample;

public class Bean {
    private final String name;
    private String csv;

    public Bean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {}

    public String getCsv() {
        return csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }
}
