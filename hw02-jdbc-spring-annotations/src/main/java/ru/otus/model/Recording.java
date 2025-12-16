package ru.otus.model;

import java.util.Date;

public class Recording {
    private long id;
    private String performer;
    private String label;
    private Date date;
    private int durationSec;
    private String sourceUrl;

    public Recording() {}

    public Recording(long id, String performer, String label, Date date, int durationSec, String sourceUrl) {
        this.id = id;
        this.performer = performer;
        this.label = label;
        this.date = date;
        this.durationSec = durationSec;
        this.sourceUrl = sourceUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDurationSec() {
        return durationSec;
    }

    public void setDurationSec(int durationSec) {
        this.durationSec = durationSec;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Recording recording = (Recording) o;
        return id == recording.id
                && durationSec == recording.durationSec
                && performer.equals(recording.performer)
                && label.equals(recording.label)
                && date.equals(recording.date)
                && sourceUrl.equals(recording.sourceUrl);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + performer.hashCode();
        result = 31 * result + label.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + durationSec;
        result = 31 * result + sourceUrl.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Recording{" + "id="
                + id + ", Performer='"
                + performer + '\'' + ", label='"
                + label + '\'' + ", date="
                + date + ", durationSec="
                + durationSec + ", sourceUrl='"
                + sourceUrl + '\'' + '}';
    }
}
